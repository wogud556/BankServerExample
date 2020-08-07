package com.esum.testbankserver.bankserver.controller;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		
		result = getHttpData(request);
		
		System.out.println(result);

		JsonHandler parseJson = new JsonHandler();
		bnkuser = parseJson.parseBankUser(result);
		dbbnkuser = mapper.selectOneUser(bnkuser.getBnk_user_id());
		
		try {
			System.out.println(bnkuser.getBnk_user_id() + " " + bnkuser.getBnk_user_pwd());
			if (bnkuser.getBnk_user_pwd().equals(dbbnkuser.getBnk_user_pwd())) {
				return "true";
			} else {
				return "false";
			}
		} catch (NullPointerException e) {
			return "none";
		}

	}
	
	@PostMapping(path = "/findUserId")
	public String findUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankUser bnkuser = new BankUser();
		int dbresult;
		
		String StreamData = null;
		String result = null;
		
		result = getHttpData(request);
		
		System.out.println(result);

		JsonHandler parseJson = new JsonHandler();
		bnkuser = parseJson.parseFindUser(result);
		dbresult = mapper.finduserId(bnkuser.getBnk_user_id());
		response.setCharacterEncoding("EUC-KR");
		try {
			System.out.println(dbresult);
			if (dbresult >= 1) {
				
				return "true";
				
			} else {
				return "false";
				
			}
		} catch (NullPointerException e) {
			return "none";
		}
	}
	
	@PostMapping(path = "/userinsert")
	public void insertUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankUser bnkuser = new BankUser();
		Date date = new Date();
		
		SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String Now = nowDate.format(date);
		System.out.println(Now);
		
		int dbresult;
		
		String StreamData = null;
		String result = null;
		
		result = getHttpData(request);
		
		System.out.println(result);
		
		JsonHandler parseJson = new JsonHandler();
		bnkuser = parseJson.parseInsertUser(result);
		bnkuser.setBnk_user_uid("A12345");
		bnkuser.setBnk_user_account_count("0");
		bnkuser.setBnk_user_last_update(Now);
		bnkuser.setBnk_user_level("M");
		
		response.setCharacterEncoding("EUC-KR");
		try {
			mapper.userInsert(bnkuser);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getHttpData(HttpServletRequest request) throws Exception{
		String StreamData = null;
		String result = null;
		
		request.setCharacterEncoding("UTF-8");
		DataInputStream dis = new DataInputStream(request.getInputStream()) ;
		
		StringBuffer resultData = new StringBuffer();
		String str = null;
		while ((str = dis.readLine()) != null) {
			StreamData = new String(str.getBytes("ISO-8859-1"), "utf-8");
			resultData.append(StreamData);
		}
		result = resultData.toString();
		System.out.println(result);
		
		return result;
	}
}
