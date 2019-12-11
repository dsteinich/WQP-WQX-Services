package gov.usgs.cida.wqp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class WqpExceptionTest {

	public static class Klass {
		public void doSomething() throws WqpException {
			throw new WqpException(WqpExceptionId.URL_PARSING_EXCEPTION, Klass.class, "doSomething", "div by zero");
		}
	}

	@Test
	public void test() {
		WqpException ex = null;
		String trace = null;
		try {
			new Klass().doSomething();
			fail("test should throw exception");
		} catch (WqpException e) {
			ex = e;
			trace = e.traceBack();
		}

		assertNotNull(trace);
		System.out.println(trace);
		assertTrue(trace.contains("doSomething"));
		assertTrue(trace.contains("div by zero"));

		assertEquals(trace, ex.getMessage());
		assertEquals(trace, ex.toString());

		assertEquals("doSomething", ex.getMethod());
		assertEquals("div by zero", ex.getMessageOnly());
		assertEquals(Klass.class.getName(), ex.getClassname());

		assertEquals(null, ex.getPrevious());
	}

}
