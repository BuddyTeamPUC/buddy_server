package buddyserver.Server.Communication;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Materia;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class CreateSubjectRequest extends BaseRequest {

	int user_id;
	String nome;
	String descricao;
	
	///addmateria?user_id=xnome=Ac%20I&descricao=uma%20materia%20bem%20bleeh
	
	public CreateSubjectRequest(String requestString) 
	{
		super(requestString);
		String[] infoLines = requestString.split("\\?")[1].split("&");
		this.user_id = Integer.parseInt(infoLines[0].split("=")[1].replace("%20", " "));
		this.nome = infoLines[1].split("=")[1].replace("%20", " ");
		this.descricao = infoLines[2].split("=")[1].replace("%20", " ");
	
		this.nome = URLDecoder.decode(this.nome, StandardCharsets.UTF_8);
		this.descricao = URLDecoder.decode(this.descricao, StandardCharsets.UTF_8);
	}
	
	public CommunicationResult ProcessRequest() 
	{
		DAO dao = new DAO();
		try 
		{
			dao.Start(ConnectionSettings.GetDefault());
			
			Materia[] materias = dao.GetMaterias();
			int id = (materias != null && materias.length > 0) ? materias.length : 0;
			
			Materia newSubject = new Materia(id, nome, descricao, user_id);
			
			dao.Insert(newSubject);
			
			return new CommunicationResult(true, "Topic successfully added", newSubject.GetJson());
		}
		catch(Exception e) 
		{
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
