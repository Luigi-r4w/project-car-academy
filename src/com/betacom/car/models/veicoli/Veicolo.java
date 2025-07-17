package com.betacom.car.models.veicoli;

import java.time.LocalDateTime;

public class Veicolo {
 
	private int idVeicolo;
    private int tipoVeicolo;
    private String modello;
    private int marca;
    private int colore;
    private int categoria;
    private int tipoAlimentazione;
    private int annoProduzione;
    private int numeroRuote;
    private LocalDateTime dataInserimento;
    private LocalDateTime dataUltimaModifica;
	public Veicolo(int idVeicolo, int tipoVeicolo, String modello, int marca, int colore, int categoria,
			int tipoAlimentazione, int annoProduzione, int numeroRuote, LocalDateTime dataInserimento,
			LocalDateTime dataUltimaModifica) {
		super();
		this.idVeicolo = idVeicolo;
		this.tipoVeicolo = tipoVeicolo;
		this.modello = modello;
		this.marca = marca;
		this.colore = colore;
		this.categoria = categoria;
		this.tipoAlimentazione = tipoAlimentazione;
		this.annoProduzione = annoProduzione;
		this.numeroRuote = numeroRuote;
		this.dataInserimento = dataInserimento;
		this.dataUltimaModifica = dataUltimaModifica;
	}
	public int getIdVeicolo() {
		return idVeicolo;
	}
	public void setIdVeicolo(int idVeicolo) {
		this.idVeicolo = idVeicolo;
	}
	public int getTipoVeicolo() {
		return tipoVeicolo;
	}
	public void setTipoVeicolo(int tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public int getColore() {
		return colore;
	}
	public void setColore(int colore) {
		this.colore = colore;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getTipoAlimentazione() {
		return tipoAlimentazione;
	}
	public void setTipoAlimentazione(int tipoAlimentazione) {
		this.tipoAlimentazione = tipoAlimentazione;
	}
	public int getAnnoProduzione() {
		return annoProduzione;
	}
	public void setAnnoProduzione(int annoProduzione) {
		this.annoProduzione = annoProduzione;
	}
	public int getNumeroRuote() {
		return numeroRuote;
	}
	public void setNumeroRuote(int numeroRuote) {
		this.numeroRuote = numeroRuote;
	}
	public LocalDateTime getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDateTime dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public LocalDateTime getDataUltimaModifica() {
		return dataUltimaModifica;
	}
	public void setDataUltimaModifica(LocalDateTime dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	@Override
	public String toString() {
		return "Veicolo [idVeicolo=" + idVeicolo + ", tipoVeicolo=" + tipoVeicolo + ", modello=" + modello + ", marca="
				+ marca + ", colore=" + colore + ", categoria=" + categoria + ", tipoAlimentazione=" + tipoAlimentazione
				+ ", annoProduzione=" + annoProduzione + ", numeroRuote=" + numeroRuote + ", dataInserimento="
				+ dataInserimento + ", dataUltimaModifica=" + dataUltimaModifica + "]";
	}
    
}
