package com.esum.testbankserver.bankserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esum.testbankserver.bankserver.dao.BankMapper;
import com.esum.testbankserver.bankserver.dto.BankBook;
import com.esum.testbankserver.bankserver.dto.BankUser;
import com.esum.testbankserver.bankserver.module.JsonHandler;

@RestController
public class BnkSvrController {

	@Autowired
	private BankMapper mapper;
	
	@GetMapping(path="/login")
	public boolean LoginController(HttpServletRequest request, HttpServletResponse response) {
		BankUser bnkuser = new BankUser();
		BankUser result = new BankUser();
		Boolean user = true;
		String DBHandler = request.getHeader("DBHandler");
		String userinfo = request.getHeader("Find");
		userinfo = userinfo.replaceAll("\\\\\"", "");

		
		System.out.println(userinfo);
		JsonHandler handler = new JsonHandler();
		bnkuser = handler.parseBankUser(userinfo);
		System.out.println(bnkuser.getBnk_user_id().replaceAll("\"", ""));
		try {
			result = mapper.selectOneUser(bnkuser.getBnk_user_id().replaceAll("\"", ""));
			System.out.println(result.getBnk_user_id());
			System.out.println(result.getBnk_user_pwd());
			System.out.println(bnkuser.getBnk_user_pwd().replaceAll("\"", ""));
			if(!"null".equals(result.getBnk_user_id())){
				if(bnkuser.getBnk_user_pwd().replaceAll("\"", "").equals(result.getBnk_user_pwd())) {
					System.out.println("맞았습니다.");
					user = true;
				}else {
					System.out.println("틀렸습니다.");
					user = false;
				}
			}
		}catch(Exception e) {
			System.out.println("없네용");
			e.printStackTrace();
		}
//
//		
//		try {
//			
//		}catch(NullPointerException e) {
//			
//		}
//		
		return user;
		
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
