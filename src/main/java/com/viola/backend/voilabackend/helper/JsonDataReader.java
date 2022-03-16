package com.viola.backend.voilabackend.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
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
		}catch(FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + filePath);
		}finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}
	}
}