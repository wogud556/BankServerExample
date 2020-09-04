package com.esum.testbankserver.bankserver.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.esum.testbankserver.bankserver.dto.BankBook;
import com.esum.testbankserver.bankserver.dto.BankUID;
import com.esum.testbankserver.bankserver.dto.BankUser;

/*
 * 대충 이런식으로 하라고 작성한 것
 * */
@Mapper
public interface BankMapper {
	public BankBook selectOneBankBook(String book_account_num);
	public List<BankBook> selectAllBankBook ();
	
	public BankUser selectOneUser(String bnk_user_id);
	public List<BankUser> selectAllUser ();
	public int finduserId(String bnk_user_id);
	public void userInsert(HashMap<String, Object> map);
	public BankUID getUID();
	public void InsertMergeUID(HashMap<String, Object> map );
	public List<BankUser> showBankUser();
	public List<BankBook> showBankBook();
	public void insertChit(HashMap<String, Object> map);
	
}
