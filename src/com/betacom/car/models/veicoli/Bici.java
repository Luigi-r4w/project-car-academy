package com.betacom.car.models.veicoli;

public class Bici {
	private int id;
    private int idVeicolo;
    private int numeroMarce;
    private int tipoSospensioni;
    private boolean pieghevole;
	public Bici(int id, int idVeicolo, int numeroMarce, int tipoSospensioni, boolean pieghevole) {
		super();
		this.id = id;
		this.idVeicolo = idVeicolo;
		this.numeroMarce = numeroMarce;
		this.tipoSospensioni = tipoSospensioni;
		this.pieghevole = pieghevole;
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
	public int getNumeroMarce() {
		return numeroMarce;
	}
	public void setNumeroMarce(int numeroMarce) {
		this.numeroMarce = numeroMarce;
	}
	public int getTipoSospensioni() {
		return tipoSospensioni;
	}
	public void setTipoSospensioni(int tipoSospensioni) {
		this.tipoSospensioni = tipoSospensioni;
	}
	public boolean isPieghevole() {
		return pieghevole;
	}
	public void setPieghevole(boolean pieghevole) {
		this.pieghevole = pieghevole;
	}
	@Override
	public String toString() {
		return "Bici [id=" + id + ", idVeicolo=" + idVeicolo + ", numeroMarce=" + numeroMarce + ", tipoSospensioni="
				+ tipoSospensioni + ", pieghevole=" + pieghevole + "]";
	}
}
