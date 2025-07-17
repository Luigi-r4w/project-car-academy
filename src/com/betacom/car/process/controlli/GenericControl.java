package com.betacom.car.process.controlli;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.betacom.car.dao.VeicoloDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.process.StartVeicolo;
import com.betacom.car.utilities.SQLManager;

public class GenericControl {

	public Boolean controlloGenerale(String[] s) throws NumberFormatException, AcademyException {
		System.out.println("	controlloGenerale");
		if (controlloRuote(Integer.parseInt(s[StartVeicolo.NUMERO_RUOTE])))
			if (controlloAnno(Integer.parseInt(s[StartVeicolo.ANNO_PRODUZIONE])))
				if (controlloAlimentazione(s[StartVeicolo.TIPO_ALIMENTAZIONE]))
					if (controlloCategoria(s[StartVeicolo.CATEGORIA]))
						if (controlloColore(s[StartVeicolo.COLORE]))
							return true;

		return false;
	}

	public Boolean controlloTarga(String targa, String regex) throws AcademyException {
		System.out.println("	controllo targa");
		System.out.println(targa + " " + regex);
		if (!targa.matches(regex))
			throw new AcademyException("formato targa non valida");
		try {
			return !new VeicoloDAO().controlloTarga(targa);
		} catch (Exception e) {
			throw new AcademyException("targa non valida");
		}
	}

	private Boolean controlloRuote(Integer numeroR) throws AcademyException {
		System.out.println("		controllo ruote");
		if (numeroR <= 0)
			throw new AcademyException("numero ruote non valido");
		return true;
	}

	private Boolean controlloAnno(Integer annoP) throws AcademyException {
		System.out.println("		controllo anno");
		if (annoP > LocalDate.now().getYear())
			throw new AcademyException("anno non valido");
		return true;
	}

	protected Boolean controlloCc(Integer Cc) throws AcademyException {
		System.out.println("	controllo cc");
		if (Cc < 0)
			throw new AcademyException("cilindrata non valida");
		return true;
	}

	private Boolean controlloAlimentazione(String tipoA) throws AcademyException {
		System.out.println("		controllo tipoAlimentazione");
		try {
			if (!new VeicoloDAO().controlloAlimentazione(tipoA))
				throw new AcademyException("tipoAlimentazione non valido");
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		return true;
	}

	private Boolean controlloColore(String colore) throws AcademyException {
		System.out.println("		controllo colore");
		try {
			if (!new VeicoloDAO().controlloColore(colore))
				throw new AcademyException("colore non valido");
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		return true;
	}

	private Boolean controlloCategoria(String cat) throws AcademyException {
		System.out.println("		controllo categoria");
		try {
			if (!new VeicoloDAO().controlloCategoria(cat))
				throw new AcademyException("categoria non valido");
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		return true;
	}

	public Boolean updateVeicolo(String[] aS) {
		try {
			String id = null;
			for (String s : aS) {
				String[] value = s.split("=");
				if (value[0].trim().equals("id")) {
					id = value[1];
				}
			}
			if (new SQLManager().exists("Macchina", "id_veicolo", id)) {
				new ProcesMacchina().modificaMacchina(stringToMap(aS));
			} else if (new SQLManager().exists("Moto", "id_veicolo", id)) {
				new ProcesMoto().modificaMoto(stringToMap(aS));
			} else if (new SQLManager().exists("Bici", "id_veicolo", id)) {
				new ProcesBici().modificaBici(stringToMap(aS));
			} else {
				throw new AcademyException("veicolo non trovato");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;

	}

	public static Map<String, Object> stringToMap(String[] s) {
		Map<String, Object> map = new HashMap<>();
		for (String str : s) {
			String[] keyValue = str.split("=");
			if (keyValue.length == 2) {
				map.put(keyValue[0].trim(), keyValue[1].trim());
			}
		}
		System.out.println(map);
		return map;

	}

	public void update(String qryMacchina, String qryVeicolo) throws Exception {
		SQLManager db = new SQLManager();
		db.executeQuery(qryMacchina);
		db.executeQuery(qryVeicolo);

	}
}
