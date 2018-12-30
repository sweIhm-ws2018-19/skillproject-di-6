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

public class AnswerHandler implements RequestHandler {

	private Gameround round;

	public AnswerHandler(Gameround round) {
		this.round = round;
	}

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(PhrasesAndConstants.INTENT_ANSWER));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Slot answerSlot = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots()
				.get(PhrasesAndConstants.LIST_OF_ANTWORT);
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (answerSlot != null) {
			// TODO aendern
			String antwort = answerSlot.getValue();
			input.getAttributesManager()
					.setSessionAttributes(Collections.singletonMap(antwort, PhrasesAndConstants.LIST_OF_ANTWORT));

			if (round.checkAnswer(antwort)) {
				speechText = PhrasesAndConstants.RIGHT_ANSWER;
			} else {
				speechText = PhrasesAndConstants.WRONG_ANSWER + round.getRightAnswer();
			}
			round.increaseQuestionsAsked();
			
			if(round.getQuestionsAsked() == round.getMaxQuestions()) {
				speechText += roundIsOver();
			}
			
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText);
		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.REPROMPT_ANSWER)
					.withSpeech(PhrasesAndConstants.REPROMPT_ANSWER);
		}
		return responseBuilder.withShouldEndSession(false).build();
	}
	
	private String roundIsOver() {
		//TODO what to do, when the round is over
		return null;
	}
	
}
