package buddyserver.utils;

import java.util.Hashtable;

public class HttpRequestStringEncoder {

	static Hashtable<String, String> utf8Chars = new Hashtable<String, String>();
	
	public static String Encode(String s) 
	{
		String toReturn = s;
		
		for(int i = 0; i < toReturn.length(); i++) 
		{
			toReturn = toReturn.replace("�", "%c0");
			toReturn = toReturn.replace("�", "%c1");
			toReturn = toReturn.replace("�", "%c2");
			toReturn = toReturn.replace("�", "%c3");
			toReturn = toReturn.replace("�", "%e0");
			toReturn = toReturn.replace("�", "%e1");
			toReturn = toReturn.replace("�", "%e2");
			toReturn = toReturn.replace("�", "%e3");
			
			toReturn = toReturn.replace("�", "%C8");
			toReturn = toReturn.replace("�", "%c9");
			toReturn = toReturn.replace("�", "%ca");
			toReturn = toReturn.replace("�", "%e8");
			toReturn = toReturn.replace("�", "%e9");
			toReturn = toReturn.replace("�", "%ea");
			
			toReturn = toReturn.replace("�", "%cc");
			toReturn = toReturn.replace("�", "%cd");
			toReturn = toReturn.replace("�", "%ce");
			toReturn = toReturn.replace("�", "%ec");
			toReturn = toReturn.replace("�", "%ed");
			toReturn = toReturn.replace("�", "%ee");
			
			toReturn = toReturn.replace("�", "%d2");
			toReturn = toReturn.replace("�", "%d3");
			toReturn = toReturn.replace("�", "%d4");
			toReturn = toReturn.replace("�", "%d5");
			toReturn = toReturn.replace("�", "%f2");
			toReturn = toReturn.replace("�", "%f3");
			toReturn = toReturn.replace("�", "%f4");
			toReturn = toReturn.replace("�", "%f5");
			
			toReturn = toReturn.replace("�", "%d9");
			toReturn = toReturn.replace("�", "%da");
			toReturn = toReturn.replace("�", "%f9");
			toReturn = toReturn.replace("�", "%fb");

			toReturn = toReturn.replace("�", "%c7");
			toReturn = toReturn.replace("�", "%e7");
		}
		
		return toReturn;
	}
}
