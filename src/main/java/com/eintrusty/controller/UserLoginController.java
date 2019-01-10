package com.eintrusty.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eintrusty.dto.UserLoginDto;
import com.eintrusty.rest.DataRest;
import com.eintrusty.service.IUserLoginService;
import com.eintrusty.utility.StringUtil;
import com.eintrusty.variable.VConstant;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserLoginController {

	@Autowired
	private IUserLoginService userService;

	@GetMapping(value = "user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserLoginDto>> getAllUSerLogin() {
		Map<String, Object> datas = userService.findAllUserLogin();
		if (datas.get("error") == null) {
			List<UserLoginDto> list = (List<UserLoginDto>) datas.get("data");

			return new ResponseEntity<List<UserLoginDto>>(list, HttpStatus.OK);
		}

		return null;

	}

	@PostMapping(value = "user/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DataRest> saveUserLogin(@RequestBody UserLoginDto user) {
		DataRest dataRest = new DataRest();
		try {
			Map<String, Object> datasMap = userService.saveUserLogin(user);
			dataRest.setMessage(VConstant.MESSAGESTATUSOK);
			return new ResponseEntity<DataRest>(dataRest, HttpStatus.OK);
		} catch (Exception e) {
			dataRest.setMessage(VConstant.MESSAGESTATUSERROR);
			return new ResponseEntity<DataRest>(dataRest, HttpStatus.OK);
		}

	}
	@PostMapping(value = "registration")
	public ResponseEntity<DataRest> registration(@RequestBody UserLoginDto userRegister){
		DataRest dataRest = new DataRest();
		try{
			Map<String, Object> dataMap = userService.createUserLogin(userRegister);
			if(dataMap !=null){
				dataRest.setMessage(VConstant.MESSAGESTATUSOK);
				return new ResponseEntity<DataRest>(dataRest, HttpStatus.OK);
			}
			String error = (String) dataMap.get("ERROR");
			dataRest.setMessage(VConstant.MESSAGESTATUSERROR);
			return new ResponseEntity<DataRest>(dataRest, HttpStatus.OK);
			
			
		}catch(Exception e){
			dataRest.setMessage(VConstant.MESSAGESTATUSERROR);
			return new ResponseEntity<DataRest>(dataRest, HttpStatus.OK);
		}
		
	}

	
	
	
	
	@PostMapping(value = "login", produces = "application/json")
	public ResponseEntity<UserLoginDto> loginUser(@RequestBody UserLoginDto user) {
		Map<String, Object> datasMap = userService.login(user);
		UserLoginDto userDto = new UserLoginDto();
		try {

			if (datasMap.get("data") != null) {
				userDto = (UserLoginDto) datasMap.get("data");		

			} else if (datasMap.get("no data") != null) {
				userDto.setUsername((String) datasMap.get("no data"));
			}
			

			
			
			return new ResponseEntity<UserLoginDto>(userDto, HttpStatus.OK);

		} catch (Exception e) {

			userDto.setUsername("ERROR" + e.getMessage());
			return new ResponseEntity<UserLoginDto>(userDto, HttpStatus.OK);
		}

	}
	@GetMapping("user/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}

}
