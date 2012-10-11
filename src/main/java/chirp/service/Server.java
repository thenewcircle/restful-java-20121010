package chirp.service;

import static com.google.inject.Guice.createInjector;
import static com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory.createHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.glassfish.grizzly.http.server.HttpServer;

import chirp.model.UserRepository;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.sun.jersey.api.core.ClasspathResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;

public class Server {

	private static final File userRepositoryFile = new File("user_repository.bin");

	public static void main(String[] args) throws IOException {
		// jersey must scan classpath for resources and providers
		ResourceConfig rc = new ClasspathResourceConfig();

		// instantiate singleton user repository for injection
		final UserRepository userRepository = thaw();
		Injector injector = createInjector(new AbstractModule() {
			protected void configure() {
				bind(UserRepository.class).toInstance(userRepository);
			}
		});

		// start server with guice injector
		HttpServer server = createHttpServer("http://localhost:8080", rc,
				new GuiceComponentProviderFactory(rc, injector));

		// wait for shutdown ...
		System.out.println("Hit <return> to stop server...");
		System.in.read();
		server.stop();

		// save state
		freeze(userRepository);
	}
	
	private static UserRepository thaw() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(userRepositoryFile));
			try {
				return (UserRepository) in.readObject();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			return new UserRepository();
		}
	}

	private static void freeze(UserRepository userRepository) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userRepositoryFile));
			try {
				out.writeObject(userRepository);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
