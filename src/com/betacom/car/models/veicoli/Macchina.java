package com.betacom.car.models.veicoli;

public class Macchina {
	private int id;
    private int idVeicolo;
    private int idTarga;
    private int cc;
	public Macchina(int id, int idVeicolo, int idTarga, int cc) {
		super();
		this.id = id;
		this.idVeicolo = idVeicolo;
		this.idTarga = idTarga;
		this.cc = cc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdVeicolo() {
		return idVeicolo;
	}
	public void setIdVeicolo(int idVeicolo) {
		this.idVeicolo = idVeicolo;
	}
	public int getIdTarga() {
		return idTarga;
	}
	public void setIdTarga(int idTarga) {
		this.idTarga = idTarga;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	@Override
	public String toString() {
		return "Macchina [id=" + id + ", idVeicolo=" + idVeicolo + ", idTarga=" + idTarga + ", cc=" + cc + "]";
	}
}
