package com.thrive.common.testdata.utils;

import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.Config;

public class DBQueries {
	
	
	public static String getCategoryFromDBUsingKey(String bdKey) {
		if(Config.getTestPlatform().contains(TestPlatform.RM)) {
			if(Config.getLocalizationLanguage().contains("fr")) {
				return "select tn.fr from translation_new tn where tn.param_id ='"+bdKey+"'";
			} else {
				return "select tn.en from translation_new tn where tn.param_id ='"+bdKey+"'";
			}
		} else {
			return "select tn.en from translation_new tn where tn.param_id ='"+bdKey+"'";
		}
	}
	
	public static String getCategoryNameInFrench(String categoryName) {
		// Get category name in french using category name in english		
		return "select tn.fr from translation_new tn where tn.en like '%"+categoryName+"%'";
	}
	
	
	public static String getQueryForDBKey(String key) {
		if(Config.getLocalizationLanguage().contains("en")){
			return "select en from translation_new tn where tn.param_id = '"+key+"'";
		} else {
			return "select fr from translation_new tn where tn.param_id = '"+key+"'";
		}
	}
	
	public static String getEnterpriseIdbyName(String entName) {
		String query = "";
		return  DBUtils.getResultFromPostgresDB(query);
	}
	
	public static String getCategoryIdByName(String categoryName) {
		String query = "select c2.id  from category c2  where c2.\"name\" like '%"+categoryName+"'";
		return  DBUtils.getResultFromPostgresDB(query);
	}
	
	public static String getInternalCategoryIdByName(String categoryName) {
		String query = "select ic.id from internal_category ic where ic.name like '%"+categoryName.toLowerCase()+"%'";
		return DBUtils.getResultFromPostgresDB(query);
	}
	
	
	public static String getInternalExpertiseIdByName(String expertiseName) {
		String query = "select ie.id from internal_expertise ie where ie.name like '%"+expertiseName.toLowerCase()+"%'";
		return DBUtils.getResultFromPostgresDB(query);
	}
	
	public static String getExpertiseIdByName(String expertiseName) {
		String query = "select id from expertise ie where name like '%"+expertiseName+"%'";
		return DBUtils.getResultFromPostgresDB(query);
	}
	
	public static String getCompanyIdByName(String companyName) {
		String query = "select c2.id from companies c2 where c2.name = '"+companyName+"'";
		return  DBUtils.getResultFromPostgresDB(query);
	}
	
	
	


}
