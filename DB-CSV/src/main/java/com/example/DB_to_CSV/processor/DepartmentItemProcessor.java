package com.example.DB_to_CSV.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.DB_to_CSV.Entity.Department;

public class DepartmentItemProcessor implements ItemProcessor<Department,Department>{

	@Override
	public Department process(Department item) throws Exception {
		return item;
	}
}
