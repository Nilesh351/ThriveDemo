package com.thrive.common.testdata.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.thrive.common.testdata.pojos.CategoryDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.pojos.credits.AllocateCredits;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.AccountManagerPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.AccountManagerUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.ClientCareerDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.ClientRegistrationDetails;
import com.thrive.common.testdata.pojos.user_details.ClientUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.CoachAboutDetails;
import com.thrive.common.testdata.pojos.user_details.CoachCorporateExpDetails;
import com.thrive.common.testdata.pojos.user_details.CoachCorporateExpUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.CoachDepartmentDetails;
import com.thrive.common.testdata.pojos.user_details.CoachEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.CoachSkillAndQualificationDetails;
import com.thrive.common.testdata.pojos.user_details.CoachUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.CoachingExpDetails;
import com.thrive.common.testdata.pojos.user_details.CreateEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.GlobalCoachProfileDetails;
import com.thrive.common.testdata.utils.Enums.Gender;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.utils.DateTimeUtils.TimeFormat;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.credits.AllocateCreditsPage;

public class TestDataGenerator {
	
	public static ClientPersonalDetails generateClientPersonalDetails() {
		List<String> languages = new ArrayList<String>();
		String lnageguQuery="";
	    lnageguQuery=ThriveAppSharedData.LANGUAGE_ENGLISH.getValue();
		languages.add(lnageguQuery);
		
		String genderQuery=ThriveAppSharedData.GENDER_MALE.getValue();
		String rmDateFormat=ThriveAppSharedData.DATE_FORMAT.getValue();
		String dateFormat="";
		if(Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
			dateFormat=ThriveAppSharedData.DATE_FORMAT.getValue();
		} else {
			dateFormat=rmDateFormat;
		}
		
		String countryName="";
		if(Config.getLocalizationLanguage().contains("en")) {
			countryName=ThriveAppSharedData.COUNTRY.getValue();
		} else {
			countryName=ThriveAppSharedData.COUNTRY_NAME_FRENCH.getValue();
		}
		
		return new ClientPersonalDetails(
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				dateFormat,
				languages,
				MockDataGenerator.generateRandomSSO(),
				MockDataGenerator.generateCompany(),
				TimeFormat.TWELVE_HR,
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode(),
				ThriveAppSharedData.CLIENT_AGE_RANGE.getValue(),
				genderQuery,
				countryName);

	}
	
	public static ClientPersonalDetails generateClientPersonalDetailsRm() {
		List<String> languages = new ArrayList<String>();
		languages.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		return new ClientPersonalDetails(
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				ThriveAppSharedData.DATE_FORMAT.getValue(),
				languages,
				MockDataGenerator.generateRandomSSO(),
				MockDataGenerator.generateCompany(),
				TimeFormat.TWELVE_HR,
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode(),
				ThriveAppSharedData.CLIENT_AGE_RANGE.getValue(),
				ThriveAppSharedData.GENDER_MALE.getValue(),
				ThriveAppSharedData.COUNTRY.getValue());
	}
	
	public static ClientRegistrationDetails generateClientRegistrationDetails() {
		return new ClientRegistrationDetails(
				Gender.MALE,
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				ThriveAppSharedData.ETHINICITY.getValue(),
				ThriveAppSharedData.AGE.getValue(),
				ThriveAppSharedData.NATIONALITY.getValue());
	}
	
	public static ClientEmploymentDetails generateClientEmploymentDetails() {
		String positionQuery=ThriveAppSharedData.POSTION.getValue();
		String regioQuery=ThriveAppSharedData.REGION.getValue();
		String departmentQuery=ThriveAppSharedData.DEPARTMENT.getValue();
		String workDuration=ThriveAppSharedData.WORK_DURATION.getValue();	
		return new ClientEmploymentDetails(
				workDuration,
				positionQuery,
				regioQuery,
				departmentQuery);
	}
	
	
	
	public static ClientCareerDetails generateClientCareerDetails() {
		return new ClientCareerDetails(ThriveAppSharedData.ATTITUDE_RADIO_OPTION.getValue(), 1, 3, 5, 7, 9, 8);

	}
	
	public static CoachInviteDetails generateCoachInviteDetails(boolean isCoachGlobal) {
		
		List<String> categories = new ArrayList<>();		
		if(isCoachGlobal) {
			String externalCategory=DBUtils.getResultFromPostgresDB(DBQueries.getCategoryFromDBUsingKey("coach_categories.auto-category-immutable"));
			categories.add(externalCategory);  
		} else {
			String internalCategory=DBUtils.getResultFromPostgresDB(DBQueries.getCategoryFromDBUsingKey("internalcoach_categories.auto-category-int-immutable"));
			categories.add(internalCategory);
		}
		String language="";
		if(Config.getLocalizationLanguage().contains("en")) {
			language=ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue();
		} else {
			language=ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue();
		}
		
		return new CoachInviteDetails(
				BaseTestPage.enterprise,
				language,
				categories,
				MockDataGenerator.generateGlobalCoachFirstName(),
				MockDataGenerator.generateGlobalCoachLastName(),
				MockDataGenerator.generateGlobalCoachEmailAddress());
	}
	
	
	
	public static CoachInviteDetails generateInternalCoachInviteDetails(boolean isCoachInterbal) {
		List<String> categories = new ArrayList<>();		
		if(isCoachInterbal) {
			String externalCategory=DBUtils.getResultFromPostgresDB(DBQueries.getCategoryFromDBUsingKey("coach_categories.auto-category-immutable"));
			categories.add(externalCategory);     
		} else {
			String internalCategory=DBUtils.getResultFromPostgresDB(DBQueries.getCategoryFromDBUsingKey("internalcoach_categories.auto-category-int-immutable"));
			categories.add(internalCategory);
		}
		return new CoachInviteDetails(
				BaseTestPage.enterprise,
				ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue(),
				categories,
				MockDataGenerator.generateInternalCoachFirstName(),
				MockDataGenerator.generateInternalCoachLastName(),
				MockDataGenerator.generateInternalCoachMailDropAddress());
	}
		
	public static ClientUpdateDetails generateClientUpdateDeatils() {
		return new ClientUpdateDetails(
				"client-test-update-" + MockDataGenerator.generateRandomWord(7),
				"client-test-update-" + MockDataGenerator.generateRandomWord(7),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode(),
				MockDataGenerator.generateRandomSSO(),
				MockDataGenerator.generateSentence()
				);
	}
	
	public static CoachDepartmentDetails generateCoachDepartmentDetails() {
		
		List<String> industries = new ArrayList<>();
		industries.add(ThriveAppSharedData.INDUSTRY_ACCOUNTANCY.getValue());
		return new CoachDepartmentDetails(
				ThriveAppSharedData.POSTION.getValue(),
				industries
				);
	}
	
	
	public static CoachSkillAndQualificationDetails generateGlobalCoachSkillAndQualificationDetails() {
		List<String> languages = new ArrayList<>();
		String langeName=ThriveAppSharedData.LANGUAGE_ENGLISH.getValue();
		languages.add(langeName);
		
		String techPlatform=ThriveAppSharedData.TECH_PLATFORM_MAC.getValue();
		
		return new CoachSkillAndQualificationDetails(
				languages,
				"test-auto-qualification" + MockDataGenerator.generateRandomWord(5),
				2,
				"test-auto-otherqualification" + MockDataGenerator.generateRandomWord(5),
				"test-auto-othrProfqualification" + MockDataGenerator.generateRandomWord(5),
				techPlatform
				);
	}
	
	public static GlobalCoachProfileDetails generateGlobalCoachPersonalDetails() {
		
		String gender=ThriveAppSharedData.GENDER_MALE.getValue();
		String ethnicity=ThriveAppSharedData.ETHINICITY.getValue();
		String nationality=ThriveAppSharedData.NATIONALITY.getValue();
		String region=ThriveAppSharedData.REGION.getValue();
        String countryName="";
        if(Config.getLocalizationLanguage().contains("en")) {
        	countryName=ThriveAppSharedData.COUNTRY.getValue();
        } else {
        	countryName=ThriveAppSharedData.COUNTRY_NAME_FRENCH.getValue();
        }
        
		return new GlobalCoachProfileDetails(
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				gender,
				ethnicity,
				countryName,
				nationality,
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomCounty(),
				region,
				MockDataGenerator.generateRandomPostalCode());

	}
	
	public static CoachAboutDetails generateCoachAboutDetails() {
		String coachOffer=ThriveAppSharedData.COACH_OFFER.getValue();
		String region=ThriveAppSharedData.REGION.getValue();
		List<String> languages = new ArrayList<>();
		String langeName=ThriveAppSharedData.LANGUAGE_ENGLISH.getValue();
		languages.add(langeName);
		String sectorEducaion=ThriveAppSharedData.SECTOR_EDUCATION.getValue();
		return new CoachAboutDetails("test-auto-summary-" + MockDataGenerator.generateSentence(),
				"test-auto-" + MockDataGenerator.generateSentence(),
				MockDataGenerator.generateSentence(),
				coachOffer,
				"test-auto-coachingSummary-" + MockDataGenerator.generateSentence(),
				"test-auto-testimonial-" + MockDataGenerator.generateSentence(),
				MockDataGenerator.generateSentence(),
				MockDataGenerator.generateSentence(8),
				region,
				2,
				languages,
				sectorEducaion,
				"12",
				ThriveAppSharedData.QUALIFICATION.getValue()
		);
	}
	
	public static CoachUpdateDetails generateCoachUpdateDeatails() {
		return new CoachUpdateDetails(
				"Coach-test-update-" + MockDataGenerator.generateRandomWord(7),
				"Coach-test-update-" + MockDataGenerator.generateRandomWord(7),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode(),
				"Diffrent companies" + MockDataGenerator.generateRandomWord(10)
				);
	}
	
	public static CoachCorporateExpDetails generateCoachCorporateDetailsDetails() {
		List<String> companies = new ArrayList<>();
		String company=ThriveAppSharedData.COMPANY_TYPE_PUBLIC.getValue();
		companies.add(company);
		
		String sectorEducaion=ThriveAppSharedData.SECTOR_EDUCATION.getValue();
		String position=ThriveAppSharedData.POSTION.getValue();

		return new CoachCorporateExpDetails(position,
				companies,
				sectorEducaion,
				"test-auto-sme-" + MockDataGenerator.generateRandomWord(6));

	}
	
	
	public static CoachCorporateExpUpdateDetails generateCoachCorporateUpdateDetails() {
		return new CoachCorporateExpUpdateDetails(
				"company" + MockDataGenerator.generateRandomWord(7),
				"role" + MockDataGenerator.generateRandomWord(6),
				"Sme" + MockDataGenerator.generateRandomWord(7)
				);
	}
	
	public static CoachingExpDetails generateCoachingExpDetails(boolean isCoachGlobal) {
		
		String topic = null;
		String expertise = null;
		
		if(isCoachGlobal) {
			if(Config.getLocalizationLanguage().contains("fr")) {
			topic = DBUtils.getResultFromPostgresDB(DBQueries.getCategoryNameInFrench(BaseTestPage.topic));
			expertise = DBUtils.getResultFromPostgresDB(DBQueries.getCategoryNameInFrench(BaseTestPage.expertise));
			} else {
				topic=BaseTestPage.topic;
				expertise=BaseTestPage.expertise;
			}
		} else {
			if(Config.getLocalizationLanguage().contains("fr")) {
			topic = DBUtils.getResultFromPostgresDB(DBQueries.getCategoryNameInFrench(BaseTestPage.internalTopic));
			expertise = DBUtils.getResultFromPostgresDB(DBQueries.getCategoryNameInFrench(BaseTestPage.internalExpertise));
			} else {
				topic=BaseTestPage.internalTopic;
				expertise=BaseTestPage.internalExpertise;
			}
		}

		
		List<String> companies = new ArrayList<>();
		String company=ThriveAppSharedData.COMPANY_TYPE_PUBLIC.getValue();
		companies.add(company);
		
		List<String> levels = new ArrayList<>();
		String positon=ThriveAppSharedData.POSTION.getValue();
		levels.add(positon);
		
		List<String> sectors = new ArrayList<>();
		String sectorEducation=ThriveAppSharedData.SECTOR_EDUCATION.getValue();
		sectors.add(sectorEducation);
		
		Map<String, String> expertiseMap = new HashMap<>();
		List<Map<String, String>> expertises = new ArrayList<Map<String,String>>();
		
		expertiseMap.put("topic", topic);
		expertiseMap.put("expertise", expertise);
		expertises.add(expertiseMap);
		
		return new CoachingExpDetails(levels, companies, 100, sectors, expertises);
	}
	
	public static CoachEmploymentDetails generateCoachEmploymentDetails() {
		String employmentStatus=ThriveAppSharedData.EMPLOYMENT_STATUS.getValue();
		String workAs=ThriveAppSharedData.WORK_AS.getValue();

		return new CoachEmploymentDetails(
				employmentStatus,
				"test-auto-otherOrg-" + MockDataGenerator.generateRandomWord(5) + "\n" + "test-auto-otherOrg-" + MockDataGenerator.generateRandomWord(5),
				workAs
				);
	}
	
	public static InviteEmployeeDetails generateInviteEmployeeDetails() {
		return new InviteEmployeeDetails(BaseTestPage.enterprise,
				MockDataGenerator.generateClientFirstName(),
				MockDataGenerator.generateClientLastName(),
				MockDataGenerator.generateClientEmailAddress(),
				MockDataGenerator.generateRandomSSO());
	}
	
	
	public static InviteClientDetails generateInviteClientDetails() {
		return new InviteClientDetails(
				BaseTestPage.enterprise,
				MockDataGenerator.generateClientFirstName(),
				MockDataGenerator.generateClientLastName(),
				MockDataGenerator.generateClientEmailAddress(),
				MockDataGenerator.generateRandomSSO());
	}
	
	
	public static InviteEnterpriseDetails generateInviteEnterpriseDetails() {
		return new InviteEnterpriseDetails(
				MockDataGenerator.generateEnterpriseName(),
				MockDataGenerator.generateEAFirstName(),
				MockDataGenerator.generateEALastName(),
				MockDataGenerator.generateEAMailDropAddress());
	}
	

	
	public static InviteAccountManagerDetails generateInviteAccountManagerDetails() {
		return new InviteAccountManagerDetails(
				MockDataGenerator.generateAccountManagerFirstName(),
				MockDataGenerator.generateAccountManagerLastName(),
				MockDataGenerator.generateAccountManagerEmailAddress());
	}
	
	
	public static EnterpriseDetails generateEnterpriseDetails() {
		
		String sessionLength = String.format(ThriveAppSharedData.SESSION_LENGTH.getValue(), 1);
		
		List<String> departments = new ArrayList<String>();
		String departmentQuery=ThriveAppSharedData.DEPARTMENT_FINANCE.getValue();
		departments.add(departmentQuery);
		
		List<String> units = new ArrayList<String>();
		String unitQuery=ThriveAppSharedData.ENTUNIT.getValue();
		units.add(unitQuery);
		
		
		List<String> sessionLengths = new ArrayList<String>();
		sessionLengths.add(sessionLength);
		
		String countryName="";
		if(Config.getLocalizationLanguage().contains("en")) {
			countryName=ThriveAppSharedData.COUNTRY.getValue();
		} else {
			countryName=ThriveAppSharedData.COUNTRY_NAME_FRENCH.getValue();
		}
		
		return new EnterpriseDetails(
				MockDataGenerator.generateRandomWebsite(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCounty(),
				countryName,
				MockDataGenerator.generateRandomPostalCode(),
				departments,
				units,
				true,
				sessionLengths,
				MockDataGenerator.generateRandomCity());

	}
	
	
	public static CreateEnterpriseDetails generateCreateEnterpriseDetails() {

		List<String> departments = new ArrayList<>();
		departments.add(ThriveAppSharedData.DEPARTMENT.getValue());

		List<String> units = new ArrayList<>();
	//	if(Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
		units.add(ThriveAppSharedData.ENTUNIT.getValue());
//		} else {
//			units.add(ThriveAppSharedData.ENTUNIT.getValue());
//		}

		List<String> sessionLength = new ArrayList<>();
		sessionLength.add(ThriveAppSharedData.SESSION_LENGTH.getValue());

		return new CreateEnterpriseDetails(MockDataGenerator.generateEnterpriseName(),
				MockDataGenerator.generateRandomWebsite(), MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(), MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomCounty(), ThriveAppSharedData.COUNTRY.getValue(),
				MockDataGenerator.generateRandomPostalCode(), departments, units, true, sessionLength);

	}
	
	
	public static EnterpriseUpdateDetails generateEnterpriseUpdateDetails() {
		return new EnterpriseUpdateDetails(
				"Ent-Update"+MockDataGenerator.generateRandomWord(7),
				"EC"+MockDataGenerator.generateRandomPostalCode(),
				MockDataGenerator.generateRandomWord(10),
				MockDataGenerator.generateRandomWebsite(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode(),
				MockDataGenerator.createUniqueNumber(2),
				MockDataGenerator.generateRandomCity()
				);
				
				
	}
	
	public static AccountManagerPersonalDetails generateAccountManagerPersonalDetails() {
		

		String gender=ThriveAppSharedData.GENDER_MALE.getValue();
		String country="";
		if(Config.getLocalizationLanguage().contains("fr")) {
			country=ThriveAppSharedData.COUNTRY_NAME_FRENCH.getValue();
		} else {
			country=ThriveAppSharedData.COUNTRY.getValue();
		}
		String nationality=ThriveAppSharedData.NATIONALITY.getValue();
		
		return new AccountManagerPersonalDetails(
				ThriveAppSharedData.COMMON_PAASWORD.getValue(),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				gender,
				country,
				nationality,
				ThriveAppSharedData.AGE.getValue(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode()
				
				);
	}
	
	public static AccountManagerUpdateDetails generateAccountManagerUpdateDetails() {
		return new AccountManagerUpdateDetails(
				"AccountManager-update-" + MockDataGenerator.generateRandomWord(7),
				"AccountManager-update-" + MockDataGenerator.generateRandomWord(7),
				MockDataGenerator.generateRandomIndiaMobileNumber(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomStreetAddress(),
				MockDataGenerator.generateRandomCity(),
				MockDataGenerator.generateRandomState(),
				MockDataGenerator.generateRandomCounty(),
				MockDataGenerator.generateRandomPostalCode()
				);
	}
	
	
	public static CategoryDetails generateCategoryDetails() {
		return new CategoryDetails(
				MockDataGenerator.generateRandomCategory(),
				MockDataGenerator.generateRandomCategory() + "French" ,
				MockDataGenerator.generateRandomCategory() + "UK", 
				MockDataGenerator.generateRandomTopic(), 
				MockDataGenerator.generateRandomTopic() + "French" ,
				MockDataGenerator.generateRandomTopic() + "UK", 
				MockDataGenerator.generateRandomExpertise(),
				MockDataGenerator.generateRandomExpertise() + "French" ,
				MockDataGenerator.generateRandomExpertise() + "UK"
				);
	}
	
	
	public static CategoryDetails generateInternalCategoryDetails() {
		return new CategoryDetails(
				MockDataGenerator.generateRandomInternalCategory(),
				MockDataGenerator.generateRandomInternalCategory() + "French",
				MockDataGenerator.generateRandomInternalCategory() + "UK",
				MockDataGenerator.generateRandomInternalTopic(),
				MockDataGenerator.generateRandomInternalTopic() + "French",
				MockDataGenerator.generateRandomInternalTopic() + "UK",
				MockDataGenerator.generateRandomInternalExpertise(),
				MockDataGenerator.generateRandomInternalExpertise() + "French",
				MockDataGenerator.generateRandomInternalExpertise() + "UK"
				);
				
				
	}
	
	public static BookSessionSummaryDetails generateBookSessionSummaryDetails() {
		
		Map<String, String> preQuestionAnswers = new HashMap<>();
		String helpWithQuestion=ThriveAppSharedData.PRE_QUESTION_SESSION_HELP_WITH.getValue();
		String contextRelevantConversationQuestion=ThriveAppSharedData.PRE_QUESTION_CONTEXT_RELEVANT_CONVERSATION.getValue();
		String succesfullyMakingChangesQuestion=ThriveAppSharedData.PRE_QUESTION_RADIO_SUCCESSFUL_MAKING_CHANGES.getValue();
		String didYouRecommend=ThriveAppSharedData.PRE_QUESTION_RADIO_DID_YOU_RECOMMEND.getValue();
		String shareWithQuestion=ThriveAppSharedData.PRE_QUESTION_SHARE_WITH_COACH.getValue();
		
		preQuestionAnswers.put(helpWithQuestion.substring(1), "Pre-question-session-help--" + MockDataGenerator.generateSentence());
		preQuestionAnswers.put(contextRelevantConversationQuestion.substring(1), "Pre-question-context-conversation--" + MockDataGenerator.generateSentence());
		if(Config.getLocalizationLanguage().contains("en")) {
		preQuestionAnswers.put(shareWithQuestion.substring(1), "Pre-question-share-with-coach--" + MockDataGenerator.generateSentence());
		} else {
		preQuestionAnswers.put(shareWithQuestion.split("serait")[1], "Pre-question-share-with-coach--" + MockDataGenerator.generateSentence());
		}
		preQuestionAnswers.put(succesfullyMakingChangesQuestion.substring(1), ThriveAppSharedData.YES.getValue());
		preQuestionAnswers.put(didYouRecommend.substring(1), ThriveAppSharedData.NO.getValue());
		
		return new BookSessionSummaryDetails(preQuestionAnswers);
	}
	
	public static BookSessionDetails generateBookSessionDetailsWithoutTime(boolean isCoachGlobal) {
		
		String expertise ;
		String sessionLength;
		if(Config.getLocalizationLanguage().contains("en")) {
			sessionLength=ThriveAppSharedData.SESSION_CREDIT.getValue();
		} else {
			sessionLength=ThriveAppSharedData.SESSION_CREDIT_FRENCH.getValue();
		}
		if(isCoachGlobal) {
			expertise = BaseTestPage.expertise;
		} else{
			expertise = BaseTestPage.internalExpertise;
		}
		
		return new BookSessionDetails(
				sessionLength,
				expertise,
				ThriveAppSharedData.SESSION_FOLLOW_ON.getValue()
				);
	}
	
	public static BookSessionDetails generateBookSessionDetailsWithoutTimeMutable(boolean isCoachGlobal) {
		
		String expertise ;
		
		if(isCoachGlobal) {
			expertise = BaseTestPage.expertise;
		} else{
			expertise = BaseTestPage.internalMutableExpertise;
		}
		
		return new BookSessionDetails(
				ThriveAppSharedData.SESSION_CREDIT.getValue(),
				expertise,
				ThriveAppSharedData.SESSION_FOLLOW_ON.getValue()
				);
	}
	
	public static AllocateCredits generateEnterpriseCredits() {
		return new AllocateCredits(
				ThriveAppSharedData.CREDIT_TYPE.getValue(),
				"10",
				ThriveAppSharedData.EXPIRYDATE.getValue()
				
				);
	}
	
}