package com.thrive.api.data_utils;

public enum ApiSharedData {

	RESPONSE_STATUS_CODE_SUCESS("200"),
	RESPONSE_STATUS_RESPONSE_THRESHOLD_MS("2000"),
	
	//Header Keys
	CONNECTION_KEY("Connection"),
	SERVER_KEY("Server"),
	CONTENT_TYPE_KEY("Content-Type"),
	TRANSFER_ENCODING_KEY("Transfer-Encoding"),
	VARY_KEY("Vary"),
	CACHE_CONTROL_KEY("Cache-Control"),
	ACCESS_CONTROL_ALLOW_ORIGIN_KEY("Access-Control-Allow-Origin"),
	ACCESS_CONTROL_ALLOW_HEADERS_KEY("Access-Control-Allow-Headers"),
	ACCESS_CONTROL_ALLOW_METHODS_KEY("Access-Control-Allow-Methods"),
	ACCESS_CONTROL_EXPOSE_HEADERS_KEY("Access-Control-Expose-Headers"),
	ACCESS_CONTROL_ALLOW_CREDENTIALS_KEY("Access-Control-Allow-Credentials"),
	X_DEBUG_TOKEN_KEY("X-Debug-Token"),
	X_DEBUG_TOKEN_LINK_KEY("X-Debug-Token-Link"),
	X_ROBOTS_TAG_KEY("X-Robots-Tag"),
	STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
	X_FRAME_OPTIONS("X-Frame-Options"),
	CONTENT_ENCODING("Content-Encoding"),
	VIA("Via"),
	
	
	//Header Values
	;
	private String value;

	private ApiSharedData(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
