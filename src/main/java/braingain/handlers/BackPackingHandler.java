package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Gameround;
import phrasesAndConstants.PhrasesAndConstants;

public class BackPackingHandler implements RequestHandler {

	private Gameround round;

	public BackPackingHandler(Gameround round) {
		this.round = round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_BACK_PACKING));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "BackPackingHandler";
		Slot backPackSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_BACK_PACKING);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (backPackSlot != null) {
			String backPack = backPackSlot.getValue();
			// TODO now you have all values and need to iterate and find out what someone
			// wants to take and then test if all is correct and when so, add the last one
			// he said in round. super English

			// Der String hat keine Kommas, sollte ein Spieler sage "einen Stuhl", dann muss
			// man nach "ein" testen, sagt er nur "Stuhl", dann muss man nicht testen

			String[] words = backPack.split("\\W+");
			String testWord = "";
			int index = 0;

			for (int i = 0; i < words.length; i++) {

				// Get the word out of the String
				if (contains(words[i])) {
					testWord = words[i] + " " + words[i + 1];
					i++;
				} else if (words[i].contains("und")) {
					i++;
					if (contains(words[i])) {
						testWord = words[i] + " " + words[i + 1];
						i++;
					} else {
						testWord = words[i];
					}
				} else {
					testWord = words[i];
				}

				// Test the word and make response
				if (index == round.getBackPackSize()) {
					// last Word
					round.addItemToBackPack(testWord);
					if (round.getNumberOfPlayers() == 1) {
						round.getPlayerAt(0).setBackPackPoints(round.getBackPackSize());
						String item = round.getRandomBackPackWordForAlexa();
						speechText = String.format("%s %s und %s", PhrasesAndConstants.RIGHT_PACKING,
								round.backPackingAlexa(), item);
						round.addItemToBackPack(item);
						responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE,
								speechText);
						// for debbunging ^
//						responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.RIGHT_PACKING)
//						.withSpeech(speechText);
					} else {
						round.setNextRandomCurrentPlayer();
						speechText = String.format("%s %s. Nun kommt %s", PhrasesAndConstants.RIGHT_PACKING, testWord,
								round.getCurrentPlayer());
						responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE,
								speechText);
					}
					index++;
				} else if (round.checkWord(testWord, index)) {
					// Right
					index++;
				} else {
					// Wrong
					// TODO Maybe add that he only forgot one word
					// TODO Say the Highscore, when playing alone
					speechText = String.format("%s %s. Du hast gesagt %s. ", PhrasesAndConstants.WRONG_PACKING,
							round.getBackPackingAt(index), testWord);
					if (round.getNumberOfPlayers() == 1) {
						if (round.setBackPackHighscore()) {
							speechText += String.format("%s %s.", PhrasesAndConstants.NEW_HIGHSCORE,
									round.getPlayerAt(0).getBackPackHigscore());
						}else {
							speechText += PhrasesAndConstants.HIGHSCORE + round.getBackPackSize();
						}
					}
					responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText);
					break;
				}
			}
			//TODO funktioniert nicht im mehrspieler
//			if (index == round.getBackPackSize()) {
//				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.ADD_ONE_ITEM)
//						.withSpeech(PhrasesAndConstants.ADD_ONE_ITEM);
//			}
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND)
					.withSpeech(PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND);
		}

		return responseBuilder.withShouldEndSession(false).build();
	}

	private boolean contains(String s) {
		return s.equals("ein") || s.equals("eine") || s.equals("einen");
	}

}
