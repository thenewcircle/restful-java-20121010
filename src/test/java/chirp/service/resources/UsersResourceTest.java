package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import chirp.model.UserRepository;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UsersResourceTest extends ResourceTest {

	private final WebResource usersResource = resource().path("users");
	private final UserRepository userRepository = UserRepository.getInstance();

	@Test
	public void postToUsersMustCreateUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("username", "testuser");
		form.add("realname", "Test User");
		usersResource.post(form);

		assertEquals("Test User", userRepository.getUser("testuser").getRealname());
	}

}
