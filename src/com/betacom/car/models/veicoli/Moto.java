package com.betacom.car.models.veicoli;

public class Moto {

	private int id;
    private int idVeicolo;
    private int numeroPorte;
    private int idTarga;
    private int cc;
	public Moto(int id, int idVeicolo, int numeroPorte, int idTarga, int cc) {
		super();
		this.id = id;
		this.idVeicolo = idVeicolo;
		this.numeroPorte = numeroPorte;
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
	public int getNumeroPorte() {
		return numeroPorte;
	}
	public void setNumeroPorte(int numeroPorte) {
		this.numeroPorte = numeroPorte;
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
		return "Moto [id=" + id + ", idVeicolo=" + idVeicolo + ", numeroPorte=" + numeroPorte + ", idTarga=" + idTarga
				+ ", cc=" + cc + "]";
	}
    
}
