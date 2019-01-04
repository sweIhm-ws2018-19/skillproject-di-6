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

import braingain.modell.Datenbank;
import braingain.modell.Gameround;
import braingain.modell.Player;
import phrasesAndConstants.PhrasesAndConstants;

public class SaveUsernameHandler implements RequestHandler {

	private Gameround round;

	public SaveUsernameHandler(Gameround round) {
		this.round = round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_SAVE_USERNAME));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Slot selectedNameSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_NAMES);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedNameSlot != null && round.getNumberOfPlayers() != 0
				&& round.getPlayersCounted() < round.getNumberOfPlayers()) {
			Datenbank db = new Datenbank(round);
			String username = selectedNameSlot.getValue();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(username, PhrasesAndConstants.LIST_OF_NAMES));
			round.addPlayer(new Player(username));

			if (round.getNumberOfPlayers() == 1) {
				speechText = String.format("Du heisst %s. %s", username, PhrasesAndConstants.LIST_ALL_CATEGORIES);
			} else {
				round.increasePlayerCount();
				speechText = String.format("Spieler %s heisst %s. ", round.getPlayersCounted(), username);

				if (round.getPlayersCounted() < round.getNumberOfPlayers()) {
					speechText += PhrasesAndConstants.SAY_NEXT_NAME;
				} else if (round.getPlayersCounted() == round.getNumberOfPlayers()) {
					speechText += round.getPlayerNames() + PhrasesAndConstants.LIST_ALL_CATEGORIES;
				}
			}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		} else if (round.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS);
		} else if (round.getPlayersCounted() >= round.getNumberOfPlayers()) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.ALL_PLAYERNAMES_SET)
					.withSpeech(PhrasesAndConstants.ALL_PLAYERNAMES_SET);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_SAVE_USERNAME)
					.withSpeech(PhrasesAndConstants.REPROMPT_SAVE_USERNAME);
		}
		return responseBuilder.withShouldEndSession(false).build();
	}
}
