package chirp.model;

import static java.util.Collections.unmodifiableCollection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class UserRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, User> users = new TreeMap<String, User>();

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

}
