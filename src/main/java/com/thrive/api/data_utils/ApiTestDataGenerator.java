package com.thrive.api.data_utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.thrive.api.pojos.EnableCategory;
import com.thrive.api.pojos.Expertise;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.InviteEmployeeRequest;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.api.pojos.Qualification;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.pojos.RegisterEnterpriseRequest;
import com.thrive.api.pojos.RegisterInternalCoachRequest;
import com.thrive.api.pojos.UserDetails;
import com.thrive.common.testdata.utils.DBQueries;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ApiTestDataGenerator {
	
	public static InviteClientRequest generateInviteClientReqBody() {
		
		int	id = Integer.parseInt(DBQueries.getCompanyIdByName(BaseTestPage.enterprise));
		
		return new InviteClientRequest(
				id,
				"en",
				MockDataGenerator.generateClientFirstName(), 
				MockDataGenerator.generateClientLastName(), 
				MockDataGenerator.generateClientEmailAddress(),
				"");
	}
	
	public static InviteEmployeeRequest generateInviteEmployeeReqBody() {

		return new InviteEmployeeRequest("en", MockDataGenerator.generateClientFirstName(),
				MockDataGenerator.generateClientLastName(), MockDataGenerator.generateClientEmailAddress(), "");
	}
	
	public static InviteAMRequest generateInviteAMReqBody() {
		
		return new InviteAMRequest(
				"en",
				MockDataGenerator.generateAccountManagerFirstName(), 
				MockDataGenerator.generateAccountManagerLastName(), 
				MockDataGenerator.generateAccountManagerEmailAddress() 
				);
	}
	
	
	public static RegisterClientRequest generateRegisterClientReqBody() {
		
		return new RegisterClientRequest(
				"first_name", 
				"last_name", 
				ThriveAppSharedData.COMMON_PAASWORD.getValue(), 
				"", 
				Arrays.asList(ThriveAppSharedData.LANGUAGE_EN.getValue()),
				true, 
				"departments.rather_not_say", 
				"data.coach.senior-positions.manager", 
				"data.client.regions.asia", 
				"data.client.work-duration.7_12_months", 
				true, 
				"", 
				ThriveAppSharedData.LANGUAGE_EN.getValue(), 
				"dev/static/153d147/415c072a/5ZBvvO0f.png", 
				"email");
	}
	
	public static RegisterEnterpriseRequest generateRegisterEntReqBody() {
		List<String> departments = new ArrayList<>();
		departments.add("data.client.departments.compliance");
		List<String> region = new ArrayList<>();
		region.add("data.client.regions.europe");
		List<UserDetails> userdetails = new ArrayList<>();
		return new RegisterEnterpriseRequest(
				"name",
				"pune",
				"IO",
				"3434535",
				departments,
				region,
				false);
	}
	
	public static RegisterAMRequest generateRegisterAMReqBody() {
		return new RegisterAMRequest(
				"first_name", 
				"last_name", 
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				"data.age-range.36_40",
				"data.gender.male",
				"data.nationality.indian",
				true,
				MockDataGenerator.generateRandomStreetAddress(),
				null,
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomPostalCode(),
				ThriveAppSharedData.LANGUAGE_EN.getValue(),
				"email",
				"dev/static/153d147/415c072a/5ZBvvO0f.png");
				
	}
	
	
	public static RegisterCoachRequest generateRegisterCoachReqBody() {
		List<String> careerindustries = new ArrayList<>();
		careerindustries.add("industries.3rd_sector");
		List<String> coachedpeoples = new ArrayList<>();
		coachedpeoples.add("data.coach.coached-people.manager");
		List<Integer> industry = new ArrayList<>();
		industry.add(40);
		List<String> lang = new ArrayList<>();
		lang.add(ThriveAppSharedData.LANGUAGE_EN.getValue());
		List<Qualification> qualification = new ArrayList<>();
		Qualification qua = new Qualification(ThriveAppSharedData.DATE.getValue(),ThriveAppSharedData.QUALIFICATION.getValue());
		qualification.add(qua);
		List<Expertise> expertise = new ArrayList<>();
		Expertise exp = new Expertise(Integer.parseInt(DBQueries.getExpertiseIdByName(Config.getExpertise())),"EXPERT");
		expertise.add(exp);
		List<EnableCategory> enablecategory = new ArrayList<>();
		EnableCategory enacat = new EnableCategory(Integer.parseInt(DBQueries.getCategoryIdByName(Config.getCategoryImmutable())),true);
		enablecategory.add(enacat);
		List<UserDetails> userdetails = new ArrayList<>();
		UserDetails userdet1 = new UserDetails("330", "• adsdn• sdfn• rytn• ytu");
		UserDetails userdet2 = new UserDetails("532", "• dfdn• dfgfn• dfghn•");
		userdetails.add(userdet1);
		userdetails.add(userdet2);
 		
		
		return new RegisterCoachRequest(
				"first_name",
				"last_name",
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				ThriveAppSharedData.POSTION.getValue(),
				MockDataGenerator.generateRandomWord(10),
				MockDataGenerator.generateRandomInt(2, 2),
				ThriveAppSharedData.CITY.getValue(),
				"50",
				coachedpeoples,
				MockDataGenerator.generateRandomWord(15),
				null,
				"data.coach.company-types.sole_trader",
				null,
				50,
				"IN",
				"email",
				true,
				"data.coach.employment-status.self_employed",
				"data.gender.male",
				industry,
				lang,
				37.5576008,
				-121.9718834,
				null,
				"data.nationality.indian",
				"data.coach.senior-positions.manager",
				null,
				MockDataGenerator.generateRandomPostalCode(),
				MockDataGenerator.generateRandomWord(8),
				"data.client.regions.asia",
				ThriveAppSharedData.LANGUAGE_EN.getValue(),
				MockDataGenerator.generateRandomWord(9),
				qualification,
				expertise,
				enablecategory,
				"pune",
				true,
				userdetails,
				0,
				null,
				null,
				null,
				null);
	}
	
	public static RegisterCoachRequest generateRegisterCoachRmReqBody() {
		
		List<Integer> industry = new ArrayList<>();
		industry.add(333);
		List<String> lang = new ArrayList<>();
		lang.add(ThriveAppSharedData.LANGUAGE_EN.getValue());
		List<Expertise> expertise = new ArrayList<>();
		Expertise exp = new Expertise(Integer.parseInt(DBQueries.getExpertiseIdByName(Config.getExpertise())),"EXPERT");
		expertise.add(exp);
		List<EnableCategory> enablecategory = new ArrayList<>();
		EnableCategory enacat = new EnableCategory(Integer.parseInt(DBQueries.getCategoryIdByName(Config.getCategoryImmutable())),true);
		enablecategory.add(enacat);
		List<UserDetails> userdetails = new ArrayList<>();
		UserDetails userdet1 = new UserDetails("277", "• adsdn• sdfn• rytn• ytu");
		userdetails.add(userdet1);
 		
		return new RegisterCoachRequest(
				"first_name",
				"last_name",
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				30,
				"pune",
				"",
				0,
				"IN",
				"email",
				true,
				"data.gender.male",
				industry,
				lang,
				37.5576008,
				-121.9718834,
				"",
				"data.nationality.rather_not_say",
				null,
				"345345",
				"data.client.regions.asia",
				"en",
				expertise,
				enablecategory,
				"pune",
				userdetails,
				10,
				10);
	}
	

	public static RegisterInternalCoachRequest generateRegisterInternalCoachReqBody() {
		List<String> careerindustries = new ArrayList<>();
		careerindustries.add("industries.aerospace_defence_security");
		List<String> languages = new ArrayList<>();
		languages.add(ThriveAppSharedData.LANGUAGE_EN.getValue());
		List<String> coachedpeoples = new ArrayList<>();
		coachedpeoples.add("data.coach.coached-people.manager");
		List<Integer> industry = new ArrayList<>();
		industry.add(41);
		List<String> lang = new ArrayList<>();
		lang.add(ThriveAppSharedData.LANGUAGE_EN.getValue());
		List<Qualification> qualification = new ArrayList<>();
		Qualification qua = new Qualification(ThriveAppSharedData.DATE.getValue(),ThriveAppSharedData.QUALIFICATION.getValue());
		qualification.add(qua);
		List<Expertise> expertise = new ArrayList<>();
		Expertise exp = new Expertise(Integer.parseInt(DBQueries.getInternalExpertiseIdByName(Config.getInternalExpertise())),"EXPERT");
		expertise.add(exp);
		List<EnableCategory> enablecategory = new ArrayList<>();
		EnableCategory enacat = new EnableCategory(Integer.parseInt(DBQueries.getInternalCategoryIdByName(Config.getInternalCategory())),true);
		enablecategory.add(enacat);
		List<UserDetails> userdetails = new ArrayList<>();
		UserDetails userdetint1 = new UserDetails("330", "• adsdn• sdfn• rytn• ytu");
		UserDetails userdetint2 = new UserDetails("532", "• dfdn• dfgfn• dfghn•");
		userdetails.add(userdetint1);
		userdetails.add(userdetint2);
		int company = Integer.parseInt(DBQueries.getCompanyIdByName(Config.getEnterpriseNameImmutable()));
		
		return new RegisterInternalCoachRequest(
				"first_name",
				"last_name",
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				ThriveAppSharedData.POSTION.getValue(),
				MockDataGenerator.generateRandomWord(10),
				MockDataGenerator.generateRandomInt(2, 2),
				careerindustries,
				ThriveAppSharedData.CITY.getValue(),
				"50",
				coachedpeoples,
				MockDataGenerator.generateRandomWord(15),
				null,
				"data.coach.company-types.sole_trader",
				null,
				50,
				"IN",
				"email",
				true,
				"data.coach.employment-status.self_employed",
				"data.gender.male",
				industry,
				lang,
				37.5576008,
				-121.9718834,
				null,
				"data.nationality.indian",
				"data.coach.senior-positions.manager",
				"dev/static/153d147/415c072a/5ZBvvO0f.png",
				MockDataGenerator.generateRandomPostalCode(),
				MockDataGenerator.generateRandomWord(8),
				"data.client.regions.asia",
				ThriveAppSharedData.LANGUAGE_EN.getValue(),
				MockDataGenerator.generateRandomWord(9),
				qualification,
				expertise,
				enablecategory,
				"pune",
				true,
				userdetails,
				0,
				null,
				null,
				null,
				null,
				"",
				company);
		
	}
	
	public static RegisterInternalCoachRequest generateRegisterInternalCoachRmReqBody() {
		
		List<Integer> industry = new ArrayList<>();
		industry.add(333);
		List<String> lang = new ArrayList<>();
		lang.add(ThriveAppSharedData.LANGUAGE_EN.getValue());
		List<Expertise> expertise = new ArrayList<>();
		Expertise exp = new Expertise(Integer.parseInt(DBQueries.getInternalExpertiseIdByName(Config.getInternalExpertise())),"EXPERT");
		expertise.add(exp);
		List<EnableCategory> enablecategory = new ArrayList<>();
		EnableCategory enacat = new EnableCategory(Integer.parseInt(DBQueries.getInternalCategoryIdByName(Config.getInternalCategory())),true);
		enablecategory.add(enacat);
		List<UserDetails> userdetails = new ArrayList<>();
		UserDetails userdetint1 = new UserDetails("277", "• adsdn• sdfn• rytn• ytu");
		userdetails.add(userdetint1);
		int company = Integer.parseInt(DBQueries.getCompanyIdByName(Config.getEnterpriseNameImmutable()));
		
		return new RegisterInternalCoachRequest(
				"first_name",
				"last_name",
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				20,
				"pune",
				"",
				39,
				"IN",
				"email",
				true,
				"data.gender.male",
				industry,
				lang,
				37.5576008,
				-121.9718834,
				"",
				"data.nationality.indian",
				"prod/static/145c3d0/5a0609ff/achRxOdS.png",
				"654645",
				"data.client.regions.asia",
				"en",
				expertise,
				enablecategory,
				"Mumbai - Pune Expressway",
				userdetails,
				0,
				company,
				"",
				12);
	}
	
	
	
	
	
	public static InviteEnterpriseRequest generateInviteEnterpriseReqBody() {
		
		return new InviteEnterpriseRequest(
				MockDataGenerator.generateEnterpriseName(),
				"en",
				MockDataGenerator.generateEAFirstName(),
				MockDataGenerator.generateEALastName(),
				MockDataGenerator.generateEAMailDropAddress()
				);
	}
	
	
	public static InviteCoachRequest generateInviteCoachReqBody() {
		List<Integer> categories = new ArrayList<>();
		categories.add(Integer.parseInt(DBQueries.getCategoryIdByName(Config.getCategoryImmutable())));
		return new InviteCoachRequest(
				categories,
				ThriveAppSharedData.LOCALE_EN.getValue(),
				MockDataGenerator.generateGlobalCoachFirstName(), 
				MockDataGenerator.generateGlobalCoachLastName(), 
				MockDataGenerator.generateGlobalCoachEmailAddress(), 
				"");
	}
	
	
	
	public static InviteCoachRequest generateInviteInternalCoachReqBody() {
		List<Integer> categories = new ArrayList<>();
		categories.add(Integer.parseInt(DBQueries.getInternalCategoryIdByName(Config.getInternalCategory())));
		return new InviteCoachRequest(
				Integer.parseInt(DBQueries.getCompanyIdByName(Config.getEnterpriseNameImmutable())),
				categories,
				ThriveAppSharedData.LOCALE_EN.getValue(),
				MockDataGenerator.generateInternalCoachFirstName(),
				MockDataGenerator.generateInternalCoachLastName(),
				MockDataGenerator.generateInternalCoachMailDropAddress(),
				"");
	}
	
	
	
	
	

}
