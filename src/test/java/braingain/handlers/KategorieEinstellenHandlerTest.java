package braingain.handlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import braingain.modell.Spielrunde;

class KategorieEinstellenHandlerTest {

	private KategorieEinstellenHandler handler = new KategorieEinstellenHandler(new Spielrunde());
	
	@Test
	void testCanHandle() {
		HandlerInput handlerInputMock = Mockito.mock(HandlerInput.class);
		Mockito.when(handlerInputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(handlerInputMock));
	}

	@Test
	void testHandle() {
		fail("Not yet implemented");
	}
}
