package chirp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UserRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, User> users = new TreeMap<String, User>();
	private final List<Set<User>> bulkDeletions = new ArrayList<Set<User>>();

	public User createUser(String username, String realname) {
		if (users.containsKey(username))
			throw new DuplicateEntityException();

		User user = new User(username, realname);
		users.put(username, user);
		return user;
	}

	public Collection<User> getUsers() {
		return new ArrayList<User>(users.values());
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

	public int createBulkDeletion() {
		bulkDeletions.add(new HashSet<User>());
		return bulkDeletions.size() - 1;
	}

	public void addToBulkDeletion(int id, String username) {
		try {
			bulkDeletions.get(id).add(getUser(username));
		} catch (Exception e) {
			throw new NoSuchEntityException();
		}
	}

	public void cancelBulkDeletion(int id) {
		try {
			bulkDeletions.set(id, null);
		} catch (Exception e) {
			throw new NoSuchEntityException();
		}
	}

	public boolean commitBulkDeletion(int id) {
		try {
			Set<User> bulkDeletion = bulkDeletions.get(id);
			for (User user : bulkDeletion) {
				if (!users.containsValue(user)) {
					return false;
				}
			}
			for (User user : bulkDeletion) {
				users.remove(user.getUsername());
			}
			bulkDeletions.set(id, null);
			return true;
		} catch (Exception e) {
			throw new NoSuchEntityException();
		}
	}
}
