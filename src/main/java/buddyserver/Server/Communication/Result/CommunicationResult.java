package buddyserver.Server.Communication.Result;

import buddyserver.utils.HttpRequestStringEncoder;

public class CommunicationResult {
	
	boolean status;
	String message;
	String data;
	
	public CommunicationResult(boolean status, String message, String data) 
	{
		this.status = status;
		this.message = message;
		this.data = data;

		System.out.println("Sending Result: ");
		System.out.println(data);
	}
	
	public boolean GetStatus() 
	{
		return status;
	}
	
	public String GetMessage() 
	{
		return message;
	}
	
	public String GetJson() 
	{
		String fetchedData = !data.isEmpty() ? data : "\"\"";
		return "{ \"status\": "+status+", \"message\":\""+message+"\", \"data\": "+ HttpRequestStringEncoder.Encode(fetchedData)+" }";
	}
}
