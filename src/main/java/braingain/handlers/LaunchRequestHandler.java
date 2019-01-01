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
import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import braingain.modell.Gameround;
import phrasesAndConstants.PhrasesAndConstants;

public class LaunchRequestHandler implements RequestHandler {

	Gameround round;

	public LaunchRequestHandler(Gameround round) {
		this.round = round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {

		if (input.matches(intentName(PhrasesAndConstants.INTENT_NEW_ROUND))) {
			round.reset();
		}
		return input.matches(requestType(LaunchRequest.class))
				|| input.matches(intentName(PhrasesAndConstants.INTENT_NEW_ROUND));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		round.reset();
		return input.getResponseBuilder().withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.WELCOME)
				.withSpeech(PhrasesAndConstants.WELCOME).withReprompt("Necessary").build();
	}

}
