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

public class KategorieEinstellenHandler implements RequestHandler {

	private static final Object LIST_OF_CATEGORIES = "gewaehlteKategorie";
	private Spielrunde sr;
	
	public KategorieEinstellenHandler(Spielrunde sr){
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
						.format("Du hast die Kategorie %s gewaehlt. Waehle nun das Level. Es gibt einfach, mittel, anspruchsvoll und schwer.", gewaehlteKategorie);
				repromptText = "Waehle jetzt deine Kategorie.";
				sr.setKategorie(gewaehlteKategorie);
	
			} else {
				// Render an error since we don't know what the users favorite color is.
				speechText = "Ich kenne die Kategorie nicht. Bitte versuche es noch einmal.";
				repromptText = "Ich habe die Kategorie nicht verstanden. Sage mir die Kategorie, in welcher du abgefragt werden willst. Sage zum Beispiel: ich waehle die Katgorie Logik.";
				isAskResponse = true;
			}

		}else {
			// Check for favorite color and create output to user.
						if (selectedCathegorySlot != null) {
							// Store the user's favorite color in the Session and create response.
							String gewaehlteKategorie = selectedCathegorySlot.getValue();
							input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
				
							speechText = String
									.format("Ihr habt die Kategorie %s gewaehlt. Waehlt nun euer Level. Es gibt einfach, mittel, anspruchsvoll und schwer.", gewaehlteKategorie);
							repromptText = "Waehlt jetzt eure Kategorie.";
							sr.setKategorie(gewaehlteKategorie);
						} else {
							// Render an error since we don't know what the users favorite color is.
							speechText = "Ich kenne die Kategorie nicht. Bitte versuche es noch einmal.";
							repromptText = "Ich habe die Kategorie nicht verstanden. Sage mir die Kategorie, in welcher du abgefragt werden willst. Sage zum Beispiel: ich waehle die Kategorie Logik.";
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
