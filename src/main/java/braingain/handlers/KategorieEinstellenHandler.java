package braingain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Spielrunde;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class KategorieEinstellenHandler implements RequestHandler {

	private static final Object LIST_OF_CATEGORIES = "gewaehlteKategorie";
	private Spielrunde sr;

	public KategorieEinstellenHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("KategorieEinstellenIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot selectedCathegorySlot = slots.get(LIST_OF_CATEGORIES);
		String speechText, repromptText;

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedCathegorySlot != null) {

			String gewaehlteKategorie = selectedCathegorySlot.getResolutions().getResolutionsPerAuthority().get(0)
					.getValues().get(0).getValue().getName();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
			if (sr.setKategorie(gewaehlteKategorie)) {
				speechText = sr.getNumberOfPlayers() == 1 ? "Du hast " : "Ihr habt ";

				speechText += String.format(
						"die Kategorie %s gewaehlt. Waehlt nun das Level. Es gibt einfach, mittel, anspruchsvoll und schwer.",
						sr.getCategorie().toString());

				responseBuilder.withSimpleCard("KategorieSession", speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			}else {
				speechText = "Diese Kategorie kenne ich nicht, bitte erneut eingeben";
			}
		} else {
			repromptText = "Ich habe die Kategorie nicht verstanden. Sage zum Beispiel: ich waehle die Katgorie Logik.";
			responseBuilder.withSimpleCard("KategorieSession", repromptText).withSpeech(repromptText)
					.withShouldEndSession(false);
		}
		return responseBuilder.build();
	}

}
