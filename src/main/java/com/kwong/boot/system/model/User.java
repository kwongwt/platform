package com.kwong.boot.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author kwong 用户表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user")
public class User extends Entitys implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 账号
	 */
	@Column(nullable = false, unique = true)
	private String username;
	/**
	 * 密码
	 */
	@Column(nullable = false)
	private String password;
	/**
	 * 密码盐
	 */
	private String salt;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 邮箱
	 */
	@Column(nullable = false, unique = true)
	private String email;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 部门
	 */
	private Integer deptId;
	/**
	 * 创建时间
	 */
	@Column(nullable = false, name = "create_time")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@Column(nullable = false, name = "edit_time")
	private Date editTime;
	/**
	 * 状态(1启用，2冻结，3删除）
	 */
	@Column(nullable = false)
	private Integer status;
	/**
	 * 最后登陆时间
	 */
	@Column(nullable = false, name = "last_login_time")
	private Date lastLoginTime;
	/**
	 * 用户角色连接表
	 */
	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "sys_link_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
