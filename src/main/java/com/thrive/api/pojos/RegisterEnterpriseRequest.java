package com.thrive.api.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterEnterpriseRequest {
	
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("address1")
	@Expose
	private String address1;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("postcode")
	@Expose
	private String postcode;
	@SerializedName("departments")
	@Expose
	private List<String> departments = null;
	@SerializedName("regions")
	@Expose
	private List<String> regions = null;
	@SerializedName("tok_record")
	@Expose
	private Boolean tokrecord;
	
}
