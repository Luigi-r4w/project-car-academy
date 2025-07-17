package com.betacom.car.models;

public class Targa {
	private int id;
    private String valore;
    private TipoVeicolo tipoVeicolo;
    public Targa(int id, String valore, TipoVeicolo tipoVeicolo) {
		super();
		this.id = id;
		this.valore = valore;
		this.tipoVeicolo = tipoVeicolo;
	}
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public TipoVeicolo getTipoVeicolo() {
		return tipoVeicolo;
	}

	public void setTipoVeicolo(TipoVeicolo tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}

	@Override
	public String toString() {
		return "Targa [id=" + id + ", valore=" + valore + ", tipoVeicolo=" + tipoVeicolo + "]";
	}

	public enum TipoVeicolo {
        Macchina,
        Moto
    }

	
}
