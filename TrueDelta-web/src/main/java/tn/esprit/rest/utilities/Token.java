package tn.esprit.rest.utilities;

import java.util.Date;

import entities.User;

public class Token {
	String hash;
	java.util.Date expiresAt;
	User user;
	public Token() {
		
	}
	
	public Token(String hash, Date expiresAt, User user) {
		super();
		this.hash = hash;
		this.expiresAt = expiresAt;
		this.user = user;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public java.util.Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(java.util.Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Token of user n° "+user.getId_user()+" [hash=" + hash + ", expiresAt=" + expiresAt + "]";
	}
	
	
	
}
