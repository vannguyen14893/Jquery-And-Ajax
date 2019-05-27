package com.example.demo.utils;

import java.util.List;

public class UserFilterKeyword {
	private String sortName;
	private Boolean sort;
	private String name;
	private String fullName;
	private Integer page = 1;
	private Integer pageSize = 5;
    private Integer pageNumber;
	private Integer status;
	private Integer roleId;
	private List<Integer>  roleIds;
	public Integer roleId1;
	public Integer roleId2;
	private String startDate;
	private String endDate;
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Boolean getSort() {
		return sort;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getRoleId1() {
		return roleId1;
	}

	public void setRoleId1(Integer roleId1) {
		this.roleId1 = roleId1;
	}

	public Integer getRoleId2() {
		return roleId2;
	}

	public void setRoleId2(Integer roleId2) {
		this.roleId2 = roleId2;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}	

}
