package com.esum.testbankserver.bankserver.vo;

import lombok.Data;

@Data
public class BankUser {
	String bnk_user_uid;
	String bnk_user_id;
	String bnk_user_pwd;
	String bnk_user_name;
	int bnk_user_account_count;
	String bnk_user_last_update;
	String bnk_user_level;

}
