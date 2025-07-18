package com.betacom.car.process.controlli;

import java.util.Map;

import com.betacom.car.dao.MotoDAO;
import com.betacom.car.dao.TargaDAO;
import com.betacom.car.dao.VeicoloDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.process.StartVeicolo;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class ProcesMoto extends GenericControl {

	public final static Integer TARGA_MOTO = 8;
	public final static Integer CC_MOTO = 9;
	public final static Integer NUMERO_PARAMETRI_MOTO = 10;

	public boolean executeMoto(String[] str) throws AcademyException {
		try {
			if (ControlliMoto(str))
				return nuovaMoto(str);
			else
				return false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

	}

	private Boolean ControlliMoto(String[] str) throws AcademyException {
		if (str.length != NUMERO_PARAMETRI_MOTO)
			return false;
		if (!super.controlloTarga(str[TARGA_MOTO], "^[A-Za-z]{2}[0-9]{5}$")) {
			return false;
		}
		if (!super.controlloCc(Integer.parseInt(str[CC_MOTO]))) {
			return false;
		}
		if (super.controlloGenerale(str))
			return true;
		return false;
	}

	private Boolean nuovaMoto(String[] str) throws AcademyException {
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
			int targa = new TargaDAO().insert("insert.targa", "moto", str[TARGA_MOTO],idx);
			new MotoDAO().insert("insert.moto", new Object[] { idx, targa, str[CC_MOTO] });
			db.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			db.rollback();
			return false;
		}
		return true;

	}

	public void modificaMoto(Map<String, Object> map) throws Exception {
		System.out.println("midifica moto");
		String qryMacchina = "UPDATE Moto SET ";
		String qryVeicolo = "UPDATE Moto SET ";
		SQLManager db = new SQLManager();
		try {
			SQLConfiguration.getInstance().setTransaction();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getKey().equals("cc")) {
					qryMacchina += entry.getKey() + "='" + entry.getValue() + "' , ";
				} else if (entry.getKey().equals("targa")) {
					new TargaDAO().cambiaTarga(map.get("id").toString(), entry.getValue().toString(),"moto");
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
