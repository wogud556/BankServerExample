package com.esum.testbankserver.bankserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esum.testbankserver.bankserver.dao.BankBookMapper;
import com.esum.testbankserver.bankserver.dto.BankBook;
import com.esum.testbankserver.bankserver.dto.BankUser;

@RestController
public class BnkSvrController {

	@Autowired
	private BankBookMapper mapper;
	
	@GetMapping(path="/login")
	public String LoginController(HttpServletRequest request, HttpServletResponse response) {
		
		return "로그인 페이지입니다 아직 아무것도 없음";
	}
	@GetMapping(path="/findBook")
	public BankBook findBankbookController(HttpServletRequest request, HttpServletResponse response) {
		BankBook bnkbook = new BankBook();
		String DBHandler = request.getHeader("DBHandler");
		
		if("Select".equals(DBHandler)) {
			bnkbook = mapper.selectOneBankBook("101-11-232-12314");
		
		}
		
		return bnkbook;
	}
	@GetMapping(path="/findAllBook")
	public List<BankBook> findAllBankbookController(HttpServletRequest request, HttpServletResponse response) {
		List<BankBook> list = mapper.selectAllBankBook();
		String DBHandler = request.getHeader("DBHandler");
		
		return list;
	}
	
	@GetMapping(path="/findUser")
	public BankUser findUserController(HttpServletRequest request, HttpServletResponse response) {
		
		BankUser bnkuser = new BankUser();
		String DBHandler = request.getHeader("DBHandler");
		String bnk_user_uid = request.getHeader("UID");
		
		bnkuser = mapper.selectOneUser(bnk_user_uid);
		
		return bnkuser;
	}
	
	@GetMapping(path="/findAllUser")
	public List<BankUser> findAllBankUserController(HttpServletRequest request, HttpServletResponse response) {
		List<BankUser> list = mapper.selectAllUser();
		String DBHandler = request.getHeader("DBHandler");
		
		return list;
	}

}
