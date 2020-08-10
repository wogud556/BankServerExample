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

	public BankUser parseInsertUser(String jsonData) {
		BankUser bnkuser = new BankUser();
		
		parser = new JsonParser();
		element = parser.parse(jsonData);
		
		String userid = element.getAsJsonObject().get("Bnk_user_id").getAsString();
		String userpwd = element.getAsJsonObject().get("Bnk_user_pwd").getAsString();
		String username = element.getAsJsonObject().get("Bnk_user_name").getAsString();
		
		bnkuser.setBnk_user_id(userid);
		bnkuser.setBnk_user_pwd(userpwd);
		bnkuser.setBnk_user_name(username);
		
		System.out.println(userid + userpwd + username);
		
		return bnkuser;
	}

}
