package com.thrive.common.testdata.providers;

import org.testng.annotations.DataProvider;

import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.ui.core.BaseTestPage;

public class LoginTestDataProvider extends BaseTestPage {
	
	static LoginDetails loginDetailsSa = new LoginDetails(superAdminUser, superAdminUserPassword);
	static LoginDetails loginDetailsEa = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	static LoginDetails loginDetailsClient = new LoginDetails(clientUser, clientPassword);
	static LoginDetails loginDetailsGlobalCoach = new LoginDetails(globalCoachUser, globalCoachPassword);
	static LoginDetails loginDetailsInternalCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
	static LoginDetails loginDetailsAm = new LoginDetails(accountManagerUser, accountManagerPassword);
	
	static LoginDetails invalidDeatilsSa = new LoginDetails(superAdminUser, "heaptrace123");
	static LoginDetails invalidDetailsEa = new LoginDetails(eaUserImmutableEmail, "heaptrace43");
	static LoginDetails invalidDetailsClient = new LoginDetails(clientUser, "heaptrace23");
	static LoginDetails invalidDetailsGlobalCoach = new LoginDetails(globalCoachUser, "heapglob34");
	static LoginDetails invalidDetailsInternalCoach = new LoginDetails(internalCoachUser, "heapint@123");
	static LoginDetails invalidDetailsAm = new LoginDetails(accountManagerUser, "heapam@113");
	
	@DataProvider (name = "LoginDataProvider")
	private static Object[][] loginDataProvider(){
		
		loginDetailsSa.setTestName("Validate_Successful_Login_SA");
		loginDetailsSa.setTestDescription("Validate successful login with SA credentials");
		loginDetailsSa.setUserType("SA");
		
		loginDetailsEa.setTestName("Validate_Successful_Login_EA");
		loginDetailsEa.setTestDescription("Validate successful login with EA credentials");
		loginDetailsEa.setUserType("EA");
		
		loginDetailsClient.setTestName("Validate_Successful_Login_Client");
		loginDetailsClient.setTestDescription("Validate successful login with Client credentials");
		loginDetailsClient.setUserType("Client");
		
		loginDetailsGlobalCoach.setTestName("Validate_Successful_Login_Global_Coach");
		loginDetailsGlobalCoach.setTestDescription("Validate successful login with Global Coach credentials");
		loginDetailsGlobalCoach.setUserType("Global coach");
		
		loginDetailsInternalCoach.setTestName("Validate_Successful_Login_Internal_Coach");
		loginDetailsInternalCoach.setTestDescription("Validate successful login with Internal Coach credentials");
		loginDetailsInternalCoach.setUserType("Internal coach");
		
		loginDetailsAm.setTestName("Validate_Successful_Login_AM");
		loginDetailsAm.setTestDescription("Validate successful login with Account Manager credentials");
		loginDetailsAm.setUserType("AM");
		
		
		return new Object[][] { 
			
			{ loginDetailsSa }, 
			{ loginDetailsEa }, 
			{ loginDetailsClient },
			{ loginDetailsGlobalCoach },
			{ loginDetailsInternalCoach },
			{ loginDetailsAm } 
			
		};
		 
	
	}
	
	@DataProvider (name = "InvalidLoginDataProvider")
	private static Object[][] invalidLoginDataProvider(){
		
		invalidDeatilsSa.setTestName("Validate_InvalidCredentails_Login_SA");
		invalidDeatilsSa.setTestDescription("Validate Invalid credentials login for SuperAdmin show error message");
		invalidDeatilsSa.setUserType("SA");
		
		invalidDetailsEa.setTestName("Validate_InvalidCredentails_Login_EA");
		invalidDetailsEa.setTestDescription("Validate Invalid credentials login for Enterprise Admin show error message");
		invalidDetailsEa.setUserType("EA");
		
		invalidDetailsClient.setTestName("Validate_InvalidCredentails_Login_Client");
		invalidDetailsClient.setTestDescription("Validate Invalid credentials login for Client show error message");
		invalidDetailsClient.setUserType("Client");
		
		invalidDetailsGlobalCoach.setTestName("Validate_InvalidCredentails_Login_Global_Coach");
		invalidDetailsGlobalCoach.setTestDescription("Validate Invalid credentials login for global coach show error message");
		invalidDetailsGlobalCoach.setUserType("Global coach");
		
		invalidDetailsInternalCoach.setTestName("Validate_InvalidCredentails_Login_Internal_Coach");
		invalidDetailsInternalCoach.setTestDescription("Validate Invalid credentials login for internal caoch show error message");
		invalidDetailsInternalCoach.setUserType("Internal coach");
		
		invalidDetailsAm.setTestName("Validate_InvalidCredentails_Login_AM");
		invalidDetailsAm.setTestDescription("Validate Invalid credentials login for Account manager show error message");
		invalidDetailsAm.setUserType("AM");
		
		
		return new Object[][] { 
			
			{ invalidDeatilsSa }, 
			{ invalidDetailsEa }, 
			{ invalidDetailsClient },
			{ invalidDetailsGlobalCoach },
			{ invalidDetailsInternalCoach },
			{ invalidDetailsAm } 
			
		};
		 
	
	}
	
	@DataProvider (name = "LogoutDataProvider")
	private static Object[][] logoutDataProvider(){
		
		loginDetailsSa.setTestName("Validate_SA_logout_successfully");
		loginDetailsSa.setTestDescription("Validate SA logout successfully");
		loginDetailsSa.setUserType("SA");
		
		loginDetailsEa.setTestName("Validate_EA_logout_successfully");
		loginDetailsEa.setTestDescription("Validate SA logout successfully");
		loginDetailsEa.setUserType("EA");
		
		loginDetailsClient.setTestName("Validate_Client_logout_successfully");
		loginDetailsClient.setTestDescription("Validate client logout successfully");
		loginDetailsClient.setUserType("Client");
		
		loginDetailsGlobalCoach.setTestName("Validate_Global_Coach_logout_successfully");
		loginDetailsGlobalCoach.setTestDescription("Validate Global Coach logout successfully");
		loginDetailsGlobalCoach.setUserType("Global coach");
		
		loginDetailsInternalCoach.setTestName("Validate_Internal_Coach_logout_successfully");
		loginDetailsInternalCoach.setTestDescription("Validate Internal Coach logout successfully");
		loginDetailsInternalCoach.setUserType("Internal coach");
		
		loginDetailsAm.setTestName("Validate_Account_Manager_logout_successfully");
		loginDetailsAm.setTestDescription("Validate Account Manager logout successfully");
		loginDetailsAm.setUserType("AM");
		
		
		return new Object[][] { 
			
			{ loginDetailsSa }, 
			{ loginDetailsEa }, 
			{ loginDetailsClient },
			{ loginDetailsGlobalCoach },
			{ loginDetailsInternalCoach },
			{ loginDetailsAm } 
			
		};
		 
	
	}
	
	
	
	@DataProvider (name = "LoginDataProviderEmployee")
	private static Object[][] loginDataProviderEmployeecheck(){
		
		loginDetailsSa.setTestName("Validate_Employee_not_presnet_SA");
		loginDetailsSa.setTestDescription("Validate Employee tab not present for SA");
		loginDetailsSa.setUserType("SA");
		
		loginDetailsAm.setTestName("Validate_Employee_not_presnet_Am");
		loginDetailsAm.setTestDescription("Validate Employee tab not present for AM");
		loginDetailsAm.setUserType("AM");
		
		return new Object[][] { 
			
			{ loginDetailsSa }, 
			{ loginDetailsAm } 
			
		};
		 
	
	}
	
	@DataProvider (name = "usersRoleDataSA")
	private static Object[][] usersRoleDataProviderSA(){
		return new Object[][] {
			
			{"Enterprise Admin"},
			{"Super Admin"},
			{"Account Manager"},
			{"Client"},
			{"Coach"},
			{"Internal Coach"}
			
		};
	}
	
	@DataProvider (name = "invitedUsersRoleDataSA")
	private static Object[][] invitedUsersRoleDataProviderSA(){
		return new Object[][] {
			
			{"Enterprise Admin"},
			{"Account Manager"},
			{"Client"},
			{"Coach"},
			{"Internal Coach"}
			
		};
	}
	
	@DataProvider (name = "usersRoleDataEA")
	private static Object[][] usersRoleDataProviderEA(){
		return new Object[][] {
			
			{"Enterprise Admin"},
			{"Client"},
			{"Internal Coach"}
			
		};
	}
	
	@DataProvider (name = "invitedUsersRoleDataEA")
	private static Object[][] invitedUsersRoleDataProviderEA(){
		return new Object[][] {
			
			{"Client"},
			{"Internal Coach"}
			
		};
	}
	
	@DataProvider (name = "usersSearchDataSA")
	private static Object[][] usersSearchDataProviderSA(){
		return new Object[][] {
			
			{eaUserImmutableEmail},
			{superAdminUser},
			{accountManagerUser},
			{clientUser},
			{globalCoachUser},
			{internalCoachUser}
			
		};
	}
	
	@DataProvider (name = "usersSearchDataEA")
	private static Object[][] usersSearchDataProviderEA(){
		return new Object[][] {
			
			{eaUserImmutableEmail},			
			{clientUser},
			{internalCoachUser}
			
		};
	}
	
	@DataProvider (name = "selectPagination")
	private static Object[][] usersPaginationData(){
		return new Object[][] {
			
			{10},			
			{20},
			{50},
			{100}
			
		};
	}

}
