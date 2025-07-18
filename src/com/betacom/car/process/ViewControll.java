package com.betacom.car.process;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;
import com.betacom.car.view.BiciView;
import com.betacom.car.view.MacchinaView;
import com.betacom.car.view.MotoView;

public class ViewControll {

	private SQLManager db = new SQLManager(); 
	
	public List<MacchinaView> visualizzaMacchine() throws AcademyException {
		String qry= SQLConfiguration.getInstance().getQuery("select.macchina.view");
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new MacchinaView(
						(Integer) row.get("macchina_id"), 
						(Integer) row.get("numeroPorte"), 
						(Integer) row.get("cc"), 
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	}

	public List<MotoView> visualizzaMoto() throws AcademyException {
		String qry= SQLConfiguration.getInstance().getQuery("select.moto.view");
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new MotoView(
						(Integer) row.get("moto_id"), 
						(Integer) row.get("cc"), 
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	}
	
	public List<BiciView> visualizzaBici() throws AcademyException {
		String qry= SQLConfiguration.getInstance().getQuery("select.bici.view");
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new BiciView(
						(Integer) row.get("bici_id"), 
						(Integer) row.get("numeroMarce"), 
						(Boolean) row.get("pieghevole"),
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	}
	
	public List<BiciView> visualizzaBiciWhere(String k,String v) throws AcademyException {
		String qry= "SELECT * FROM bicidettagli where "+k+"='"+v+"'";
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new BiciView(
						(Integer) row.get("bici_id"), 
						(Integer) row.get("numeroMarce"), 
						(Boolean) row.get("pieghevole"),
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	} 
	public List<MotoView> visualizzaMotoWhere(String k,String v) throws AcademyException {
		String qry= "SELECT * FROM motodettagli where "+k+"='"+v+"'";
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new MotoView(
						(Integer) row.get("moto_id"), 
						(Integer) row.get("cc"), 
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	} 
	public List<MacchinaView> visualizzaMacchinaWhere(String k,String v) throws AcademyException {
		String qry= "SELECT * FROM bicidettagli where "+k+"='"+v+"'";
		List<Map<String, Object>> lD = db.list(qry);
		return lD.stream()
				.map(row -> new MacchinaView(
						(Integer) row.get("macchina_id"), 
						(Integer) row.get("numeroPorte"), 
						(Integer) row.get("cc"), 
						(String) row.get("modello"), 
						(Integer) row.get("numero_ruote"), 
						(String) row.get("targa_valore"), 
						(String) row.get("marca_nome"),
						(String) row.get("colore_nome"),
						(String) row.get("categoria_nome"),
						(String) row.get("alimentazione_nome"),
						(Integer) row.get("annoProduzione")))
				.collect(Collectors.toList());
	} 
}
