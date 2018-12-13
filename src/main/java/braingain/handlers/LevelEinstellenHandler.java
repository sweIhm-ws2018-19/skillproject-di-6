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
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot selectedLevelSlot = slots.get(LIST_OF_LEVEL);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		String speechText, repromptText;

		if (selectedLevelSlot != null) {

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
				responseBuilder.withSimpleCard("LevelSession", speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			} else {
				repromptText = String.format("Das Level %s kenne ich nicht.", gewaehltesLevel);
				responseBuilder.withSimpleCard("LevelSession", repromptText).withReprompt(repromptText)
						.withShouldEndSession(false);
			}
		} else {
			repromptText = "Ich habe das Level nicht verstanden. Sage mir das Level, in welchem du abgefragt werden willst. Sage zum Beispiel: Mittel.";
			responseBuilder.withSimpleCard("LevelSession", repromptText).withReprompt(repromptText)
					.withShouldEndSession(false);
		}

		return responseBuilder.build();
	}

}
