package com.betacom.car.models;

public class Alimentazione {
	private int id;
    private String nome;
	public Alimentazione(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	@Override
	public String toString() {
		return "Marca [id=" + id + ", nome=" + nome + "]";
	}
}
