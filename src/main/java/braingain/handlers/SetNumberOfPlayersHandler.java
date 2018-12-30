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
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Spielrunde;
import phrasesAndConstants.PhrasesAndConstants;

public class AnzahlDerSpielerSetzenHandler implements RequestHandler {


	private LaunchRequestHandler lrh;
	private Spielrunde round;

	public AnzahlDerSpielerSetzenHandler(LaunchRequestHandler lrh) {
		this.lrh = lrh;
		round = lrh.round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AnzahlDerSpielerSetzenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
	
		Slot selectedPlayerSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_PLAYERNUMBERS);

		ResponseBuilder responseBuilder = input.getResponseBuilder();
		
		if (selectedPlayerSlot != null) {
			String numberOfPlayers = selectedPlayerSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues()
					.get(0).getValue().getName();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(numberOfPlayers, PhrasesAndConstants.LIST_OF_PLAYERNUMBERS));
			round.setNumberOfPlayers(Integer.parseInt(numberOfPlayers));
			if (round.getNumberOfPlayers() == 1) {
				speechText = PhrasesAndConstants.PLAY_ALONE;
			} else {
				speechText = "OK. Ihr spielt nun zu " + round.getNumberOfPlayers() + " ." + PhrasesAndConstants.PLAY_WITH_MORE;
			}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
					.withShouldEndSession(false);
		} else {
			responseBuilder
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.REPROMPT_NUMBER_OF_PLAYERS).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
