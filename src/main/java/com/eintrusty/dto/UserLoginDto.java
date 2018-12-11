package com.eintrusty.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eintrusty.security.jwt.JwtResponse;
import com.fasterxml.jackson.annotation.JsonProperty;



public class UserLoginDto {
	
	private String idUser;

	private String username;

	private String password;

	private Integer status;

	private Date createDate;

	private String createBy;

	private Date updateDate;

	private String updateBy;
	
	@JsonProperty("token")
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	List<String> roles = new ArrayList();
	

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public UserLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginDto(String idUser, String username, String password, Integer status, Date createDate,
			String createBy, Date updateDate, String updateBy) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.status = status;
		this.createDate = createDate;
		this.createBy = createBy;
		this.updateDate = updateDate;
		this.updateBy = updateBy;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}
