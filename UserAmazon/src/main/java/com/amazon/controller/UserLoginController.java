package com.amazon.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amazon.entity.User;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/amazon/user")
public class UserLoginController {

	@Autowired
	SessionFactory sessionFactory;
 
	@PostMapping("/login")
	public boolean userLogin(@RequestBody User userformbrower) {
		System.out.println(userformbrower);
		Session session = sessionFactory.openSession();
		User userformdb = session.get(User.class, userformbrower.getUserId());
		
		System.out.println(userformdb);
		boolean result = userformdb.equals(userformbrower);
		
		if(result) {
			System.out.println("user Login");
			return true;
		}
		else {
			return false;
		}
			

	}
}
