package com.betacom.car.process;

import java.util.List;

import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.MacchinaDAO;
import com.betacom.car.dao.MotoDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.process.controlli.GenericControl;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.view.MacchinaView;
import com.betacom.car.view.MotoView;
import com.betacom.car.view.BiciView;


public class StartVeicolo {
	
	public final static Integer TIPO_VEICOLO = 0;
	public final static Integer NUMERO_RUOTE = 1;
	public final static Integer TIPO_ALIMENTAZIONE = 2;
	public final static Integer CATEGORIA = 3;
	public final static Integer COLORE = 4;
	public final static Integer MARCA = 5;
	public final static Integer ANNO_PRODUZIONE = 6;
	public final static Integer MODELLO = 7;

	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
		try {
			SQLConfiguration.getInstance().getConnection();
			for(String inp:params) {
				String[] oper = inp.split(":");
				switch(oper[0]) {
				case "add" :
					System.out.println("crea :"+oper[1]);
					creaVeicolo(oper[1]);
					break;
				case "update":
					System.out.println("modifica :"+oper[1]);
					modificaVeicolo(oper[1]);
					break;
				case "delete":
					System.out.println("elimina :"+oper[1]);
					elimaVeicolo(oper[1]);
					break;		
				}	
			}
			mostraVeicoli();
		} catch (Exception e) {
			System.out.println ("Error found:" + e.getMessage());
			rc = false;
		}
		try {
			SQLConfiguration.getInstance().closeConnection();
		} catch (AcademyException e) {
			System.out.println("Error in close connection:" + e.getMessage());
		}
		System.out.println("Connection is closed....");
		return rc;
	}
		
		
	private void elimaVeicolo(String s) throws Exception{
		String[] tmp = s.split("=");
		switch(tmp[TIPO_VEICOLO].substring(0,1).toUpperCase()+tmp[TIPO_VEICOLO].substring(1).toLowerCase()) {
		case "Macchina":
			System.out.println("cancello macchina id= "+tmp[1]);
			new MacchinaDAO().delete("delete.macchina", new Object[] {tmp[1]});
			break;
		case "Moto":
			System.out.println("cancello moto id= "+tmp[1]);
			new MotoDAO().delete("delete.moto",new Object[] {tmp[1]});
			break;
		case "Bici":
			System.out.println("cancello bici id= "+tmp[1]);
			new BiciDAO().delete("delete.bci", new Object[] {tmp[1]});
			break;
		}
	}

	private void creaVeicolo(String s) throws Exception {
		String[] tmp = s.split(",");
		switch(tmp[TIPO_VEICOLO].substring(0,1).toUpperCase()+tmp[TIPO_VEICOLO].substring(1).toLowerCase()) {
		case "Macchina":
			if(new com.betacom.car.process.controlli.ProcesMacchina().executeMacchina(tmp))
				System.out.println("nuova macchina creata");
			else 
				System.err.println("errore creazione macchina");
			break;
		case "Moto":
			System.out.println("creo nuova moto");
			if(new com.betacom.car.process.controlli.ProcesMoto().executeMoto(tmp))
				System.out.println("nuova moto creata");
			else 
				System.err.println("errore creazione moto");
			break;
		case "Bici":
			System.out.println("creo nuova bici");
			if(new com.betacom.car.process.controlli.ProcesBici().executeBici(tmp))
				System.out.println("nuova bici creata");
			else 
				System.err.println("errore creazione bici");
			break;
		}
	}
		
	private void modificaVeicolo(String s) throws Exception {
		String[] tmp = s.split(",");
		new GenericControl().updateVeicolo(tmp);
	}	
	
	public void mostraVeicoli() throws AcademyException {
		List<MacchinaView> lM = new ViewControll().visualizzaMacchine();
		lM.forEach(row -> System.out.println(row));
		List<MotoView> lMoto = new ViewControll().visualizzaMoto();
		lMoto.forEach(row -> System.out.println(row));
		List<BiciView> lB = new ViewControll().visualizzaBici();
		lB.forEach(row -> System.out.println(row));
		List<BiciView> lB2 = new ViewControll().visualizzaBiciWhere("numeroMarce","12");
		lB2.forEach(row -> System.out.println(row));
	}
}
