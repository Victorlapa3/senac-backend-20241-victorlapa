package model.entity;

import java.time.LocalDate;

public class Pessoa {
	private String nome;
	private LocalDate dtNascimento;
	private char sexo;
	private String cpf;
	private String tipo;
	private int id;
	private Pais pais;
	
	public Pessoa() {
		super();
	}
	
	public Pessoa(String nome, LocalDate dtNascimento, char sexo, String cpf, String tipo, int id, Pais pais) {
		super();
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipo = tipo;
		this.id = id;
		this.pais = pais;
	}
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
