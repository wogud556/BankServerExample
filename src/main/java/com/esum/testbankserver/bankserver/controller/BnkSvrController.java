package com.esum.testbankserver.bankserver.controller;

import java.io.DataInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esum.testbankserver.bankserver.dao.BankMapper;
import com.esum.testbankserver.bankserver.dto.BankBook;
import com.esum.testbankserver.bankserver.dto.BankUser;
import com.esum.testbankserver.bankserver.module.JsonHandler;

@RestController
@RequestMapping(path = "/")
public class BnkSvrController {

	@Autowired
	private BankMapper mapper;

	@PostMapping(value = "/login")
	public String LoginController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankUser bnkuser = new BankUser();
		BankUser dbbnkuser = new BankUser();
		
		String StreamData = null;
		String result = null;
		
		request.setCharacterEncoding("UTF-8");
		DataInputStream dis = new DataInputStream(request.getInputStream());
		
		StringBuffer resultData = new StringBuffer();
		String str = null;
		while ((str = dis.readLine()) != null) {
			StreamData = new String(str.getBytes("ISO-8859-1"), "utf-8");
			resultData.append(StreamData);
		}
		result = resultData.toString();
		System.out.println(result);

		JsonHandler parseJson = new JsonHandler();
		bnkuser = parseJson.parseBankUser(result);
		dbbnkuser = mapper.selectOneUser(bnkuser.getBnk_user_id());
		
		try {
			System.out.println(bnkuser.getBnk_user_id() + " " + bnkuser.getBnk_user_pwd());
			System.out.println(dbbnkuser.getBnk_user_id() + " " + dbbnkuser.getBnk_user_pwd());
			if (bnkuser.getBnk_user_pwd().equals(dbbnkuser.getBnk_user_pwd())) {
				return "true";
			} else {
				return "false";
			}
		} catch (NullPointerException e) {
			return "none";
		}

	}

	@GetMapping(path = "/findBook")
	public BankBook findBankbookController(HttpServletRequest request, HttpServletResponse response) {
		BankBook bnkbook = new BankBook();
		String DBHandler = request.getHeader("DBHandler");

		if ("Select".equals(DBHandler)) {
			bnkbook = mapper.selectOneBankBook("101-11-232-12314");

		}

		return bnkbook;
	}

	@GetMapping(path = "/findAllBook")
	public List<BankBook> findAllBankbookController(HttpServletRequest request, HttpServletResponse response) {
		List<BankBook> list = mapper.selectAllBankBook();
		String DBHandler = request.getHeader("DBHandler");

		return list;
	}

	@GetMapping(path = "/findUser")
	public BankUser findUserController(HttpServletRequest request, HttpServletResponse response) {

		BankUser bnkuser = new BankUser();
		String DBHandler = request.getHeader("DBHandler");
		String bnk_user_uid = request.getHeader("UID");

		bnkuser = mapper.selectOneUser(bnk_user_uid);

		return bnkuser;
	}

	@GetMapping(path = "/findAllUser")
	public List<BankUser> findAllBankUserController(HttpServletRequest request, HttpServletResponse response) {
		List<BankUser> list = mapper.selectAllUser();
		String DBHandler = request.getHeader("DBHandler");

		return list;
	}

}
