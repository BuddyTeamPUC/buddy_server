import buddyserver.Server.ServerObject;

public class Main {
	
public static void main(String[] args) {
	
		System.out.println("Lines:");
		for(int i = 0; i < args.length; i++) 
		{
			System.out.println(args[i]);
		}
		
		ServerObject server = null;
		
		if (ServerObject.isBuild) 
		{
			server = new ServerObject(Integer.parseInt(args[0]));
		}
		else 
		{
			server = new ServerObject(3000);
		}
		
		try {
			server.Run();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}
}
