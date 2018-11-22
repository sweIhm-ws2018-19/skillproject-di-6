package main.java.braingain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import main.java.braingain.Modell.Spielrunde;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class KategorieEinstellenIntentHandler implements RequestHandler {

	private static final Object LIST_OF_CATEGORIES = "LIST_OF_CATEGORIES";
	private Spielrunde sr;
	
	public KategorieEinstellenIntentHandler(Spielrunde sr){
		this.sr = sr;
		
	}
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("MyCathegoryIsIntent"));
	}

	
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		// Get the color slot from the list of slots.
		Slot selectedCathegorySlot = slots.get(LIST_OF_CATEGORIES);

		String speechText, repromptText;
		boolean isAskResponse = false;
		if(sr.getAnzahlSpieler()==1) {
			// Check for favorite color and create output to user.
			if (selectedCathegorySlot != null) {
				// Store the user's favorite color in the Session and create response.
				String gewaehlteKategorie = selectedCathegorySlot.getValue();
				input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
	
				speechText = String
						.format("Du hast dich mir vorgestellt. Wähle jetzt deine Kathegorie. "
								+ "Es gibt folgende Kategorien: Logik, Mathematik, Geografie und Gedächtnistraining.", gewaehlteKategorie);
				repromptText = "Wähle jetzt deine Kategorie.";
	
			} else {
				// Render an error since we don't know what the users favorite color is.
				speechText = "Ich kenne die Kategorie nicht. Bitte versuche es noch einmal.";
				repromptText = "Ich habe die Kahegorie nicht verstanden. Sage mir die Kategorie, in welcher du abgefragt werden willst. Sage zum Beispiel: ich wähle die Katgorie Logik.";
				isAskResponse = true;
			}

		}else {
			// Check for favorite color and create output to user.
						if (selectedCathegorySlot != null) {
							// Store the user's favorite color in the Session and create response.
							String gewaehlteKategorie = selectedCathegorySlot.getValue();
							input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
				
							speechText = String
									.format("Ihr habt euch mir jetzt vorgestellt. Wählt jetzt eure Kategorie."
											+ "Es gibt folgende Kategorien: Logik, Mathematik, Geografie und Gedächtnistraining.", gewaehlteKategorie);
							repromptText = "Wählt jetzt eure Kategorie.";
				
						} else {
							// Render an error since we don't know what the users favorite color is.
							speechText = "Ich kenne die Kathegorie nicht. Bitte versuche es noch einmal.";
							repromptText = "Ich habe die Kathegorie nicht verstanden. Sage mir die Kathegorie, in welcher du abgefragt werden willst. Sage zum Beispiel: ich wähle die Kathegorie Logik.";
							isAskResponse = true;
						}
				
		}
		
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		responseBuilder.withSimpleCard("KategorieSession", speechText).withSpeech(speechText).withShouldEndSession(false);

		if (isAskResponse) {
			responseBuilder.withShouldEndSession(false).withReprompt(repromptText);
		}
		return responseBuilder.build();
	}

}
