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
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import braingain.modell.Spielrunde;

public class AnzahlDerSpielerSetzenHandler implements RequestHandler {

	private static final String LIST_OF_PLAYERNUMBERS = "numberOfPlayers";

	Spielrunde sr;

	public AnzahlDerSpielerSetzenHandler(Spielrunde sr) {
		this.sr = sr;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AnzahlDerSpielerSetzenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;

		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot selectedPlayerSlot = slots.get(LIST_OF_PLAYERNUMBERS);

		if (selectedPlayerSlot != null) {
			//String numberOfPlayers = selectedPlayerSlot.getResolutions().getResolutionsPerAuthority().get(2).getValues().get(0).getValue().getName();
			String numberOfPlayers = selectedPlayerSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0).getValue().getName();
			sr.setAnzahlSpieler(Integer.parseInt(numberOfPlayers));
			if (sr.getAnzahlSpieler() == 1) {
				speechText = "OK, Du spielst alleine. Sage mir nun bitte deinen Namen.";
			} else {
				speechText = String.format(
						"OK. Ihr spielt nun zu %s. Sagt mir nun nacheinander eure Namen. Zum Beispiel: Ich heisse Max.",
						numberOfPlayers);
			}
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(numberOfPlayers, LIST_OF_PLAYERNUMBERS));

		} else {
			speechText = "Ich habe deine Antwort leider nicht verstanden. Wie viele Spieler seid ihr?";
		}

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();

	}
}
