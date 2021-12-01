package buddyserver.Server.Communication;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Materia;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class MateriaisRequest extends BaseRequest{
	
	int userId;
	
	public MateriaisRequest(String requestString) 
	{
		super(requestString);
		this.userId = Integer.parseInt(requestString.split("\\?")[1].split("=")[1]);
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			
			Materia[] materias = dao.GetMaterias(userId);
			
			String materiasJson = "[]";
			
			if(materias != null && materias.length > 0)
			{
				materiasJson = "[";
			
				for(int i = 0; i < materias.length; i++) 
				{
					String comma = (i == materias.length - 1) ? "" : ",";
					materiasJson += materias[i].GetJson() +comma;
				}
				
				materiasJson += "]";
			}
			
			return new CommunicationResult(true, "Login success: User fetched", materiasJson);
		}
		catch(Exception e) 
		{
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
