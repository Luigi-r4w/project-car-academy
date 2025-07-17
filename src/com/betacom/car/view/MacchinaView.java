package com.betacom.car.view;

public class MacchinaView {

    private Integer id;
    private Integer numeroPorte;
    private Integer cc;
    private String modello;
    private Integer numero_ruote;
    private String targa_valore;
    private String marca_nome;
    private String colore_nome;
    private String categoria_nome;
    private String alimentazione_nome;
    private Integer annoProduzione;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroPorte() {
		return numeroPorte;
	}
	public void setNumeroPorte(Integer numeroPorte) {
		this.numeroPorte = numeroPorte;
	}
	public Integer getCc() {
		return cc;
	}
	public void setCc(Integer cc) {
		this.cc = cc;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public Integer getNumero_ruote() {
		return numero_ruote;
	}
	public void setNumero_ruote(Integer numero_ruote) {
		this.numero_ruote = numero_ruote;
	}
	public String getTarga_valore() {
		return targa_valore;
	}
	public void setTarga_valore(String targa_valore) {
		this.targa_valore = targa_valore;
	}
	public String getMarca_nome() {
		return marca_nome;
	}
	public void setMarca_nome(String marca_nome) {
		this.marca_nome = marca_nome;
	}
	public String getColore_nome() {
		return colore_nome;
	}
	public void setColore_nome(String colore_nome) {
		this.colore_nome = colore_nome;
	}
	public String getCategoria_nome() {
		return categoria_nome;
	}
	public void setCategoria_nome(String categoria_nome) {
		this.categoria_nome = categoria_nome;
	}
	public String getAlimentazione_nome() {
		return alimentazione_nome;
	}
	public void setAlimentazione_nome(String alimentazione_nome) {
		this.alimentazione_nome = alimentazione_nome;
	}
	public Integer getAnnoProduzione() {
		return annoProduzione;
	}
	public void setAnnoProduzione(Integer annoProduzione) {
		this.annoProduzione = annoProduzione;
	}
	public MacchinaView(Integer id, Integer numeroPorte, Integer cc, String modello, Integer numero_ruote,
			String targa_valore, String marca_nome, String colore_nome, String categoria_nome,
			String alimentazione_nome, Integer annoProduzione) {
		super();
		this.id = id;
		this.numeroPorte = numeroPorte;
		this.cc = cc;
		this.modello = modello;
		this.numero_ruote = numero_ruote;
		this.targa_valore = targa_valore;
		this.marca_nome = marca_nome;
		this.colore_nome = colore_nome;
		this.categoria_nome = categoria_nome;
		this.alimentazione_nome = alimentazione_nome;
		this.annoProduzione = annoProduzione;
	}
	@Override
	public String toString() {
		return "MacchinaView [id=" + id + ", numeroPorte=" + numeroPorte + ", cc=" + cc + ", modello=" + modello
				+ ", numero_ruote=" + numero_ruote + ", targa_valore=" + targa_valore + ", marca_nome=" + marca_nome
				+ ", colore_nome=" + colore_nome + ", categoria_nome=" + categoria_nome + ", alimentazione_nome="
				+ alimentazione_nome + ", annoProduzione=" + annoProduzione + "]";
	}
    
}
