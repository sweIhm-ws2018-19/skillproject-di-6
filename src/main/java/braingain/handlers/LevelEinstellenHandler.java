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

public class LevelEinstellenIntentHandler implements RequestHandler {

	private static final Object LIST_OF_LEVEL = "LIST_OF_LEVEL";
	private Spielrunde sr;
	
	public LevelEinstellenIntentHandler(Spielrunde sr){
		this.sr = sr;
		
	}
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("MyLevelIsIntent"));
	}

	
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		// Get the level slot from the list of slots.
		Slot selectedLevelSlot = slots.get(LIST_OF_LEVEL);

		String speechText, repromptText;
		boolean isAskResponse = false;
		if(sr.getAnzahlSpieler()==1) {
			// Check for level and create output to user.
			if (selectedLevelSlot != null) {
				// Store the user's Level in the Session and create response.
				String gewaehltesLevel = selectedCathegorySlot.getValue();
				input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteLevel, LIST_OF_LEVEL));
	
				speechText = String
						.format("Du hast Kategorie %s gewaehlt. Waehle jetzt dein Level. "
								+ "Es gibt folgende Level: Einfach, Mittel, Anspruchsvoll und Schwer.", gewaehltesLevel);
				repromptText = "Waehle jetzt dein Level.";
	
			} else {
				// Render an error since we don't know what the users favorite color is.
				speechText = "Ich kenne das Level nicht. Bitte versuche es noch einmal.";
				repromptText = "Ich habe das Level nicht verstanden. Sage mir das Level, in welchem du abgefragt werden willst. Sage zum Beispiel: ich waehle das Level Mittel.";
				isAskResponse = true;
			}

		}else {
			// Check for favorite color and create output to user.
						if (selectedLevelSlot != null) {
							// Store the user's favorite color in the Session and create response.
							String gewaehltesLevel = selectedCathegorySlot.getValue();
							input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehltesLevel, LIST_OF_LEVEL));
				
							speechText = String
									.format("Ihr habt Kategorie %s gewaehlt. Waehlt jetzt euer Level."
											+ "Es gibt folgende Level: Einfach, Mittel, Anspruchsvoll und Schwer.", gewaehltesLevel);
							repromptText = "Waehlt jetzt eurer Level.";
				
						} else {
							// Render an error since we don't know what the users favorite color is.
							speechText = "Ich kenne das Level nicht. Bitte versucht es noch einmal.";
							repromptText = "Ich habe das Level nicht verstanden. Sagt mir das Level, in welchem ihr abgefragt werden wollt. Sagt zum Beispiel: Wir waehlen das Level Mittel.";
							isAskResponse = true;
						}
				
		}
		
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		responseBuilder.withSimpleCard("LevelSession", speechText).withSpeech(speechText).withShouldEndSession(false);

		if (isAskResponse) {
			responseBuilder.withShouldEndSession(false).withReprompt(repromptText);
		}
		return responseBuilder.build();
	}

}

