package chirp.model;

import static java.util.Collections.unmodifiableCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class UserRepository {

	private static UserRepository instance;

	private static final File file = new File("state.bin");
	private final Map<String, User> users;

	private UserRepository() {
		users = thaw();
	}

	public static UserRepository getInstance() {
		if (instance == null)
			instance = new UserRepository();
		return instance;
	}

	public User createUser(String username, String realname) {
		if (users.containsKey(username))
			throw new DuplicateEntityException();

		User user = new User(username, realname);
		users.put(username, user);
		return user;
	}

	public Collection<User> getUsers() {
		return unmodifiableCollection(users.values());
	}

	public User getUser(String username) {
		User user = users.get(username);
		if (user == null)
			throw new NoSuchEntityException();

		return user;
	}

	public void deleteUser(String username) {
		if (users.remove(username) == null)
			throw new NoSuchEntityException();
	}

	@SuppressWarnings("unchecked")
	private static Map<String, User> thaw() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			try {
				return (Map<String, User>) in.readObject();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			return new TreeMap<String, User>();
		}
	}

	public void freeze() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			try {
				out.writeObject(users);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
