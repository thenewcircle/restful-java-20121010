package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class HelloResourceTest extends ResourceTest {

	@Test
	public void helloResourceMustSayHello() {
		String hello = resource().path("/").get(String.class);
		assertEquals("Hello!", hello);
	}

}
