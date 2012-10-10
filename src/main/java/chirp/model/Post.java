package chirp.model;

import java.io.Serializable;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Timestamp timestamp;
	private final String content;
	private final User user;

	public Post(Timestamp timestamp, String content, User user) {
		this.timestamp = timestamp;
		this.content = content;
		this.user = user;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Post other = (Post) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
