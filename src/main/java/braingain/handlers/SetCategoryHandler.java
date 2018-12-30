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

import braingain.modell.Level;
import braingain.modell.Spielrunde;
import phrasesAndConstants.PhrasesAndConstants;

public class KategorieEinstellenHandler implements RequestHandler {

	private static final Object LIST_OF_CATEGORIES = "gewaehlteKategorie";
	private LaunchRequestHandler lrh;
	private Spielrunde round;

	public KategorieEinstellenHandler(LaunchRequestHandler lrh) {
		this.lrh = lrh;
		this.round = lrh.round;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("KategorieEinstellenIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText, repromptText;

		Slot selectedCathegorySlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(LIST_OF_CATEGORIES);

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedCathegorySlot != null && round.getNumberOfPlayers() != 0 && round.allPlayersSet()
				&& round.getCategory() == null) {

			String gewaehlteKategorie = selectedCathegorySlot.getResolutions().getResolutionsPerAuthority().get(0)
					.getValues().get(0).getValue().getName();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
			if (round.setKategorie(gewaehlteKategorie)) {
				speechText = round.getNumberOfPlayers() == 1 ? "Du hast " : "Ihr habt ";
				speechText += String.format("die Kategorie %s gewaehlt. ", round.getCategory().toString());
				speechText += round.getNumberOfPlayers() == 1 ? "Waehle " : "Waehlt";
				speechText += " nun das Level. Es gibt " + Level.getLevels();

				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			} else {
				repromptText = String.format("Die %s kenne ich nicht, bitte erneut eingeben", gewaehlteKategorie);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, repromptText).withSpeech(repromptText)
						.withShouldEndSession(false);
			}
		} else if (round.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS).withShouldEndSession(false);
		} else if (!round.allPlayersSet()) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_PLAYER_NAMES)
					.withSpeech(PhrasesAndConstants.SET_PLAYER_NAMES).withShouldEndSession(false);
		} else if (round.getCategory() != null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.RESET_CATEGORY)
					.withSpeech(PhrasesAndConstants.RESET_CATEGORY).withShouldEndSession(false);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_CATEGORY)
					.withSpeech(PhrasesAndConstants.REPROMPT_CATEGORY).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}

}
