package com.eintrusty.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eintrusty.entity.UserLogin;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface IUserLoginRepository extends CrudRepository<UserLogin, String> {
	
	
	@Query("select a from UserLogin a where a.username =:username and a.password =:password")
	public UserLogin findUserLoginWithUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query(value="SELECT ul.username, r.rolename  FROM erc_user_role ur " + 
			"join erc_user_login ul " + 
			"on ur.id_user = ul.iduser " + 
			"join erc_role r on " + 
			"r.idrole = ur.id_role" + 
			" where ul.iduser =?1", nativeQuery=true)
	public List<Object[]> findRoleWithIdUser(String userId);
	
	
	@Query(value="SELECT ul.username, r.rolename  FROM erc_user_role ur " + 
			"join erc_user_login ul " + 
			"on ur.id_user = ul.iduser " + 
			"join erc_role r on " + 
			"r.idrole = ur.id_role " + 
			" where ul.username =?1 And ul.password =?2", nativeQuery=true)
	public List<Object[]> findUserRoleUsingUsernameAndPassword(String username,String password);
	

}
