package buddyserver.utils;

import java.util.Hashtable;

public class HttpRequestStringEncoder {

	static Hashtable<String, String> utf8Chars = new Hashtable<String, String>();
	
	public static String Encode(String s) 
	{
		String toReturn = s;
		
		for(int i = 0; i < toReturn.length(); i++) 
		{
			toReturn = toReturn.replace("À", "%c0");
			toReturn = toReturn.replace("Á", "%c1");
			toReturn = toReturn.replace("Â", "%c2");
			toReturn = toReturn.replace("Ã", "%c3");
			toReturn = toReturn.replace("à", "%e0");
			toReturn = toReturn.replace("á", "%e1");
			toReturn = toReturn.replace("â", "%e2");
			toReturn = toReturn.replace("ã", "%e3");
			
			toReturn = toReturn.replace("È", "%C8");
			toReturn = toReturn.replace("É", "%c9");
			toReturn = toReturn.replace("Ê", "%ca");
			toReturn = toReturn.replace("è", "%e8");
			toReturn = toReturn.replace("é", "%e9");
			toReturn = toReturn.replace("ê", "%ea");
			
			toReturn = toReturn.replace("Ì", "%cc");
			toReturn = toReturn.replace("Í", "%cd");
			toReturn = toReturn.replace("Î", "%ce");
			toReturn = toReturn.replace("ì", "%ec");
			toReturn = toReturn.replace("í", "%ed");
			toReturn = toReturn.replace("î", "%ee");
			
			toReturn = toReturn.replace("Ò", "%d2");
			toReturn = toReturn.replace("Ó", "%d3");
			toReturn = toReturn.replace("Ô", "%d4");
			toReturn = toReturn.replace("Õ", "%d5");
			toReturn = toReturn.replace("ò", "%f2");
			toReturn = toReturn.replace("ó", "%f3");
			toReturn = toReturn.replace("ô", "%f4");
			toReturn = toReturn.replace("õ", "%f5");
			
			toReturn = toReturn.replace("Ù", "%d9");
			toReturn = toReturn.replace("Ú", "%da");
			toReturn = toReturn.replace("ù", "%f9");
			toReturn = toReturn.replace("ú", "%fb");

			toReturn = toReturn.replace("Ç", "%c7");
			toReturn = toReturn.replace("ç", "%e7");
		}
		
		return toReturn;
	}
}
