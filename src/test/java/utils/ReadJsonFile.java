package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;

public class ReadJsonFile {

	public static String readJson() throws IOException {

		String json = new String(Files.readAllBytes(Paths.get(".\\request\\users.json")));

		return json;

	}

	public static Object updateJson(String key, String value) throws IOException {

		String jsonString = readJson();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		
        JsonObject addressObject = jsonObject.getAsJsonObject("address");
        addressObject.addProperty("$."+key,value);
        String updatedJson = gson.toJson(jsonObject);

		/*
		 * ObjectMapper objectMapper = new ObjectMapper();
		 * 
		 * @SuppressWarnings("unchecked") Map<String, Object> jsonMap =
		 * objectMapper.readValue(jsonString, Map.class);
		 * 
		 * jsonMap.put(key, value); String updatedJsonString =
		 * objectMapper.writeValueAsString(jsonMap);
		 * System.out.println(updatedJsonString);
		 */

		return updatedJson;

	}

	public static void main(String[] args) throws IOException {
		Object json = updateJson("$.telephone.mobile", "01711084452");
		System.out.println(json);

	}

}
