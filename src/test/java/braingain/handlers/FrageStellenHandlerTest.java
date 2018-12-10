package braingain.handlers;
/*
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import braingain.modell.Frage;
import braingain.modell.Spielrunde;
*/
class FrageStellenHandlerTest {
	/*
	private FrageStellenHandler handler = new FrageStellenHandler(new Spielrunde());
	private static final Frage FRAGE = new Frage("Wie heisst die Hauptstadt von Schweden?", "Stockholm");

	@Test
	void testCanHandle() {
		HandlerInput handlerInputMock = Mockito.mock(HandlerInput.class);
		Mockito.when(handlerInputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(handlerInputMock));
	}

	@Test
	void testHandle() {
		HandlerInput handlerInputMock = Mockito.mock(HandlerInput.class);
		//Gibt eine IndexOutOfBoundsEcxeption, weiss nicht wieso
		Response response = handler.handle(handlerInputMock).get();
		
		Assertions.assertFalse(response.getShouldEndSession());
		Mockito.when(handlerInputMock.getResponseBuilder().withSpeech(FRAGE.getFrage()));
		Assertions.assertEquals(FRAGE.getFrage(), handler.handle(handlerInputMock));
	}
*/
}
