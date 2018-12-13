package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Collections;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Spielrunde;
import phrasesAndConstants.PhrasesAndConstants;

public class LevelEinstellenHandler implements RequestHandler {

	private static final Object LIST_OF_LEVEL = "gewaehltesLevel";
	private Spielrunde sr;

	public LevelEinstellenHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("LevelEinstellenIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText, repromptText;

		Slot selectedLevelSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(LIST_OF_LEVEL);

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedLevelSlot != null && sr.getNumberOfPlayers() != 0 && sr.allPlayersSet()
				&& sr.getCategory() != null && sr.getLevel() == null) {

			String gewaehltesLevel = selectedLevelSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues()
					.get(0).getValue().getName();

			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehltesLevel, LIST_OF_LEVEL));
			if (sr.setLevel(gewaehltesLevel)) {
				if (sr.getNumberOfPlayers() == 1) {
					speechText = String.format(
							"Du hast das Level %s gewaehlt. Dir werden nun 10 Fragen gestellt. Sage Los, um zu beginnen.",
							sr.getLevel().toString());
				} else {
					speechText = String.format("Ihr habt das Level %s gewaehlt. Euch werden nun "
							+ sr.getNumberOfPlayers() * 5 + " Fragen gestellt. Sagt los um zu beginnen.",
							sr.getLevel().toString());
				}
				sr.buildQuestions();
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			} else {
				repromptText = String.format("Das Level %s kenne ich nicht.", gewaehltesLevel);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, repromptText).withReprompt(repromptText)
						.withShouldEndSession(false);
			}
		} else if (sr.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS).withShouldEndSession(false);
		} else if (!sr.allPlayersSet()) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_PLAYER_NAMES)
					.withSpeech(PhrasesAndConstants.SET_PLAYER_NAMES).withShouldEndSession(false);
		} else if (sr.getCategory() == null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_CATEGORY)
					.withSpeech(PhrasesAndConstants.SET_CATEGORY).withShouldEndSession(false);
		} else if (sr.getLevel() != null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.RESET_LEVEL)
			.withSpeech(PhrasesAndConstants.RESET_LEVEL).withShouldEndSession(false);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_LEVEL)
					.withReprompt(PhrasesAndConstants.REPROMPT_LEVEL).withShouldEndSession(false);
		}

		return responseBuilder.build();
	}

}
