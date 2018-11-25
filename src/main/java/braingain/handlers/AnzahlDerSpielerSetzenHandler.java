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

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import main.java.braingain.Modell.Spielrunde;


public class AnzahlDerSpielerSetzenHandler implements RequestHandler {

	private static final String NumberOfPlayers = "NumberOfPlayers";
	
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
		int numberOfPlayers;
		numberOfPlayers = (int) input.getAttributesManager().getSessionAttributes().get(NumberOfPlayers);
		
		if(numberOfPlayers == 0 || numberOfPlayers > 4) {
			speechText = String.format("Die angegebene Spielerzahl %s kann nicht akzeptiert werden. Die Spieleranzahl ist auf 1 bis 4 Spieler begrenzt.", numberOfPlayers);
		}else {
			speechText = String.format("OK. Ihr spielt nun zu %s", numberOfPlayers);
		}
		
		return input.getResponseBuilder().withSpeech(speechText).build();
		
	}
}
