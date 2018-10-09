package com.kwong.boot.system.model;

import java.io.Serializable;
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
 * @author kwong 角色表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role")
public class Role extends Entitys implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 	父角色id
	 */
	private Integer pid;
	/**
	 * 	角色名称
	 */
	@Column(nullable = false, unique = true)
	private String name;
	/**
	 * 	部门id
	 */
	@Column(name = "dept_id")
	private String deptId;
	/**
	 * 	备注
	 */
	private String tips;
    /**
     * 	 角色资源连接表
     */
    @ManyToMany(fetch= FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_link_role_resource",joinColumns = {@JoinColumn(name="role_id")}, inverseJoinColumns = {@JoinColumn(name="resource_id")})
    private List<Resource> resourceList;
    /**
     * 	用户角色连接表
     */
    @ManyToMany(fetch= FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_link_user_role", joinColumns = { @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
