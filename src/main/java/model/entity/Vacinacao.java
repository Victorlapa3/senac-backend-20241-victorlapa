package model.entity;

import java.time.LocalDate;

public class Vacinacao {
	private int id;
	private int idPessoa;
	private String tipo;
	private LocalDate dataAplicacao;
	private int avaliacao;
	
	public Vacinacao() {
		
	}
	public Vacinacao(int id, int idPessoa, String tipo, LocalDate dataAplicacao, int avaliacao) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.tipo = tipo;
		this.dataAplicacao = dataAplicacao;
		this.avaliacao = avaliacao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
}
