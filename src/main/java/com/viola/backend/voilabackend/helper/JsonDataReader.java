package com.viola.backend.voilabackend.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.viola.backend.voilabackend.model.SocialMediaAccounts;

import org.springframework.stereotype.Service;

@Service("jsonDataReader")
public class JsonDataReader {
	private final String FOLDERPATH = "src/test/resources/com/viola/backend/voilabackend/data/";
	
	public SocialMediaAccounts getSocialMediaAccounts() {
		return (SocialMediaAccounts) getSingleObjectFromFile("SocialMedia.json", SocialMediaAccounts.class);
	}

	public SocialMediaAccounts getSocialMediaAccountsUpdated() {
		return (SocialMediaAccounts) getSingleObjectFromFile("SocialMediaUpdated.json", SocialMediaAccounts.class);
	}

	public Object getSingleObjectFromFile(String file, Class clazz) {
		Gson gson = new Gson();
		String filePath = FOLDERPATH + file;
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(filePath));
			Object object = gson.fromJson(bufferReader, clazz);
			return object;
		} catch(FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + filePath);
		} catch(JsonIOException e) {
			throw new RuntimeException("Can not conert to object");
		} finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}
	}

	public String getJsonStringfromFile(String fileName) {
		String filePath = FOLDERPATH + fileName;
		BufferedReader bufferReader = null;
		String result; 
		try {
			result = new String(Files.readAllBytes(Paths.get(filePath)));
    		return result;  
		} catch(IOException e) {
			throw new RuntimeException("Json file not found at path : " + filePath);
		} finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}
	}
}