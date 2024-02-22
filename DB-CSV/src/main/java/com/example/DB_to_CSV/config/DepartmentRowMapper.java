package com.example.DB_to_CSV.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.DB_to_CSV.Entity.Department;

public class DepartmentRowMapper implements RowMapper<Department> {

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department d= new Department();
		d.setDepartmentId(rs.getInt("departmentId"));
		d.setDepartmentName(rs.getString("departmentName"));
		d.setManagerId(rs.getInt("managerId"));
		d.setManagerName(rs.getInt("managerName"));
		return d;
	}

}
