package buddyserver.Server.Communication;

import buddyserver.Server.Communication.Result.CommunicationResult;

public abstract class BaseRequest {
	
	String requestString;
	
	public BaseRequest(String requestString) 
	{
		this.requestString = requestString;
	}
	
	public abstract CommunicationResult ProcessRequest();
}
