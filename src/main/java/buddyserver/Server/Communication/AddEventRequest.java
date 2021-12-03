package buddyserver.Server.Communication;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Evento;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class AddEventRequest extends BaseRequest {

	int user_id;
	String nome;
	String descricao;
	String data;

	public AddEventRequest(String requestString) {
		super(requestString);

		String[] infoLines = requestString.split("\\?")[1].split("&");
		this.user_id = Integer.parseInt(infoLines[0].split("=")[1].replace("%20", " "));
		this.nome = URLDecoder.decode(infoLines[1].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
		this.descricao = URLDecoder.decode(infoLines[2].split("=")[1].replace("%20", " "), StandardCharsets.UTF_8);
		this.data = infoLines[3].split("=")[1];
	}

	public CommunicationResult ProcessRequest() {
		DAO dao = new DAO();
		try {
			dao.Start(ConnectionSettings.GetDefault());

			Evento[] eventos = dao.GetEventos();
			int id = (eventos != null && eventos.length > 0) ? eventos.length : 0;

			Evento newEvento = new Evento(id, nome, data, descricao);

			dao.Insert(newEvento);

			return new CommunicationResult(true, "Topic successfully added", newEvento.GetJson());
		} catch (Exception e) {
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
