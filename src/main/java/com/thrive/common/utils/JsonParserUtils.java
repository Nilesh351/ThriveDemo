package com.thrive.common.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class JsonParserUtils {
	
    private static final Logger LOGGER = LogManager.getLogger(JsonParserUtils.class);
	
	public void parseJason() {
		
		JsonParser jsonParser = new JsonParser();
		/*
		 * InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
		 * "C:\\Users\\gauri\\eclipse-workspace\\elimu-sapphire-automation\\src\\test\\java\\com\\elimu\\sapphire\\ui\\test\\organization\\OrganizationData.json"
		 * );
		 * 
		 * Reader reader = new InputStreamReader(inputStream);
		 */
		JsonElement jsonElement = null;
		try {
			jsonElement = jsonParser.parse(new FileReader("path.json"));
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		
		Gson gson = new Gson();
	
	}
	

}

