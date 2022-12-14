package model;

public class Clientes {
	private Double idCliente;
	private String email;
	private String nome;
	private String cpf;
	private String telefone;
	private String senha;
	private String endereco;
	public Clientes(Double idCliente, String email, String nome, String cpf, String telefone, String senha, String endereco) {
		super();
		this.idCliente = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
		
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	

}
