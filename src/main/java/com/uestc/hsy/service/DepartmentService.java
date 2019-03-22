package com.uestc.hsy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.hsy.bean.Department;
import com.uestc.hsy.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentMapper departmentMapper;
	public List<Department> getDepts() {
		
        return departmentMapper.selectByExample(null);
	}

}
