package com.thrive.api.data_utils;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class DBQueries extends BaseTestPage {

	
	public static String getQueryForDBKey(String key) {
		if(Config.getLocalizationLanguage().contains("en")){
			return "select tn.en from translation_new tn where tn.param_id = '"+key+"'";
		} else {
			return "select tn.fr from translation_new tn where tn.param_id ='"+key+"'";
		}
	}
	
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
	
	public static String getResultInFrench(String value) {
		// Get category name in french using category name in english		
		return "select tn.fr from translation_new tn where tn.en like '%"+value+"%'";
	}
	
	
	public static String geExternalCategoryFromBD(String bdKey) {
		return "select ic.name from internal_category ic where ic.name like '%"+bdKey+"%'";
	}
	
	public static String getUserRoleInFrench(String userRole) {
		return "select tn.fr from translation_new tn where tn.en = '"+userRole+"'";
	}
}
