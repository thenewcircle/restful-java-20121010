package chirp.service.resources;

import com.sun.jersey.api.core.ClasspathResourceConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.grizzly2.GrizzlyTestContainerFactory;

public class ResourceTest extends JerseyTest {

	public ResourceTest() {
		super(new GrizzlyTestContainerFactory());
	}

	@Override
	protected AppDescriptor configure() {
		return new LowLevelAppDescriptor.Builder(new ClasspathResourceConfig()).build();
	}

}
