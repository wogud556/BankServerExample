package com.esum.testbankserver.bankserver.module;


import com.google.gson.JsonParser;
import com.esum.testbankserver.bankserver.dto.BankBook;
import com.esum.testbankserver.bankserver.dto.BankUser;
import com.google.gson.JsonElement;

public class JsonHandler {
	JsonParser parser;
	JsonElement element;
	
//	public BankBook parseBnkBook(String jsonData) {
//		
//	
//	}
	
	public BankUser parseBankUser(String jsonData) {
		BankUser bnkuser = new BankUser();
		
		parser = new JsonParser();
		element = parser.parse(jsonData);
		
		String loginid = element.getAsJsonObject().get("Bnk_user_id").getAsString();
		String loginpwd = element.getAsJsonObject().get("Bnk_user_pwd").getAsString();
		
		bnkuser.setBnk_user_id(loginid);
		bnkuser.setBnk_user_pwd(loginpwd);
		
		return bnkuser;
	}
	
	public BankUser parseFindUser(String jsonData) {
		BankUser bnkuser = new BankUser();
		
		parser = new JsonParser();
		element = parser.parse(jsonData);
		
		String loginid = element.getAsJsonObject().get("Bnk_user_id").getAsString();
		
		bnkuser.setBnk_user_id(loginid);
	
		return bnkuser;
	}

}
