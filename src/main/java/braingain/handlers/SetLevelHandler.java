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
import braingain.modell.Level;
import phrasesAndConstants.PhrasesAndConstants;

public class SetLevelHandler implements RequestHandler {

	private Gameround round;

	public SetLevelHandler(Gameround round) {
		this.round = round;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_SET_LEVEL));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Slot selectedLevelSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_LEVEL);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (selectedLevelSlot != null) {
			String gewaehltesLevel = selectedLevelSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues()
					.get(0).getValue().getName();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(gewaehltesLevel, PhrasesAndConstants.LIST_OF_LEVEL));
			round.setLevel(Level.getLevel(gewaehltesLevel));

			speechText = round.getNumberOfPlayers() == 1
					? String.format("Du hast das Level %s gewaehlt. Dir ", round.getLevel().toString())
					: String.format("Ihr habt das Level %s gewaehlt. Euch ", round.getLevel().toString());
			speechText += String.format("werden nun %s Fragen gestellt. ", round.getMaxQuestions());
			speechText += PhrasesAndConstants.START;

			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);

		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_LEVEL)
					.withReprompt(PhrasesAndConstants.REPROMPT_LEVEL);
		}

		return responseBuilder.withShouldEndSession(false).build();
	}

}
