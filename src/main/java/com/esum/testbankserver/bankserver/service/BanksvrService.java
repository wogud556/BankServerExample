package com.esum.testbankserver.bankserver.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.esum.testbankserver.bankserver.dao.BankMapper;
import com.esum.testbankserver.bankserver.dto.BankUID;
import com.esum.testbankserver.bankserver.dto.BankUser;

public class BanksvrService {
	
	@Autowired
	BankMapper mapper; 
	
	public BankUser selectOneUser(String bnk_user_id) {
		return mapper.selectOneUser(bnk_user_id);
	}
	
	public int findUserId(String bnk_user_id) {
		return mapper.finduserId(bnk_user_id);
	}
	
	public void userInsert(HashMap<String, Object> userdata) {
		mapper.userInsert(userdata);
	}
	
	public BankUID getUserUID() {
		return mapper.getUID();
	}
	
	public void insertUserUID(HashMap<String, Object> map) {
		mapper.InsertMergeUID(map);
	}
	
}
