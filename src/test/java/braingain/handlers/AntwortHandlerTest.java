package braingain.handlers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class AntwortHandlerTest {

	private AntwortHandler handler;
	private LaunchRequestHandler lrh = new LaunchRequestHandler();
	
	@Before
	public void setup() {
		handler = new AntwortHandler(lrh);
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));

	}

	@Test
	public void testHandle() {
	}

}
