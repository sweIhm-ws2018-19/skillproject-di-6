package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Spielrunde;
import phrasesAndConstants.PhrasesAndConstants;

public class AntwortHandler implements RequestHandler {

	public static final Object ANTWORT = "antwort";
	private LaunchRequestHandler lrh;

	public AntwortHandler(LaunchRequestHandler lrh) {
		this.lrh = lrh;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AntwortIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {

		String speechText, repromptText;
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		
		Slot antwortSlot = slots.get(ANTWORT);

		if (antwortSlot != null) {
			String antwort = antwortSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(antwort, ANTWORT));
			
			if (lrh.sr.checkAnswer(antwort)) {
				speechText = "Deine Antwort ist richtig!";
			} else {
				speechText = String.format("Deine Antwort war leider falsch. Die richtige Antwort ist %s",
						lrh.sr.getRightAnswer());
			}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText).withShouldEndSession(false);
		} else {
			repromptText = "Ich habe deine Antwort leider nicht verstanden. Bitte wiederhole deine Antwort.";
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, repromptText).withSpeech(repromptText).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}

}
