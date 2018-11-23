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

import test.java.*;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import main.java.braingain.Modell.Spielrunde;

import static com.amazon.ask.request.Predicates.intentName;

public class UsernamenSpeichernHandler implements RequestHandler {

	public static final String NAME = "NAME";
	private Spielrunde sr;

	public UsernamenSpeichernHandler(Spielrunde sr) {
		this.sr = sr;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("UsernameSpeichernIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		String speechText;
		String name = (String) input.getAttributesManager().getSessionAttributes().get(NAME);
		if (name != null && !name.isEmpty()) {
			speechText = String.format("Dein Name ist %s und wird gespeichert.", name);
		} else {
			speechText = "Um deinen Namen zu speichern musst du ihn mir sagen.";
		}
		return input.getResponseBuilder().withSpeech(speechText).build();
	}
}
