package buddyserver.Server.Communication;

import buddyserver.utils.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Assunto;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class CreateTopicRequest extends BaseRequest {

	//Layout: materia_id=x&nome=y&descricao=z
	
	int materia_id;
	String nome;
	String descricao;
	String data;
	
	public CreateTopicRequest(String requestString) 
	{
		super(requestString);
		
		String[] infoLines = requestString.split("\\?")[1].split("&");
		this.materia_id = Integer.parseInt(infoLines[0].split("=")[1].replace("%20", " "));
		this.nome =  URLDecoder.decode( infoLines[1].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
		this.descricao = URLDecoder.decode( infoLines[2].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
		this.data = infoLines[3].split("=")[1];
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			
			Assunto[] assuntos = dao.GetAssuntos();
			int id = (assuntos != null && assuntos.length > 0) ? assuntos.length : 0;
			
			Assunto newTopic = new Assunto(id, nome, materia_id, 0, data, descricao);
			
			dao.Insert(newTopic);
			
			return new CommunicationResult(true, "Topic successfully added", newTopic.GetJson());
		}
		catch(Exception e) 
		{
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
