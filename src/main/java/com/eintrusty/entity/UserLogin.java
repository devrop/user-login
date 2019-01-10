package com.eintrusty.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="erc_user_login")
public class UserLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8838383730964834737L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name="iduser")
	private String idUser;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="status")
	private Integer status;
	@Column(name="createdate")
	private Date createDate;
	@Column(name="createby")
	private String createBy;
	@Column(name="updatedate")
	private Date updateDate;
	@Column(name="updateby")
	private String updateBy;
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserLogin(String idUser, String username, String password, Integer status, Date createDate, String createBy,
			Date updateDate, String updateBy) {
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
