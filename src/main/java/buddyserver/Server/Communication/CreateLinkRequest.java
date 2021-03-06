package buddyserver.Server.Communication;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Materiais_link;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class CreateLinkRequest extends BaseRequest {

	int assuntoId;
	String titulo;
	String link;

	public CreateLinkRequest(String requestString) {
		super(requestString);
		String[] infoLines = requestString.split("\\?")[1].split("&");
		this.assuntoId = Integer.parseInt(infoLines[0].split("=")[1].replace("%20", " "));
		this.titulo =  URLDecoder.decode( infoLines[1].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
		this.link = URLDecoder.decode( infoLines[2].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
	}

	public CommunicationResult ProcessRequest() {
		DAO dao = new DAO();
		try {
			dao.Start(ConnectionSettings.GetDefault());

			Materiais_link[] assuntos = dao.GetAssuntosMaterial();
			int id = (assuntos != null && assuntos.length > 0) ? assuntos.length : 0;

			Materiais_link newTopic = new Materiais_link(id, titulo, link, assuntoId);

			dao.Insert(newTopic);

			return new CommunicationResult(true, "Topic successfully added", newTopic.GetJson());
		} catch (Exception e) {
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
