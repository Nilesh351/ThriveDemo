package com.thrive.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.thrive.common.testdata.utils.DBConnection;
import com.thrive.ui.core.BaseTestPage;

public class DBUtils extends BaseTestPage {
	
	public static String getResultFromPostgresDB(String query) {

		Connection connection = null ;
		try {

			DBConnection dBConnectionDetails = new DBConnection();
			
			connection = connectPostgresqlDB(dBConnectionDetails.getDbDriver(), dBConnectionDetails.getDbServer(),
					dBConnectionDetails.getDbPort(),
					dBConnectionDetails.getDbName(), 
					dBConnectionDetails.getDbUsername(), dBConnectionDetails.getDbPassword());


			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception e) {
			return e.toString();
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
	private static Connection connectPostgresqlDB(String driverClass, String dbServer, int dbPort, String dbName, String dbUsername, String dbPassword) {

		try {
			Class.forName(driverClass);
			String url = "jdbc:postgresql://"+dbServer+":"+dbPort+"/"+dbName+"";
			Connection connection= DriverManager.getConnection(url, dbUsername, dbPassword);
			return connection;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static Table<String, String, String> getAllTranslationTableData() {

		String query = "select * from translation_new";
		
		Connection connection = null ;
		try {

			DBConnection dBConnectionDetails = new DBConnection();
			
			connection = connectPostgresqlDB(dBConnectionDetails.getDbDriver(), dBConnectionDetails.getDbServer(),
					dBConnectionDetails.getDbPort(),
					dBConnectionDetails.getDbName(), 
					dBConnectionDetails.getDbUsername(), dBConnectionDetails.getDbPassword());

			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			Table<String, String, String> translationTable = HashBasedTable.create();

			try {
				while(rs.next()){
					String paramId = rs.getString("param_id");
					String en = rs.getString("en");
					String fr = rs.getString("fr");
					  
					if (!paramId.isEmpty()) {
						if(en == null) {
							en = "";
						}
						if (fr == null){
							fr = "";
						}
						
						translationTable.put(paramId, en, fr);
					}
				
				 }
				
				return translationTable;
				
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}

		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return null;
	}

}
