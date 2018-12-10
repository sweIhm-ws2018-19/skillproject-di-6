/*
Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
 except in compliance with the License. A copy of the License is located at

     http://aws.amazon.com/apache2.0/

 or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 the specific language governing permissions and limitations under the License.
*/

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

public class AnzahlDerSpielerSetzenHandler implements RequestHandler {

	private static final Object LIST_OF_PLAYERNUMBERS = "numberOfPlayers";
	
	Spielrunde sr;
	
	public AnzahlDerSpielerSetzenHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AnzahlDerSpielerSetzenIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		//int numberOfPlayers;
		
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		// Get the level slot from the list of slots.
		Slot selectedPlayerSlot = slots.get(LIST_OF_PLAYERNUMBERS);
		
		if(selectedPlayerSlot != null) {
			String numberOfPlayers = selectedPlayerSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(numberOfPlayers, LIST_OF_PLAYERNUMBERS));
			speechText = String.format("OK. Ihr spielt nun zu %s. Sagt mir nun einer zur Zeit eure Namen. Sagt zum Beispiel ich heisse Max.", numberOfPlayers);
			sr.setAnzahlSpieler(Integer.parseInt(numberOfPlayers));
		} else {
			speechText = "Ich habe deine Antwort leider nicht verstanden. Wie viele Spieler seid ihr?";
		}
		
		
/*
		try {
			numberOfPlayers = (int) input.getAttributesManager().getSessionAttributes().get(NumberOfPlayers);
		
			if(numberOfPlayers == 0 || numberOfPlayers > 4) {
				speechText = String.format("Die angegebene Spielerzahl %s kann nicht akzeptiert werden. Die Spieleranzahl ist auf 1 bis 4 Spieler begrenzt.", numberOfPlayers);
			}else {
				speechText = String.format("OK. Ihr spielt nun zu %s", numberOfPlayers);
			}
		
		}catch (Exception e) {
			speechText = "Es geschah ein Fehler: "+ e.getMessage();
		}*/
		
		
		return input.getResponseBuilder().withSpeech(speechText)
				.withShouldEndSession(false)
				.build();
		
	}
}
