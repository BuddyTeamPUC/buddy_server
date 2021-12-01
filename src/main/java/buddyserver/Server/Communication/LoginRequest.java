package buddyserver.Server.Communication;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Estudante;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class LoginRequest extends BaseRequest {
	
	String email;
	String pass;
	
	public LoginRequest(String requestString) 
	{
		super(requestString);
		String[] infos = requestString.split("\\?")[1].split("&");
		this.email = infos[0].split("=")[1];
		this.pass = infos[1].split("=")[1];
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			Estudante estudante = dao.GetEstudante(email, pass);
			
			if(estudante == null) 
			{
				return new CommunicationResult(false, "Login fail: There'is no user with such credentials", "");
			}
			
			return new CommunicationResult(true, "Login success: User fetched", estudante.GetJson());
		}
		catch(Exception e) 
		{
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
