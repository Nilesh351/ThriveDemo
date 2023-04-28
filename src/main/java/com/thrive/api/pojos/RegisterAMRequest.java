package com.thrive.api.pojos;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterAMRequest {
	
	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("age_range")
	@Expose
	private String agerange;
	@SerializedName("gender")
	@Expose
	private String gender;
	@SerializedName("nationality")
	@Expose
	private String nationality;
	@SerializedName("email_updates")
	@Expose
	private Boolean emailUpdates;
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
	@SerializedName("selected_locale")
	@Expose
	private String selectedLocale;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("photo")
	@Expose
	private String photo;

	
	

	public RegisterAMRequest(String firstName, String lastName, String password, String agerange, String gender,
			String nationality, Boolean emailUpdates, String address1, Object address2, String city, String county,
			String state, String postcode, String selectedLocale, String email, String photo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.agerange = agerange;
		this.gender = gender;
		this.nationality = nationality;
		this.emailUpdates = emailUpdates;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.state = state;
		this.postcode = postcode;
		this.selectedLocale = selectedLocale;
		this.email = email;
		this.photo = photo;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getPassword() {
		return password;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getAgerange() {
		return agerange;
	}


	public String getGender() {
		return gender;
	}


	public String getNationality() {
		return nationality;
	}


	public Boolean getEmailUpdates() {
		return emailUpdates;
	}


	public String getAddress1() {
		return address1;
	}


	public Object getAddress2() {
		return address2;
	}


	public String getCity() {
		return city;
	}


	public String getCounty() {
		return county;
	}


	public String getState() {
		return state;
	}


	public String getPostcode() {
		return postcode;
	}


	public String getSelectedLocale() {
		return selectedLocale;
	}


	public String getEmail() {
		return email;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAgerange(String agerange) {
		this.agerange = agerange;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public void setEmailUpdates(Boolean emailUpdates) {
		this.emailUpdates = emailUpdates;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public void setAddress2(Object address2) {
		this.address2 = address2;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public void setSelectedLocale(String selectedLocale) {
		this.selectedLocale = selectedLocale;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	


}
