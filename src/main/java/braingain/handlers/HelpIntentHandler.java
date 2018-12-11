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

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import braingain.modell.Spielrunde;

public class HelpIntentHandler implements RequestHandler {
	
	private Spielrunde sr;
	
	public HelpIntentHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Du kannst mit mir dein Gehirn trainieren. Zuerst musst du mir sagen, wie viele Spieler ihr seid, dann eure Namen, die Kategorie, welche ihr spielen wollt und das Level.";
        String repromptText = "Bitte sage mir, wie viele Spieler ihr seid.";
        return input.getResponseBuilder()
                .withSimpleCard("BraingainSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
