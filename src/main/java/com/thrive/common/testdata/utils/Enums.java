package com.thrive.common.testdata.utils;

public class Enums {
	
	public enum Gender {
		
		MALE("Male"),
		FEMALE("Female");
		
		private String value;
		
		private Gender(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}

	
	public enum LocalizationLanguage {
		
		ENGLISH_UK("english-uk"),
		ENGLISH_US("english-us"),
		FRENCH("French");
		
		private String value;
		
		private LocalizationLanguage(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		
	}

}
