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

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import main.java.braingain.Modell.Spielrunde;

public class LaunchRequestHandler implements RequestHandler {
	
	private Spielrunde sr;
	
	public LaunchRequestHandler(Spielrunde sr){
		this.sr = sr;
	}
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Hallo. Mit mir trainierst du dein Gehirn. Bitte sage mir, mit wie vielen Spielern du spielst. Es koennen maximal 4 Spieler spielen.";
		String repromptText = "Bitte sage mir wie viele Leute spielen.";
		return input.getResponseBuilder().withSimpleCard("BrainSession", speechText).withSpeech(speechText)
				.withReprompt(repromptText).build();
	}
}
