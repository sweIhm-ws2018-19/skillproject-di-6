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

public class HighscoreHandler implements RequestHandler{
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HighscoreIntent"));
	}
	
	public Optional<Response> handle(HandlerInput input){
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		String speechText = "Dein Highscore muss erst noch in Gameround ermittelt werden.";
		responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		return responseBuilder.withShouldEndSession(false).build();
	}

}
