package com.betacom.car.dao;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.process.controlli.GenericControl;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class TargaDAO {

	private SQLManager db = new SQLManager();

	public int insert(String qryName, String string2, String string3, int idx) throws AcademyException {
		int num = 0;
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		int tipo = db.findId("TipoVeicolo", "nome", string2);
		num = db.update(qry, new Object[] { string3, tipo, idx }, true);
		return num;
	}

	public void cambiaTarga(String idVeicolo, String newTarga, String tipoVeicolo) throws AcademyException {
		String qry = "UPDATE Targa SET valore='" + newTarga + "'  where id_veicolo = " + idVeicolo;
		System.out.println(qry);
		if (tipoVeicolo.equals("macchina"))
			new GenericControl().controlloTarga(newTarga, "^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$");

		if (tipoVeicolo.equals("moto"))
			new GenericControl().controlloTarga(newTarga, "^[A-Za-z]{2}[0-9]{5}$");

		new SQLManager().executeQuery(qry);
	}

}
