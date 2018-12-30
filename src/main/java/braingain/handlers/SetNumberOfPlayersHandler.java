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

import braingain.modell.Gameround;
import phrasesAndConstants.PhrasesAndConstants;

public class SetNumberOfPlayersHandler implements RequestHandler {

	private Gameround round;

	public SetNumberOfPlayersHandler(Gameround round) {
		this.round = round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_SET_NUMBER_OF_PLAYERS));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Slot selectedPlayerSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_PLAYERNUMBERS);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedPlayerSlot != null) {
			String numberOfPlayers = selectedPlayerSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues()
					.get(0).getValue().getName();
			input.getAttributesManager().setSessionAttributes(
					Collections.singletonMap(numberOfPlayers, PhrasesAndConstants.LIST_OF_PLAYERNUMBERS));
			round.setNumberOfPlayers(Integer.parseInt(numberOfPlayers));
			if (round.getNumberOfPlayers() == 1) {
				round.setMaxQuestions(PhrasesAndConstants.MAX_NUMBER_OF_QUESTIONS_ONE_PLAYER);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.PLAY_ALONE)
						.withSpeech(PhrasesAndConstants.PLAY_ALONE);
			} else {
				round.setMaxQuestions(round.getNumberOfPlayers() * PhrasesAndConstants.MAX_NUMBER_OF_QUESTIONS_MORE_PLAYER);
				String speechText = String.format("OK. Ihr spielt nun zu %s. %s", round.getNumberOfPlayers(),
						PhrasesAndConstants.PLAY_WITH_MORE);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.PLAY_WITH_MORE)
						.withSpeech(speechText);
			}
		} else {
			responseBuilder
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.REPROMPT_NUMBER_OF_PLAYERS);
		}
		return responseBuilder.withShouldEndSession(false).build();
	}
}
