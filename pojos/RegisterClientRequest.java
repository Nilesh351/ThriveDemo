package com.heaptrace.thrive.api.response.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterClientRequest {

	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("mobile_number")
	@Expose
	private String mobileNumber;
	@SerializedName("languages")
	@Expose
	private List<String> languages = null;
	@SerializedName("is_sms_reminders_enabled")
	@Expose
	private Boolean isSmsRemindersEnabled;
	@SerializedName("address1")
	@Expose
	private String address1;
	@SerializedName("address2")
	@Expose
	private Object address2;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("county")
	@Expose
	private String county;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("postcode")
	@Expose
	private String postcode;
	@SerializedName("latitude")
	@Expose
	private Double latitude;
	@SerializedName("longitude")
	@Expose
	private Double longitude;
	@SerializedName("department")
	@Expose
	private String department;
	@SerializedName("position")
	@Expose
	private String position;
	@SerializedName("region")
	@Expose
	private String region;
	@SerializedName("work_duration")
	@Expose
	private String workDuration;
	@SerializedName("email_updates")
	@Expose
	private Boolean emailUpdates;
	@SerializedName("sso_user_id")
	@Expose
	private String ssoUserId;
	@SerializedName("selected_locale")
	@Expose
	private String selectedLocale;
	@SerializedName("photo")
	@Expose
	private Object photo;
	@SerializedName("email")
	@Expose
	private String email;
	

	public RegisterClientRequest(String firstName, String lastName, String password, String mobileNumber,
			List<String> languages, Boolean isSmsRemindersEnabled, String address1, Object address2, String city,
			String county, String state, String postcode, Double latitude, Double longitude, String department,
			String position, String region, String workDuration, Boolean emailUpdates, String ssoUserId,
			String selectedLocale, Object photo, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.languages = languages;
		this.isSmsRemindersEnabled = isSmsRemindersEnabled;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.state = state;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.department = department;
		this.position = position;
		this.region = region;
		this.workDuration = workDuration;
		this.emailUpdates = emailUpdates;
		this.ssoUserId = ssoUserId;
		this.selectedLocale = selectedLocale;
		this.photo = photo;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public Boolean getIsSmsRemindersEnabled() {
		return isSmsRemindersEnabled;
	}

	public void setIsSmsRemindersEnabled(Boolean isSmsRemindersEnabled) {
		this.isSmsRemindersEnabled = isSmsRemindersEnabled;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Object getAddress2() {
		return address2;
	}

	public void setAddress2(Object address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(String workDuration) {
		this.workDuration = workDuration;
	}

	public Boolean getEmailUpdates() {
		return emailUpdates;
	}

	public void setEmailUpdates(Boolean emailUpdates) {
		this.emailUpdates = emailUpdates;
	}

	public String getSsoUserId() {
		return ssoUserId;
	}

	public void setSsoUserId(String ssoUserId) {
		this.ssoUserId = ssoUserId;
	}

	public String getSelectedLocale() {
		return selectedLocale;
	}

	public void setSelectedLocale(String selectedLocale) {
		this.selectedLocale = selectedLocale;
	}

	public Object getPhoto() {
		return photo;
	}

	public void setPhoto(Object photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
