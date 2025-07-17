package com.betacom.car.dao;

import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class BiciDAO {
	
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

}
