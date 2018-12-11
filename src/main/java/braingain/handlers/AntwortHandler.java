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

import braingain.modell.Spielrunde;

public class AntwortHandler implements RequestHandler {

	public static final Object ANTWORT = "antwort";
	private Spielrunde sr;

	public AntwortHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AntwortIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {

		String speechText;
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot antwortSlot = slots.get(ANTWORT);
		
		if (antwortSlot != null) {
			String antwort = antwortSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(antwort, ANTWORT));
			boolean richtig = sr.checkAntwort(antwort);
			if(richtig) {
			speechText = String.format("Deine Antwort war richtig!");
			}else {
				speechText = String.format("Deine Antwort war leider falsch. Die richtige Antwort ist %s", sr.getRichtigeAntwort());
			}

		} else {
			speechText = "Ich habe deine Antwort leider nicht verstanden. Bitte wiederhole deine Antwort.";

		}
			return input.getResponseBuilder().withSpeech(speechText)
					.withShouldEndSession(false)
					.build();
		}
	
}
