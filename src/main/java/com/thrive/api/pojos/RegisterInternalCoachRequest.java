package com.thrive.api.pojos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInternalCoachRequest {
	
	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("career_sme_roles")
	@Expose
	private String careerSmeRoles;
	@SerializedName("about")
	@Expose
	private String about;
	@SerializedName("career_corporate_time")
	@Expose
	private Integer careerCorporateTime;
	@SerializedName("career_industries")
	@Expose
	private List<String> careerIndustries = null;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("coach_hours_in_year")
	@Expose
	private String coachHoursInYear;
	@SerializedName("coached_people")
	@Expose
	private List<String> coachedPeople = null;
	@SerializedName("coaching_summary")
	@Expose
	private String coachingSummary;
	@SerializedName("company_name")
	@Expose
	private String companyName;
	@SerializedName("company_type")
	@Expose
	private String comapnyType;
	@SerializedName("company_website")
	@Expose
	private String companyWebsite;
	@SerializedName("corporate_week_time")
	@Expose
	private Integer corporateWeekTime;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("email_updates")
	@Expose
	private Boolean emailUpdates;
	@SerializedName("employment_status")
	@Expose
	private String employmentStatus;
	@SerializedName("gender")
	@Expose
	private String gender;
	@SerializedName("industries")
	@Expose
	private List<Integer> industries = null;
	@SerializedName("languages")
	@Expose
	private List<String> languages = null;
	@SerializedName("latitude")
	@Expose
	private Double latitude;
	@SerializedName("longitude")
	@Expose
	private Double longitude;
	@SerializedName("mobile_number")
	@Expose
	private String mobileNumber;
	@SerializedName("nationality")
	@Expose
	private String nationality;
	@SerializedName("other_senior_position")
	@Expose
	private String otherSeniorPositions;
	@SerializedName("photo")
	@Expose
	private String photo;
	@SerializedName("postcode")
	@Expose
	private String postcode;
	@SerializedName("professional_summary")
	@Expose
	private String professionalSummary;
	@SerializedName("region")
	@Expose
	private String region;
	@SerializedName("selected_locale")
	@Expose
	private String selectedLocale;
	@SerializedName("testimonial")
	@Expose
	private String testimonial;
	@SerializedName("qualifications")
	@Expose
	private List<Qualification> qualifications = null;
	@SerializedName("expertise")
	@Expose
	private List<Expertise> expertise = null;
	@SerializedName("enable_categories")
	@Expose
	private List<EnableCategory> enableCategories = null;
	@SerializedName("address1")
	@Expose
	private String address1;
	@SerializedName("is_sms_reminders_enabled")
	@Expose
	private Boolean issmsremindersenabled;
	@SerializedName("user_details")
	@Expose
	private List<UserDetails> userdetails = null;
	@SerializedName("career_sme_time")
	@Expose
	private Integer careersmetime;
	@SerializedName("company_indemnity_cover")
	@Expose
	private String companyindemnitycover;
	@SerializedName("company_indemnity_provider")
	@Expose
	private String companyindemnityprovider;
	@SerializedName("company_reg_number")
	@Expose
	private String companyregnumber;
	@SerializedName("company_vat_reg_number")
	@Expose
	private String companyvatregnumber;
	@SerializedName("sso_user_id")
	@Expose
	private String ssouserid;
	@SerializedName("company")
	@Expose
	private Integer company;
	@SerializedName("years_as_coach")
	@Expose
	private Integer yearsascoach;
	
	public RegisterInternalCoachRequest(String firstName, String lastName, String password, String careerSmeRoles,
			String about, Integer careerCorporateTime, List<String> careerIndustries, String city,
			String coachHoursInYear, List<String> coachedPeople, String coachingSummary, String companyName,
			String comapnyType, String companyWebsite, Integer corporateWeekTime, String country, String email,
			Boolean emailUpdates, String employmentStatus, String gender, List<Integer> industries,
			List<String> languages, Double latitude, Double longitude, String mobileNumber, String nationality,
			String otherSeniorPositions, String photo, String postcode, String professionalSummary, String region,
			String selectedLocale, String testimonial, List<Qualification> qualifications, List<Expertise> expertise,
			List<EnableCategory> enableCategories, String address1, Boolean issmsremindersenabled,
			List<UserDetails> userdetails, int careersmetime, String companyindemnitycover,
			String companyindemnityprovider, String companyregnumber, String companyvatregnumber, String ssouserid,
			Integer company) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.careerSmeRoles = careerSmeRoles;
		this.about = about;
		this.careerCorporateTime = careerCorporateTime;
		this.careerIndustries = careerIndustries;
		this.city = city;
		this.coachHoursInYear = coachHoursInYear;
		this.coachedPeople = coachedPeople;
		this.coachingSummary = coachingSummary;
		this.companyName = companyName;
		this.comapnyType = comapnyType;
		this.companyWebsite = companyWebsite;
		this.corporateWeekTime = corporateWeekTime;
		this.country = country;
		this.email = email;
		this.emailUpdates = emailUpdates;
		this.employmentStatus = employmentStatus;
		this.gender = gender;
		this.industries = industries;
		this.languages = languages;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mobileNumber = mobileNumber;
		this.nationality = nationality;
		this.otherSeniorPositions = otherSeniorPositions;
		this.photo = photo;
		this.postcode = postcode;
		this.professionalSummary = professionalSummary;
		this.region = region;
		this.selectedLocale = selectedLocale;
		this.testimonial = testimonial;
		this.qualifications = qualifications;
		this.expertise = expertise;
		this.enableCategories = enableCategories;
		this.address1 = address1;
		this.issmsremindersenabled = issmsremindersenabled;
		this.userdetails = userdetails;
		this.careersmetime = careersmetime;
		this.companyindemnitycover = companyindemnitycover;
		this.companyindemnityprovider = companyindemnityprovider;
		this.companyregnumber = companyregnumber;
		this.companyvatregnumber = companyvatregnumber;
		this.ssouserid = ssouserid;
		this.company = company;
	}

	public RegisterInternalCoachRequest(String firstName, String lastName, String password, Integer careerCorporateTime,
			String city, String coachHoursInYear, Integer corporateWeekTime, String country, String email,
			Boolean emailUpdates, String gender, List<Integer> industries, List<String> languages, Double latitude,
			Double longitude, String mobileNumber, String nationality, String photo, String postcode, String region,
			String selectedLocale, List<Expertise> expertise, List<EnableCategory> enableCategories, String address1,
			List<UserDetails> userdetails, int careersmetime, Integer company,String coachingSummary,Integer yearsascoach) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.careerCorporateTime = careerCorporateTime;
		this.city = city;
		this.coachHoursInYear = coachHoursInYear;
		this.corporateWeekTime = corporateWeekTime;
		this.country = country;
		this.email = email;
		this.emailUpdates = emailUpdates;
		this.gender = gender;
		this.industries = industries;
		this.languages = languages;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mobileNumber = mobileNumber;
		this.nationality = nationality;
		this.photo = photo;
		this.postcode = postcode;
		this.region = region;
		this.selectedLocale = selectedLocale;
		this.expertise = expertise;
		this.enableCategories = enableCategories;
		this.address1 = address1;
		this.userdetails = userdetails;
		this.careersmetime = careersmetime;
		this.company = company;
		this.coachingSummary = coachingSummary;
		this.yearsascoach = yearsascoach;
	}
	
	
	
	

}
