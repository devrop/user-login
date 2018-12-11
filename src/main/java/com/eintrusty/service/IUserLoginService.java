package com.eintrusty.service;

import java.util.Map;

import com.eintrusty.dto.UserLoginDto;

public interface IUserLoginService {
	
	public Map<String,Object> findAllUserLogin();
	public Map<String,Object> saveUserLogin(UserLoginDto userSave);
	public Map<String,Object> updateUserLogin(UserLoginDto userUpdate);
	public Map<String,Object> deactiveUserLogin(String idUser);
	public Map<String,Object> findOneById(String idUser);
	public Map<String,Object> login(UserLoginDto userLogin);
	

}
