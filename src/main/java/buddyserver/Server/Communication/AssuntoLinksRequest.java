package buddyserver.Server.Communication;

import buddyserver.DB.ConnectionSettings;
import buddyserver.DB.DAO;
import buddyserver.DB.entities.Materiais_link;
import buddyserver.Server.Communication.Result.CommunicationResult;

public class AssuntoLinksRequest extends BaseRequest {

	int assuntoId;

	public AssuntoLinksRequest(String requestString) {
		super(requestString);

		this.assuntoId = Integer.parseInt(requestString.split("\\?")[1].split("=")[1]);
	}

	public CommunicationResult ProcessRequest() {
		DAO dao = new DAO();
		try {
			dao.Start(ConnectionSettings.GetDefault());

			Materiais_link[] assuntos = dao.GetAssuntosMaterial(assuntoId);

			String linksJson = "[]";

			if (assuntos != null && assuntos.length > 0) {
				linksJson = "[";

				for (int i = 0; i < assuntos.length; i++) {
					String comma = (i == assuntos.length - 1) ? "" : ",";
					linksJson += assuntos[i].GetJson() + comma;
				}

				linksJson += "]";
			}

			return new CommunicationResult(true, "Success: data fetched", linksJson);
		} catch (Exception e) {
			System.out.println("RegisterRequest: " + e.getMessage());
			return new CommunicationResult(false, e.getMessage(), "");
		}
	}
}
