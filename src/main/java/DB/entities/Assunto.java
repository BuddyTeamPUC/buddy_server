package DB.entities;

public class Assunto extends BaseEntity {
	
	String nome;
	int materia_id;
	int horasEstudadas;
	String lembrete;
	String descricao;
	
	public Assunto(int id, String nome, int materia_id, int horasEstudadas, String lembrete, String descricao) 
	{
		super(id);
		this.nome = nome;
		this.materia_id = materia_id;
		this.horasEstudadas = horasEstudadas;
		this.lembrete = lembrete;
		this.descricao = descricao;
	}
	
	public String GetTable()
	{
		return "assunto";
	}

	public int GetId() 
	{
		return id;
	}
	
	public String GetNome() 
	{
		return nome;
	}
	
	public int GetMateria_id() 
	{
		return materia_id;
	}
	
	public int GetHorasEstudadas() 
	{
		return horasEstudadas;
	}
	
	public String GetLembrete() 
	{
		return lembrete;
	}
	
	public void Imprimir() 
	{
		System.out.println("[EVENTO]: " + id + " " + nome + " " + materia_id + " " + horasEstudadas + " " + lembrete);
	}
	
	public String Insert() 
	{
		return "INSERT INTO "+GetTable()+"(id, nome, materia_guid, horas_estudadas, lembrete, descricao) VALUES ("+id+", '"+nome+"', "+materia_id+", "+horasEstudadas+", '"+lembrete+"', '"+descricao+"')";
	}
	
	public String GetJson() 
	{
		return "{ \"id\": "+id+", \"nome\": \""+nome+"\", \"materia_guid\": "+materia_id+", \"data\": \""+lembrete+"\", \"desricao\": \""+descricao+"\" }";
	}
}
