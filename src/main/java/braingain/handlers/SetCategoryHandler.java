package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Category;
import braingain.modell.Gameround;
import braingain.modell.MemoryTraining;
import braingain.modell.Player;
import phrasesAndConstants.PhrasesAndConstants;

public class SetCategoryHandler implements RequestHandler {

	private Gameround round;

	public SetCategoryHandler(Gameround round) {
		this.round = round;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_SET_CATEGORY));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Slot selectedCathegorySlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_CATEGORIES);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedCathegorySlot != null) {
			String choosencategoryString = selectedCathegorySlot.getResolutions().getResolutionsPerAuthority().get(0)
					.getValues().get(0).getValue().getName();
			input.getAttributesManager().setSessionAttributes(
					Collections.singletonMap(choosencategoryString, PhrasesAndConstants.LIST_OF_CATEGORIES));
			round.setCategory(Category.getCategory(choosencategoryString));
			speechText = round.getNumberOfPlayers() == 1 ? "Du hast " : "Ihr habt ";
			speechText += String.format("die Kategorie %s gewaehlt. ", round.getCategory().toString());
			Iterator<Player> it = round.getPlayerArrayList().iterator();
			while(it.hasNext()) {
				speechText += it.next().getName();
			}
			
			if (round.getCategory() == Category.GEDAECHTNISTRAINING) {
				round.setMemoryTraining(MemoryTraining.getRandomMemoryTraining());
				round.setNextRandomCurrentPlayer();
				
				switch(round.getMemoryTraining()) {
				case KOFFERPACKEN:
					if (round.getNumberOfPlayers() == 1) {
						String item = round.getRandomBackPackWordForAlexa();
						round.addItemToBackPack(item);
						speechText += String.format("%s %s.", PhrasesAndConstants.START_BACK_PACKING_ONE_PLAYER, round.getBackPackingAt(0));
					} else {
						speechText += String.format("%s %s startet.", PhrasesAndConstants.START_BACK_PACKING_MORE_PLAYER,
								round.getCurrentPlayer().getName());
					}
					break;
				case WORTDOMINO:
					speechText += "Jetzt spielst du Doppelwortkette " + round.getCurrentPlayer().getName();
					//TODO what happens when you play WortDomino
					break;
				default:
					break;
				
				}
			} else {
				speechText += PhrasesAndConstants.LIST_ALL_LEVELS;
			}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_CATEGORY)
					.withSpeech(PhrasesAndConstants.REPROMPT_CATEGORY);
		}
		return responseBuilder.withShouldEndSession(false).build();
	}

}
