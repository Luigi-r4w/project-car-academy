package com.betacom.car.dao;

import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class VeicoloDAO {

	private SQLManager db = new SQLManager();
	
	public Integer insert(String qryName, Object[] params) throws Exception{
		int num=0;
		String qry= SQLConfiguration.getInstance().getQuery(qryName);
		num=db.update(qry, params,true);
		return num;
	}
	
	public Integer update(String qryName, Object[] params) throws Exception{
		int num=0;
		String qry= SQLConfiguration.getInstance().getQuery(qryName);
		num=db.update(qry, params);
		return num;
	}
	
	public Integer delete(String qryName, Object[] params) throws Exception{
		int num=0;
		String qry= SQLConfiguration.getInstance().getQuery(qryName);
		num=db.update(qry, params);
		return num;
	}
	
	public boolean controlloAlimentazione(String s) throws Exception{
		return new SQLManager().exists("Alimentazione", "nome", s);
	}
	
	public boolean controlloCategoria(String s) throws Exception{
		return new SQLManager().exists("Categoria", "nome", s);
	}
	
	public boolean controlloColore(String s) throws Exception{
		return new SQLManager().exists("Colore", "nome", s);
	}
	
	public boolean controlloMarca(String s) throws Exception{
		return new SQLManager().exists("Marca", "nome", s);
	}
	
	public boolean controlloTarga(String s) throws Exception{
		return new SQLManager().exists("Targa", "valore", s);
	}
}
