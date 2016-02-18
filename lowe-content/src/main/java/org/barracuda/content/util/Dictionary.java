package org.barracuda.content.util;

import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Dictionary {

	/**
	 * The collection of string - string mappings
	 */
	private final Map<String, String> dictionary;

	/**
	 * 
	 * @param dictionary
	 */
	private Dictionary(Map<String, String> dictionary) {
		this.dictionary = dictionary;
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	public static Dictionary load(String resource) {
		return new Dictionary(new Gson().fromJson(new InputStreamReader(ClassLoader.getSystemResourceAsStream(resource)), 
				new TypeToken<Map<String, String>>(){}.getType()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return dictionary.get(key);
	}

}