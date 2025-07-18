package com.betacom.car.process.controlli;

import java.util.Map;

import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.VeicoloDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.process.StartVeicolo;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class ProcesBici extends GenericControl {

	public final static Integer NUMERO_MARCE = 8;
	public final static Integer TIPO_SOSPENSIONI = 9;
	public final static Integer PIEGHEVOLE = 10;
	public final static Integer NUMERO_PARAMETRI_BICI = 11;

	public boolean executeBici(String[] str) throws NumberFormatException, AcademyException {
		try {
			if (controlliBici(str))
				return nuovaBici(str);
			else
				return false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	private Boolean nuovaBici(String[] str) throws AcademyException {
		SQLManager db = new SQLManager();
		VeicoloDAO daoV = new VeicoloDAO();
		try {
			SQLConfiguration.getInstance().setTransaction();
			int idx = daoV.insert("insert.veicolo",
					new Object[] { db.findId("TipoVeicolo", "nome", str[StartVeicolo.TIPO_VEICOLO]),
							str[StartVeicolo.MODELLO], db.findId("Marca", "nome", str[StartVeicolo.MARCA]),
							db.findId("Colore", "nome", str[StartVeicolo.COLORE]),
							db.findId("Categoria", "nome", str[StartVeicolo.CATEGORIA]),
							db.findId("Alimentazione", "nome", str[StartVeicolo.TIPO_ALIMENTAZIONE]),
							str[StartVeicolo.ANNO_PRODUZIONE], str[StartVeicolo.NUMERO_RUOTE] });
			int pieghevole = 0;
			if (str[PIEGHEVOLE].equals("true"))
				pieghevole = 1;
			new BiciDAO().insert("insert.bici", new Object[] { idx, str[NUMERO_MARCE],
					db.findId("Sospensione", "nome", str[TIPO_SOSPENSIONI]), pieghevole });
			db.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			db.rollback();
			return false;
		}
		return true;
	}

	private Boolean controlliBici(String[] str) throws NumberFormatException, AcademyException {
		if (str.length != NUMERO_PARAMETRI_BICI)
			return false;
		if (super.controlloGenerale(str))
			return true;
		return false;
	}

	public void modificaBici(Map<String, Object> map) throws Exception {
		System.out.println("midifica bici");
		String qryMacchina = "UPDATE Bici SET ";
		String qryVeicolo = "UPDATE Bici SET ";
		SQLManager db = new SQLManager();
		try {
			SQLConfiguration.getInstance().setTransaction();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getKey().equals("numeroMarce") || entry.getKey().equals("tipoSospensioni")
						|| entry.getKey().equals("pieghevole")) {
					if(entry.getKey().equals("tipoSospensioni")) {
						qryVeicolo += entry.getKey() + "=" + db.findId("Sospensione", "nome", entry.getValue().toString()) + " , ";
					} else {
						qryMacchina += entry.getKey() + "='" + entry.getValue() + "' , ";
					}
				} else {
					if (!entry.getKey().equals("id")) {
						if(entry.getKey().equals("marca")) {
							qryVeicolo += entry.getKey() + "=" + db.findId("Marca", "nome", entry.getValue().toString()) + " , ";
						} else if (entry.getKey().equals("colore")) {
							qryVeicolo += entry.getKey() + "=" + db.findId("Colore", "nome", entry.getValue().toString()) + " , ";
						} else if (entry.getKey().equals("categoria")) {
							qryVeicolo += entry.getKey() + "=" + db.findId("Categoria", "nome", entry.getValue().toString()) + " , ";
						} else if (entry.getKey().equals("alimentazione")) {
							qryVeicolo += entry.getKey() + "=" + db.findId("Alimentazione", "nome", entry.getValue().toString()) + " , ";
						} else {
							qryVeicolo += entry.getKey() + "='" + entry.getValue() + "' , ";
						}
					}
				}
			}
			if (qryMacchina.endsWith(", ")) {
				qryMacchina = qryMacchina.substring(0, qryMacchina.length() - 2);
			}
			if (qryVeicolo.endsWith(", ")) {
				qryVeicolo = qryVeicolo.substring(0, qryVeicolo.length() - 2);
			}
			qryVeicolo += " where id_veicolo = " + map.get("id");
			qryMacchina += " where id_veicolo = " + map.get("id");
			System.out.println(qryVeicolo + " & " + qryMacchina);
			new GenericControl().update(qryMacchina, qryVeicolo);
			db.commit();

		} catch (Exception e) {
			db.rollback();
			throw new AcademyException(e.getMessage());
		}
	}

}