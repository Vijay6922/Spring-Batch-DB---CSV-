package com.example.DB_to_CSV.Entity;


public class Department {
	
	private int departmentId;
	private String departmentName;
	private int managerName;
	private int managerId;
	public Department(int departmentId, String departmentName, int managerName, int managerId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerName = managerName;
		this.managerId = managerId;
	}
	public Department() {
		super();
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getManagerName() {
		return managerName;
	}
	public void setManagerName(int managerName) {
		this.managerName = managerName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	

}
