package com.eintrusty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eintrusty.dto.UserLoginDto;
import com.eintrusty.entity.UserLogin;
import com.eintrusty.repo.IUserLoginRepository;
import com.eintrusty.security.jwt.JwtProvider;
import com.eintrusty.security.jwt.JwtResponse;
import com.eintrusty.utility.DateUtil;
import com.eintrusty.utility.GeneratedUtil;
import com.eintrusty.utility.StringUtil;

@Service
@Transactional
public class UserLoginService implements IUserLoginService {

	@Autowired
	private IUserLoginRepository userRepo;
	
	
	@Value("${eintrusty.app.regex1}")
    private String regex1;
 
	@Value("${eintrusty.app.regex2}")
    private String regex2;

	@Autowired
    JwtProvider jwtProvider;
	
	
	@Override
	public Map<String, Object> findAllUserLogin() {
		// TODO Auto-generated method stub
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			List<UserLoginDto> listUserLoginDto = new ArrayList();
			userRepo.findAll().forEach(r -> {
				UserLoginDto dto = new UserLoginDto();
				dto.setIdUser(r.getIdUser());
				dto.setUsername(r.getUsername());
				dto.setStatus(r.getStatus());
				dto.setCreateDate(r.getCreateDate());
				dto.setCreateBy(r.getCreateBy());
				dto.setUpdateDate(r.getUpdateDate());
				dto.setUpdateBy(r.getUpdateBy());
				listUserLoginDto.add(dto);
			});
			datas.put("data", listUserLoginDto);
			return datas;

		} catch (Exception e) {
			String error = "ERROR " + e.getMessage();
			
			datas.put("error", error);

			return datas;
		}

	}

	@Override
	public Map<String, Object> saveUserLogin(UserLoginDto userSave) {
		// TODO Auto-generated method stub
		Map<String,Object> datas = new HashMap<String,Object>();
		try {
			UserLogin user = new UserLogin();
			String uuid = GeneratedUtil.generatedUUID();
			user.setCreateBy(userSave.getCreateBy());
			user.setCreateDate(DateUtil.getNow());
			user.setIdUser(uuid);
			user.setPassword(userSave.getPassword());
			user.setStatus(0);
			user.setUsername(userSave.getUsername());
			userRepo.save(user);
			return datas;
		}catch(Exception e){
			String errorValue = "ERROR " + e.getMessage();
			datas.put("error", errorValue);
			return datas;
		}
		
		
	}

	@Override
	public Map<String, Object> updateUserLogin(UserLoginDto userUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deactiveUserLogin(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findOneById(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> login(UserLoginDto userLogin) {
		// TODO Auto-generated method stub
		Map<String,Object> mapData = new HashMap<>();
		try {
			System.out.println("username " + userLogin.getUsername()+ " password" + userLogin.getPassword() );
			//UserLogin user = userRepo.findUserLoginWithUsernameAndPassword(userLogin.getUsername(),userLogin.getPassword());
			
			List<Object[]> listUser = userRepo.findUserRoleUsingUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
			
			
			if(listUser.size() > 0) {
				List<String> roles = new ArrayList();
				String username = (String) listUser.get(0)[0];
				listUser.forEach(object -> {
					String role = (String)object[1];
					//username = (String)object[0];
					roles.add(role);
					
;				});
				 System.out.println("id user " + username);
				  UserLoginDto dto = new UserLoginDto();
				  //dto.setIdUser(user.getIdUser());
				  dto.setUsername(username);
				  //dto.setStatus(user.getStatus());
				  dto.setRoles(roles);
				  String bodyToken = StringUtil.buildingBodyTokenUsernameAndRole(username, regex1, roles, regex2);
				  StringBuffer token = new StringBuffer("Bearer ");
				  token.append(jwtProvider.generateJwtTokenCustom(bodyToken));
				  
				  
				  //jwtProvider.generateJwtTokenCustom(bodyToken); 
				  //JwtResponse jwtResponse = new JwtResponse(jwt);
				  dto.setToken(token.toString());
				mapData.put("data", dto);

				return mapData;
			}else {
				
				mapData.put("no data", "UserName tidak di temukan");
				return mapData;
			}
			
		}catch(Exception e) {
			String error = "ERROR " + e.getMessage();
			System.out.println("ERROR " + error);
			mapData.put("ERROR", error);
			return mapData;
		}
		//return mapData;
		
		
	}

	@Override
	public Map<String, Object> createUserLogin(UserLoginDto userLogin) {
		// TODO Auto-generated method stub
		Map<String,Object> mapData = new HashMap<String,Object>();
		try{
			UserLogin user = new UserLogin();
			//String uuid = GeneratedUtil.generatedUUID();
			user.setCreateBy(userLogin.getCreateBy());
			user.setCreateDate(DateUtil.getNow());
			//user.setIdUser(uuid);
			user.setPassword(userLogin.getPassword());
			user.setStatus(0);
			user.setUsername(userLogin.getUsername());
			userRepo.save(user);
			return null;
		}catch(Exception e){
			String errorInService = "ERROR IN SERVICE " + e;
			mapData.put("ERROR", errorInService);
			return mapData;
		}
		
		
		
	}

}
