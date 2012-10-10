package chirp.model;

import static java.util.Collections.unmodifiableMap;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String realname;
	private final Map<Timestamp, Post> posts = new TreeMap<Timestamp, Post>();

	public User(String username, String realname) {
		this.username = username;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

	public Post createPost(String content) {
		Timestamp timestamp = new Timestamp();
		if (posts.containsKey(timestamp))
			throw new DuplicateEntityException();

		Post post = new Post(timestamp, content, this);
		posts.put(timestamp, post);
		return post;
	}

	public Map<Timestamp, Post> getPosts() {
		return unmodifiableMap(posts);
	}

	public Post getPost(Timestamp timestamp) {
		Post post = posts.get(timestamp);
		if (post == null)
			throw new NoSuchEntityException();

		return post;
	}

	public void deletePost(String timestamp) {
		if (posts.remove(timestamp) == null)
			throw new NoSuchEntityException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}

}
