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
	private final int NumberOfQuestionsPerPlayer = 3;
	private LaunchRequestHandler lrh;
	private Spielrunde round;

	public LevelEinstellenHandler(LaunchRequestHandler lrh) {
		this.lrh = lrh;
		round = lrh.round;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("LevelEinstellenIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText, repromptText;

		Slot selectedLevelSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(LIST_OF_LEVEL);

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedLevelSlot != null && round.getNumberOfPlayers() != 0 && round.allPlayersSet()
				&& round.getCategory() != null && round.getLevel() == null) {

			String gewaehltesLevel = selectedLevelSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues()
					.get(0).getValue().getName();

			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehltesLevel, LIST_OF_LEVEL));
			if (round.setLevel(gewaehltesLevel)) {
				if (round.getNumberOfPlayers() == 1) {
					speechText = String.format(
							"Du hast das Level %s gewaehlt. Dir werden nun 10 Fragen gestellt. Sage Los, um zu beginnen.",
							round.getLevel().toString());
				} else {
					speechText = String.format("Ihr habt das Level %s gewaehlt. Euch werden nun "
							+ round.getNumberOfPlayers() * NumberOfQuestionsPerPlayer + " Fragen gestellt. Sagt los um zu beginnen.",
							round.getLevel().toString());
				}
				round.buildQuestions();
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			} else {
				repromptText = String.format("Das Level %s kenne ich nicht.", gewaehltesLevel);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, repromptText).withReprompt(repromptText)
						.withShouldEndSession(false);
			}
		} else if (round.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS).withShouldEndSession(false);
		} else if (!round.allPlayersSet()) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_PLAYER_NAMES)
					.withSpeech(PhrasesAndConstants.SET_PLAYER_NAMES).withShouldEndSession(false);
		} else if (round.getCategory() == null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_CATEGORY)
					.withSpeech(PhrasesAndConstants.SET_CATEGORY).withShouldEndSession(false);
		} else if (round.getLevel() != null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.RESET_LEVEL)
			.withSpeech(PhrasesAndConstants.RESET_LEVEL).withShouldEndSession(false);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_LEVEL)
					.withReprompt(PhrasesAndConstants.REPROMPT_LEVEL).withShouldEndSession(false);
		}

		return responseBuilder.build();
	}

}