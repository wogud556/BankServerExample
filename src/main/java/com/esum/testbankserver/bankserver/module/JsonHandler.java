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
		
		String loginid = element.getAsJsonObject().get("bnk_user_id").toString();
		String loginpwd = element.getAsJsonObject().get("bnk_user_pwd").toString();
		
		bnkuser.setBnk_user_id(loginid);
		bnkuser.setBnk_user_pwd(loginpwd);
		
		return bnkuser;
	}

}
