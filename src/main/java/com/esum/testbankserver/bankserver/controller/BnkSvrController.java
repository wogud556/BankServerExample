package com.esum.testbankserver.bankserver.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esum.testbankserver.bankserver.vo.BankBook;

@RestController
public class BnkSvrController {

	
	
	@GetMapping(path="/login")
	public BankBook LoginController() {
		Date date = new Date();
		SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat realtime = new SimpleDateFormat("HH:mm:ss");
		
		
		
		
		BankBook testbook = new BankBook();
		testbook.setBnk_book_account_num("11-1111-1111");
		testbook.setBnk_user_uid("112b23v-sd");
		testbook.setBnk_book_deposit_price(2000);
		testbook.setBnk_book_withdraw_price(5000);
		testbook.setBnk_total_price(3000);
		testbook.setBnk_book_tra_date(todayDate.format(date).toString() + " " + realtime.format(date).toString());
		
		return testbook;
	}

}
