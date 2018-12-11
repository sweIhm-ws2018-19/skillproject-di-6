/*
Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
 except in compliance with the License. A copy of the License is located at

     http://aws.amazon.com/apache2.0/

 or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 the specific language governing permissions and limitations under the License.
*/

package main.java.braingain.handlers;

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

import main.java.braingain.Modell.Spielrunde;

import static com.amazon.ask.request.Predicates.intentName;

public class UsernamenSpeichernHandler implements RequestHandler {

	public static final String LIST_OF_NAMES = "username";
	private Spielrunde sr;

	public UsernamenSpeichernHandler(Spielrunde sr) {
		this.sr = sr;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("UsernamenSpeichernIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		String speechText;
		//String name = (String) input.getAttributesManager().getSessionAttributes().get(NAME);
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		// Get the color slot from the list of slots.
		Slot selectedNameSlot = slots.get(LIST_OF_NAMES);
		
		if(selectedNameSlot != null) {
		String username = selectedNameSlot.getValue();
		input.getAttributesManager().setSessionAttributes(Collections.singletonMap(username, LIST_OF_NAMES));
		speechText = String.format("Du heiﬂt %s. Wenn ihr alle Namen genannt habt, waehlt eure Kategorie. Es gibt Mathe, Geografie, Logik und Gehirntraining.", username);
		} else {
			speechText = "Ich habe deinen Namen leider nicht verstanden. Bitte wiederhole deinen Namen.";
		}


		/*if (name != null && !name.isEmpty()) {
			speechText = String.format("Dein Name ist %s. Wenn ihr alle Namen genannt habt, waehlt eure Kategorie. Es gibt Mathe, Geografie, Logik und Gehirntraining.", name);
		} else {
			speechText = "Um deinen Namen zu speichern musst du ihn mir sagen.";
		}*/
		return input.getResponseBuilder().withSpeech(speechText)
				.withShouldEndSession(false)
				.build();
	}
}
