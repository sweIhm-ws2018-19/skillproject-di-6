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
		String speechText;
		Slot backPackSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_BACK_PACKING);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (backPackSlot != null) {
			String backPack = backPackSlot.getValue();
			// TODO now you have all values and need to iterate and find out what someone
			// wants to take and then test if all is correct and when so, add the last one
			// he said in round. super English
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, backPack).withSpeech(backPack);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND)
					.withSpeech(PhrasesAndConstants.REPROMPT_DONT_UNDERSTAND);
		}

		return responseBuilder.withShouldEndSession(false).build();
	}

}
