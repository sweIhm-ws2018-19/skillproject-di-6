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

import braingain.modell.Kategorie;
import braingain.modell.Spieler;
import braingain.modell.Spielrunde;

import static com.amazon.ask.request.Predicates.intentName;

public class UsernamenSpeichernHandler implements RequestHandler {

	public static final String LIST_OF_NAMES = "username";
	private Spielrunde sr;
	private int spielerGenannt = 1;

	public UsernamenSpeichernHandler(Spielrunde sr) {
		this.sr = sr;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("UsernamenSpeichernIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText, repromptText;
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		
		Slot selectedNameSlot = slots.get(LIST_OF_NAMES);

		if (selectedNameSlot != null) {
			String username = selectedNameSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(username, LIST_OF_NAMES));
			sr.addPlayer(new Spieler(username));

			if (sr.getNumberOfPlayers() == 1) {
				speechText = String.format("Du heisst %s. ", username) + "Waehle nun deine Kategorie. Es gibt "
						+ Kategorie.values()[0].toString();
				for (int i = 1; i < Kategorie.values().length - 1; i++) {
					speechText += ", " + Kategorie.values()[i].toString();
				}
				speechText += " und " + Kategorie.values()[Kategorie.values().length - 1].toString() + ".";
			} else if (spielerGenannt < sr.getNumberOfPlayers()) {
				spielerGenannt++;
				speechText = String.format("Spieler %s heisst %s, bitte sagt mir nun den naechsten Namen.",
						spielerGenannt - 1, username);
			} else {
				speechText = String.format("Spieler %s heisst %s. Das sind nun alle Spieler. Eure Namen sind: ",
						spielerGenannt, username);
				switch (sr.getNumberOfPlayers()) {
				case 2:
					speechText += sr.getPlayer()[0] + " und " + sr.getPlayer()[1] + ". ";
					break;
				case 3:
					speechText += sr.getPlayer()[0] + ", " + sr.getPlayer()[1] + " und " + sr.getPlayer()[2] + ". ";
					break;
				case 4:
					speechText += sr.getPlayer()[0] + ", " + sr.getPlayer()[1] + ", " + sr.getPlayer()[2] + " und "
							+ sr.getPlayer()[3] + ". ";
					break;
				default:
					speechText += "Fehler.";
					break;
				}
				
				speechText += "Waehlt nun eure Kategorie. Es gibt " + Kategorie.values()[0].toString();

				for (int i = 1; i < Kategorie.values().length - 1; i++) {
					speechText += ", " + Kategorie.values()[i].toString();
				}
				speechText += " und " + Kategorie.values()[Kategorie.values().length - 1].toString() + ".";
				spielerGenannt = 1;
			}
			responseBuilder.withSimpleCard("SaveUserName", speechText).withSpeech(speechText).withShouldEndSession(false);
		} else {
			repromptText = "Ich habe deinen Namen leider nicht verstanden. Bitte wiederhole deinen Namen.";
			responseBuilder.withSimpleCard("SaveUserName", repromptText).withSpeech(repromptText).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
