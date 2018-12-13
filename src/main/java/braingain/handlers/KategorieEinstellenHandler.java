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

		Slot selectedCathegorySlot = slots.get(LIST_OF_CATEGORIES);

		String speechText, repromptText;
		boolean isAskResponse = false;
		if(sr.getAnzahlSpieler()==1) {
			if (selectedCathegorySlot != null) {
				String gewaehlteKategorie = selectedCathegorySlot.getValue();
				input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
	
				speechText = String
						.format("Du hast die Kategorie %s gewaehlt. Waehle nun das Level. Es gibt einfach, mittel, anspruchsvoll und schwer.", gewaehlteKategorie);
				repromptText = "Waehle jetzt deine Kategorie.";
				sr.setKategorie(gewaehlteKategorie);
	
			} else {
				speechText = "Ich kenne die Kategorie nicht. Bitte versuche es noch einmal.";
				repromptText = "Ich habe die Kategorie nicht verstanden. Sage mir die Kategorie, in welcher du abgefragt werden willst. Sage zum Beispiel: ich waehle die Katgorie Logik.";
				isAskResponse = true;
			}

		}else {
						if (selectedCathegorySlot != null) {
							String gewaehlteKategorie = selectedCathegorySlot.getValue();
							input.getAttributesManager().setSessionAttributes(Collections.singletonMap(gewaehlteKategorie, LIST_OF_CATEGORIES));
				
							speechText = String
									.format("Ihr habt die Kategorie %s gewaehlt. Waehlt nun euer Level. Es gibt einfach, mittel, anspruchsvoll und schwer.", gewaehlteKategorie);
							repromptText = "Waehlt jetzt eure Kategorie.";
							sr.setKategorie(gewaehlteKategorie);
						} else {
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
