package buddyserver.Server.Communication;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Assunto;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class TopicsRequest extends BaseRequest{
	
	int materialId = 0;
	
	public TopicsRequest(String requestString) 
	{
		super(requestString);
		
		///assuntos?materialid=0
		
		this.materialId = Integer.parseInt(requestString.split("\\?")[1].split("=")[1]);
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			
			Assunto[] assuntos = dao.GetAssuntos(materialId);
			
			String materiasJson = "[]";
			
			if(assuntos != null && assuntos.length > 0)
			{
				materiasJson = "[";
			
				for(int i = 0; i < assuntos.length; i++) 
				{
					String comma = (i == assuntos.length - 1) ? "" : ",";
					materiasJson += assuntos[i].GetJson() +comma;
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
