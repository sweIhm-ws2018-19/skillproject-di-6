package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import phrasesAndConstants.PhrasesAndConstants;

public class FallbackIntentHandler implements RequestHandler {

	public FallbackIntentHandler() {
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.FallbackIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
		return input.getResponseBuilder().withSpeech(speechText)
				.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).build();
	}

}
