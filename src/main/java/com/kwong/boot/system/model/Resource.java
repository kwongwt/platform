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
 * @author kwong 资源表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_resource")
public class Resource extends Entitys implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名称
	 */
	@Column(nullable = false)
	private String name;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 父编号
	 */
	private String pcode;
	/**
	 * 所有父编号
	 */
	private String pcodes;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * url地址
	 */
	private String url;
	/**
	 * 排序号
	 */
	private Integer num;
	/**
	 * 层级
	 */
	private Integer levels;
	/**
	 * 菜单类型（1:菜单，2：按钮）
	 */
	@Column(name = "resource_type")
	private Integer resourceType;
	/**
	 * 菜单状态 : 1:启用 0:不启用
	 */
	@Column(name = "is_use")
	private Integer useStatus;
	/**
	 * 是否打开: 1:打开 0:不打开
	 */
	@Column(name = "is_open")
	private Integer openStatus;
	/**
	 * 备注
	 */
	private String tips;
    /**
     * 	用户角色连接表
     */
    @ManyToMany(fetch= FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_link_role_resource", joinColumns = { @JoinColumn(name = "resource_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    private List<Role> roleList;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodes() {
		return pcodes;
	}

	public void setPcodes(String pcodes) {
		this.pcodes = pcodes;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
