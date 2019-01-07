package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import braingain.modell.Category;
import braingain.modell.Gameround;
import phrasesAndConstants.PhrasesAndConstants;

public class AskQuestionHandler implements RequestHandler {

	private Gameround round;

	public AskQuestionHandler(Gameround round) {
		this.round = round;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_ASK_QUESTION));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "No Questions available yet.";

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (round.isEverythingSet()) {
			round.buildQuestions();
			// TODO Everything is set, now start the questions
			
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);

		} else if (round.getNumberOfPlayers() == 0) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_NUMBER_OF_PLAYERS)
					.withSpeech(PhrasesAndConstants.SET_NUMBER_OF_PLAYERS);
		} else if (!round.allPlayerSet()) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_PLAYER_NAMES)
					.withSpeech(PhrasesAndConstants.SET_PLAYER_NAMES);
		} else if (round.getCategory() == null) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_CATEGORY)
					.withSpeech(PhrasesAndConstants.SET_CATEGORY);
		} else if (round.getLevel() == null /*&& round.getCategory() != Category.KOFFERPACKEN*/) {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SET_LEVEL)
					.withSpeech(PhrasesAndConstants.SET_LEVEL);
		} else {
			// TODO What to do, when nothing is given in the handlerinput
		}

		return responseBuilder.withShouldEndSession(false).build();
	}

}
