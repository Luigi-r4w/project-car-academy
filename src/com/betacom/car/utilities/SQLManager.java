package com.betacom.car.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singletone.SQLConfiguration;


public class SQLManager {

	public Connection getConnection() throws AcademyException{
		Connection con = null;	
		try {	
			Class.forName(SQLConfiguration.getInstance().getProperty("driver"));
			con = DriverManager.getConnection(
					SQLConfiguration.getInstance().getProperty("url"),
					SQLConfiguration.getInstance().getProperty("user"),
					SQLConfiguration.getInstance().getProperty("pwd")
					);		
			return con;
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public void commit() throws AcademyException{
		try {
			SQLConfiguration.getInstance().getConnection().commit();
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public void rollback() throws AcademyException{
		try {
			SQLConfiguration.getInstance().getConnection().rollback();  
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	private PreparedStatement createSet(PreparedStatement cmd, Object[] params) {
		int pIdx = 1;
		
		for (Object o:params) {
			try {
				cmd.setObject(pIdx++, o);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cmd;
	}
	
	private List<Map<String, Object>> resultsetToList(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();   
		int columns = md.getColumnCount();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		while(rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i=1;i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));													   //               value = reultset value
			}
			rows.add(row);
		}
		return rows;	
	}
	
	private Map<String, Object> resultsetToMap(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		if (!rs.next())
			return null;
		Map<String, Object> row = new HashMap<String, Object>();
		for (int i=1;i <= columns; ++i) {
			row.put(md.getColumnName(i), rs.getObject(i));
		}
		return row;	
	}
	
/***********************+++++++*+++++++++++++++++++++++++++++++++****************+**************************/
	
	public boolean exists(String tableName, String campo, String valore) throws AcademyException {
        String qry = "select 1 from " + tableName + " where "+campo+" = ?";
        try {
        	PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
        	cmd=createSet(cmd, new Object[] {valore});
        	ResultSet rs = cmd.executeQuery();
        	System.out.println(cmd);
            return rs.next();
        } catch (SQLException | AcademyException e) {
            throw new AcademyException(e.getMessage());
        } 
    }
	
	public Integer findId(String tableName, String campo, String valore) throws AcademyException {
        String qry = "select id from " + tableName + " where "+campo+" = ?";
        try {
        	PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
        	cmd=createSet(cmd, new Object[] {valore});
        	System.out.println(cmd);
        	ResultSet rs = cmd.executeQuery();
        	rs.next();
            return rs.getInt(1);
        } catch (SQLException | AcademyException e) {
            throw new AcademyException(e.getMessage());
        } 
    }
	
	public void executeQuery(String qry) throws AcademyException {
		try {
        	Connection con = SQLConfiguration.getInstance().getConnection();
        	Statement stm = con.createStatement();
        	stm.executeUpdate(qry);
        	
        } catch (SQLException | AcademyException e) {
            throw new AcademyException(e.getMessage());
        } 
	}
	
	/****************************************************************************************************************+*/

	public int update(String qry, Object[] params) throws AcademyException{
		return update(qry, params, false);
	}
	
	public int update(String qry, Object[] params, boolean viewPK) throws AcademyException{
		int rc = 0;
		try {
			PreparedStatement cmd = null;
			if (viewPK)
				cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry,
						Statement.RETURN_GENERATED_KEYS);
			else
				cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
			cmd = createSet(cmd, params);
			System.out.println(cmd);
			rc = cmd.executeUpdate();	
			if (viewPK) {
				try(ResultSet generatedKeys = cmd.getGeneratedKeys()){
					if (generatedKeys.next()) {
						rc = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Problem with generated key, no iD obtained");
					}
				}
			}	
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		return rc;
		
	}
	
	public Long count(String qry, Object[] params) throws AcademyException{
		try {
			String qryCount = "select count(*) as numero from ( " + qry + " ) as numero";
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qryCount);
			cmd = createSet(cmd, params);
			ResultSet res = cmd.executeQuery();
			res.next();
			return (Long) res.getObject("numero");	
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
		
	public List<String> listOfTable(String dbName) throws AcademyException {
		List<String> lT = new ArrayList<String>();
		try {
			DatabaseMetaData dbMD = SQLConfiguration.getInstance().getConnection().getMetaData();
			ResultSet res = dbMD.getTables(dbName, null, null, null);
			while (res.next()) {
				lT.add(res.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
		return lT;
	}
	
	public List<Map<String, Object>> list(String qry) throws AcademyException{
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
			ResultSet res = cmd.executeQuery();
			return resultsetToList(res);
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		
	}

	public List<Map<String, Object>> list(String qry, Object[] params) throws AcademyException{
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
			cmd = createSet(cmd, params);
			ResultSet res = cmd.executeQuery();
			return resultsetToList(res);
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public Map<String, Object> get(String qry, Object[] params) throws AcademyException{
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);
			cmd = createSet(cmd, params);
			ResultSet res = cmd.executeQuery();
			return resultsetToMap(res);
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
}
