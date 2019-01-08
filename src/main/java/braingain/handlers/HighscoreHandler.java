package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Gameround;
import phrasesAndConstants.PhrasesAndConstants;

public class HighscoreHandler implements RequestHandler{
	
	private Gameround round;
	
	public HighscoreHandler(Gameround gameround) {
		round = gameround;
	}
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HighscoreIntent"));
	}
	
	public Optional<Response> handle(HandlerInput input){
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		String speechText = round.getHighscore();
		responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		return responseBuilder.withShouldEndSession(false).build();
	}

}
