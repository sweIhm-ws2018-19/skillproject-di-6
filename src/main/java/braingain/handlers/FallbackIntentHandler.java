package braingain.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import braingain.modell.Spielrunde;

// 2018-July-09: AMAZON.FallackIntent is only currently available in en-US locale.
//              This handler will not be triggered except in that locale, so it can be
//              safely deployed for any locale.
public class FallbackIntentHandler implements RequestHandler {
	
	private Spielrunde sr;
	
	public FallbackIntentHandler(Spielrunde sr) {
		this.sr = sr;
	}

	public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("ColorSession", speechText)
                .withReprompt(speechText)
                .build();
    }

}
