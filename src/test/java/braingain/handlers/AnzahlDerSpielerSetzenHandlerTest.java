package braingain.handlers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.Value;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import com.amazon.ask.response.ResponseBuilder;

import phrasesAndConstants.PhrasesAndConstants;

public class AnzahlDerSpielerSetzenHandlerTest {
//
//	private AnzahlDerSpielerSetzenHandler handler;
//	private LaunchRequestHandler lrh = new LaunchRequestHandler();
//
//	@Before
//	public void setup() {
//		handler = new AnzahlDerSpielerSetzenHandler(lrh);
//	}
//
//	@Test
//	public void testCanHandle() {
//		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
//		when(inputMock.matches(any())).thenReturn(true);
//		assertTrue(handler.canHandle(inputMock));
//	}
//
//	@Test
//	public void testHandle() {
//
//		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
//		final String numberOfPlayers = "3";
//		final Value val = Value.builder().withName(numberOfPlayers).withId(numberOfPlayers).build();
//		final ValueWrapper wrap = ValueWrapper.builder().withValue(val).build();
//
//		final List<ValueWrapper> list = new ArrayList<>();
//		list.add(wrap);
//
//		final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder().withRequest(IntentRequest.builder()
//				.withIntent(Intent.builder().putSlotsItem(PhrasesAndConstants.LIST_OF_PLAYERNUMBERS, Slot.builder()
//						.withName(PhrasesAndConstants.LIST_OF_PLAYERNUMBERS).withValue(numberOfPlayers)
//						.withResolutions(Resolutions.builder()
//								.addResolutionsPerAuthorityItem(Resolution.builder().withValues(list).build()).build())
//						.build()).build())
//				.build()).build();
//
//		final ResponseBuilder responseBuilder = Mockito.mock(ResponseBuilder.class);
//
//		when(inputMock.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
//		when(inputMock.getResponseBuilder()).thenReturn(responseBuilder);
//
//		assertNotNull(handler.handle(inputMock));
//	}

}
