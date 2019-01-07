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

import braingain.modell.Category;
import braingain.modell.Gameround;
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
			/*if (round.getCategory() == Category.KOFFERPACKEN) {
				round.setNextRandomCurrentPlayer();
				if (round.getNumberOfPlayers() == 1) {
					String item = round.getRandomBackPackWordForAlexa();
					round.addItemToBackPack(item);
					speechText += String.format("%s %s.", PhrasesAndConstants.START_BACK_PACKING_ONE_PLAYER, round.getBackPackingAt(0));
				} else {
					speechText += String.format("%s %s startet.", PhrasesAndConstants.START_BACK_PACKING_MORE_PLAYER,
							round.getCurrentPlayer().getName());
				}
			} else {*/
				speechText += PhrasesAndConstants.LIST_ALL_LEVELS;
			//}
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_CATEGORY)
					.withSpeech(PhrasesAndConstants.REPROMPT_CATEGORY);
		}
		return responseBuilder.withShouldEndSession(false).build();
	}

}
