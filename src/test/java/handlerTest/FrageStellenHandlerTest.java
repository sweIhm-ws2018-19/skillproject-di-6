package handlerTest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.model.slu.entityresolution.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import braingain.handlers.FrageStellenHandler;
import braingain.modell.Frage;
import braingain.modell.Spielrunde;

class FrageStellenHandlerTest {

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
		Mockito.when(handlerInputMock.matches(any())).thenReturn(FRAGE.getFrage());
		Assertions.assertEquals(FRAGE.getFrage(), handler.handle(handlerInputMock));
	}

}
