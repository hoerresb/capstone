package edu.gymtrack.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.gymtrack.db.Factory;

public class Authentication {
	private final long AUTH_TIMEOUT = 60 * 60;	// 1 hour
	
	protected Map<String, String> userHashPairs;
	private Map<String, Long> authenticatedUsers = new HashMap<String, Long>();
	
	public Authentication(Map<String, String> userHashPairs){
		this.userHashPairs = userHashPairs;
	}
	
	public boolean authenticateUser(String username, String password){
		if(!userHashPairs.containsKey(username))
			return false;
		
		if(!getHashForPassword(password).equals(userHashPairs.get(username)))
			return false;
		
		authenticatedUsers.put(username, new Date().getTime());
		return true;
	}
	
	public boolean isUserAuthenticated(String username){
		if(isUserTimedOut(username))
			return false;
		
		if(authenticatedUsers.containsKey(username))
			return true;
		
		return false;
	}
	
	public void updateUserActive(String username){
		if(!authenticatedUsers.containsKey(username))
			return;
		
		authenticatedUsers.put(username, new Date().getTime());
	}
	
	public void updateUserList() throws SQLException{
		Authentication newAuth = Factory.createAuthentication();
		this.userHashPairs = newAuth.userHashPairs;
	}
	
	private boolean isUserTimedOut(String username){
		if(!authenticatedUsers.containsKey(username))
			return true;
		
		long tTimeout = authenticatedUsers.get(username) + AUTH_TIMEOUT;
		if(new Date().getTime() > tTimeout)
			return true;
		
		return false;
	}
	
	// from http://viralpatel.net/blogs/java-md5-hashing-salting-password/
	private String getHashForPassword(String password) {
		String md5 = null;
		if(null == password) return null;

		try {

			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			//Update input string in message digest
			digest.update(password.getBytes(), 0, password.length());

			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
    }
}
