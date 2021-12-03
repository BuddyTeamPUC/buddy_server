package buddyserver.Server.Communication;

import java.security.NoSuchAlgorithmException;

import buddyserver.DB.*;
import buddyserver.DB.entities.Estudante;
import buddyserver.Server.Communication.Result.CommunicationResult;
import buddyserver.utils.*;

public class RegisterRequest extends BaseRequest{

	private String name;
	private String lastName;
	private String email;
	private String pass;
	
	//EXPTECTED REQUEST LAYOUT: /register?name=a&sobrenome=a&email=a&pass=a
	
	public RegisterRequest(String requestString) throws NoSuchAlgorithmException {
		super(requestString);
		
		String[] infos = requestString.split("\\?")[1].split("&");
		name = infos[0].split("=")[1];
		lastName = infos[1].split("=")[1];
		email = infos[2].split("=")[1];
		pass =  Hash.Encode( infos[3].split("=")[1] );
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			 
			Estudante estudante = dao.GetEstudante(email);
			
			if(estudante != null) 
			{
				return new CommunicationResult(false, "Email already taken", "");
			}
			
			Estudante[] estudantes = dao.GetEstudantes();
			
			int id = estudantes != null && estudantes.length > 0 ? estudantes.length : 0;
			
			dao.Insert(new Estudante(id, name, lastName, pass, email));
			dao.Close();
			return new CommunicationResult(true, "Register process complete", "");
		}
		catch(Exception e) 
		{
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
