import buddyserver.Server.ServerObject;

public class Main {
public static void main(String[] args) {
		
		ServerObject server = new ServerObject(3000);
		try {
			server.Run();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}
}
