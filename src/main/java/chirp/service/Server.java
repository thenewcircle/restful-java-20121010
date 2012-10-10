package chirp.service;

import static com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory.createHttpServer;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import chirp.model.UserRepository;

public class Server {

	public static void main(String[] args) throws IOException {
		HttpServer server = createHttpServer("http://localhost:8080");
		System.out.println("Hit <return> to stop server...");
		System.in.read();
		server.stop();
		UserRepository.getInstance().freeze();
	}

}
