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
		String speechText = null;
		Slot backPackSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_BACK_PACKING);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (backPackSlot != null) {
			String backPack = backPackSlot.getValue();
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

				if (index == round.getBackPackSize()) {
					// last Word
					round.addItemToBackPack(testWord);
					if (round.getNumberOfPlayers() == 1) {
						round.getPlayerAt(0).setBackPackPoints(round.getBackPackSize());
						String item = round.getRandomBackPackWordForAlexa();
						speechText = String.format("%s %s und %s", PhrasesAndConstants.RIGHT_PACKING,
								round.backPackingAlexa(), item);
						round.addItemToBackPack(item);
						//responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE,
						//		speechText);
						// for debbunging ^
						 responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.RIGHT_PACKING).withSpeech(speechText);
					} else {
						round.setNextRandomCurrentPlayer();
						speechText = String.format("%s du hast %s hinzugefuegt. Nun kommt %s", PhrasesAndConstants.RIGHT_PACKING, testWord,
								round.getCurrentPlayer());
						responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE,
								speechText);
					}
					index++;
				} else if (round.checkWord(testWord, index)) {
					// Right said
					index++;
				} else {
					// Wrong said
					// TODO Maybe add that he only forgot one word
					
					speechText = String.format("%s %s. Du hast %s hinzugefuegt. ", PhrasesAndConstants.WRONG_PACKING,
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
			if(speechText == null) {
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.ADD_ONE_ITEM)
				.withSpeech(PhrasesAndConstants.ADD_ONE_ITEM);
			}
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND)
					.withSpeech(PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND);
		}

		return responseBuilder.withShouldEndSession(false).build();
	}

	private boolean contains(String s) {
		return s.equals("ein") || s.equals("eine") || s.equals("einen") || s.equals("einem");
	}

}
