package com.sonu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonu.dao.UserDao;
import com.sonu.entites.User;

@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public int saveUser(User user) {
		int i = userDao.save(user);
		return i;
	}

}
