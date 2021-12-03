package buddyserver.utils;

import java.security.*;
import java.math.*;

public class Hash {
	
	public static String Encode(String data) throws NoSuchAlgorithmException 
	{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(data.getBytes(), 0, data.length());
		return new BigInteger(1,m.digest()).toString(16);
	}
}
