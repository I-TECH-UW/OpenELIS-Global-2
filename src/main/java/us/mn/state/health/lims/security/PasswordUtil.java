package us.mn.state.health.lims.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	private static int WORK = 12;

	public PasswordUtil() {
	}
	
	//salt-hash password for security
	public String hashPassword(String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(WORK));
		return hash;
	}

	//check password against salt-hashed password
	public boolean checkPassword(String password, String hash) {
		boolean match = BCrypt.checkpw(password, hash);
		return match;
	}
}
