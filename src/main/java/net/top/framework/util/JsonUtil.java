package net.top.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.top.framework.domain.Account;
import net.top.framework.domain.Role;

public class JsonUtil {
	
	private static ObjectMapper objectmapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);//.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	/**
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
     * @return
     */
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		T obj = null;
		try {
			obj = objectmapper.readValue(json, clazz);
			return obj;
		} catch(Exception e) {
			System.out.println("无法把对象序列化为json: " + e);
		}
		return null;
	}

	/**
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
     * @return
     */
	public static <T> T jsonToObject(byte[] json, Class<T> clazz) {
		T obj = null;
		try {
			obj = objectmapper.readValue(json, clazz);
		} catch (Exception e) {
			System.out.println("无法把对象序列化为json: " + e);
		}
		return obj;
	}

	/**
	 *
	 * @param json
	 * @param t
	 * @param <T>
     * @return
     */
	public static <T> T jsonToObject(String json, TypeReference t) {
		T obj = null;
		try {
			obj = objectmapper.readValue(json, t);
		} catch (Exception e) {
			System.out.println("无法把对象序列化为json: " + e);
		}
		return obj;
	}

	/**
	 *
	 * @param obj
	 * @return
     */
	public static byte[] objectToJson(Object obj) {
		byte[] json = null;
        try {
            json = objectmapper.writeValueAsBytes(obj);
        } catch (Exception e) {
        	System.out.println("无法把对象序列化为json: " + e);
        }
        return json;
	}

	public static String objectToJsonString(Object obj) {
		String json = null;
		try {
			json = objectmapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String getProperty(String json,String property){
		Map<String,String> obj=jsonToObject(json, HashMap.class);
		return obj.get(property);
	}
	
	public static void main(String[] args) {
		Account account = new Account();
		account.setId(1);
		account.setAccount("123");
		account.setRoleNumber(1);
		account.setRole(new Role());
		String json = objectToJsonString(account);
		System.out.println(json);
	}
	
}
