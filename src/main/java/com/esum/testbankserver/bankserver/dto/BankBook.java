package com.esum.testbankserver.bankserver.dto;

import lombok.Data;

@Data
public class BankBook {
	String bnk_user_uid;
	String bnk_book_account_num;
	String bnk_book_tra_date;
	int bnk_book_deposit_price;
	int bnk_book_withdraw_price;
	int bnk_total_price;

}
