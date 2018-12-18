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

import braingain.modell.Kategorie;
import braingain.modell.Spieler;
import braingain.modell.Spielrunde;
import phrasesAndConstants.PhrasesAndConstants;

public class UsernamenSpeichernHandler implements RequestHandler {

	public static final String LIST_OF_NAMES = "username";
	private LaunchRequestHandler lrh;
	private Spielrunde round;

	public UsernamenSpeichernHandler(LaunchRequestHandler lrh) {
		this.lrh = lrh;
		this.round = lrh.round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("UsernamenSpeichernIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;

		Slot selectedNameSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(LIST_OF_NAMES);

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedNameSlot != null && round.getNumberOfPlayers() != 0 && round.getNumberOfPlayers() != round.getPlayer().length
				&& round.getCategory() == null && round.getLevel() == null) {
			String username = selectedNameSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(username, LIST_OF_NAMES));
			round.addPlayer(new Spieler(username));

			if (round.getNumberOfPlayers() == 1) {
				speechText = String.format("Du heisst %s. ", username) + "Waehle nun deine Kategorie. Es gibt "
						+ Kategorie.getKategorien();
			} else if (round.getPlayersCounted() < round.getNumberOfPlayers()) {
				round.increasePlayersCounted();
				speechText = String.format("Spieler %s heisst %s, bitte sagt mir nun den naechsten Namen.",
						round.getPlayersCounted() - 1, username);
			} else {
				speechText = String.format("Spieler %s heisst %s. Das sind nun alle Spieler. Eure Namen sind: ",
						round.getPlayersCounted(), username);
				switch (round.getNumberOfPlayers()) {
				case 2:
					speechText += round.getPlayer()[0] + " und " + round.getPlayer()[1] + ". ";
					break;
				case 3:
					speechText += round.getPlayer()[0] + ", " + round.getPlayer()[1] + " und " + round.getPlayer()[2] + ". ";
					break;
				case 4:
					speechText += round.getPlayer()[0] + ", " + round.getPlayer()[1] + ", " + round.getPlayer()[2] + " und "
							+ round.getPlayer()[3] + ". ";
					break;
				default:
					speechText += "Fehler.";
					break;
				}
				
				speechText += "Waehlt nun eure Kategorie. Es gibt " + Kategorie.getKategorien();
			}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
					.withShouldEndSession(false);
		} else if (round.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS).withShouldEndSession(false);
		} else if (round.getCategory() == null) {
			responseBuilder
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE,
							PhrasesAndConstants.SET_NEW_NAME + " " + PhrasesAndConstants.SET_CATEGORY)
					.withSpeech(PhrasesAndConstants.SET_NEW_NAME + " " + PhrasesAndConstants.SET_CATEGORY)
					.withShouldEndSession(false);
		} else if (round.getLevel() == null) {
			responseBuilder
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE,
							PhrasesAndConstants.SET_NEW_NAME + " " + PhrasesAndConstants.SET_LEVEL)
					.withSpeech(PhrasesAndConstants.SET_NEW_NAME + " " + PhrasesAndConstants.SET_LEVEL)
					.withShouldEndSession(false);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_SAVE_USERNAME)
					.withSpeech(PhrasesAndConstants.REPROMPT_SAVE_USERNAME).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
