package com.com.com.firebase;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.com.com.firebase.service.FirebaseServiceImpl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

@Controller
public class FirebaseController {

	@Resource(name = "fservice")
	FirebaseServiceImpl firebaseService;
	
	@RequestMapping(value = "/google/login")
	public String googleLogin() throws Exception {
		// Initialize the default app
		FirebaseConfig firebase = new FirebaseConfig();
		FirebaseApp defaultApp = firebase.init();
		
		System.out.println(defaultApp.getName());  // "[DEFAULT]

		// Retrieve services by passing the defaultApp variable...
		FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
		FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

		// ... or use the equivalent shorthand notation
		defaultAuth = FirebaseAuth.getInstance();
		defaultDatabase = FirebaseDatabase.getInstance();
		
		return "firebase/goLogin";
	}
	
	@RequestMapping(value = "/google/regist")
	public String regist() {
		
		return "firebase/regist";
	}
	
	@RequestMapping(value = "/google/registProc")
	public @ResponseBody boolean registProc(HttpSession session, @RequestParam Map <String, Object> map ) {
		boolean idOverapCheck = firebaseService.idCheck(map);
		
		return idOverapCheck;
	}
	@RequestMapping(value = "/google/logon")
	public @ResponseBody Map logon(HttpSession session, @RequestParam Map <String, Object> map ) {
		Map<String, Object> resultMap = firebaseService.getUser(map);
		
		return resultMap;
	}
//	session.setAttribute("userid", map.get("userid"));
//	session.setAttribute("userpw", map.get("userpw"));
//	session.setAttribute("username", map.get("username"));
//	session.setAttribute("email", map.get("email"));
//	session.setAttribute("phone", map.get("phone"));
//	
//	firebaseService.createUser(map);
}
