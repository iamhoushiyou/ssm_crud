package com.uestc.hsy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.hsy.bean.Employee;
import com.uestc.hsy.bean.EmployeeExample;
import com.uestc.hsy.bean.EmployeeExample.Criteria;
import com.uestc.hsy.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	public List<Employee> getAll() {

		EmployeeExample example=new EmployeeExample();
		example.setOrderByClause("emp_id ASC");
		return employeeMapper.selectByExampleWithDept(example);
	}
	
	public void saveEmp(Employee employee) {

		employeeMapper.insertSelective(employee);
	}

	/**
	 * 
	 * @param empName
	 * @return true  
	 * is ok
	 */
	public boolean checkuser(String empName) {
		EmployeeExample example =new EmployeeExample();
		Criteria createCriteria = example.createCriteria();
	    createCriteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}

	public Employee getEmp(Integer id) {
		  
		return employeeMapper.selectByPrimaryKey(id);
	}

	public void updateEmp(Employee employee) {

		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void deleteEmp(Integer id) {
		
		employeeMapper.deleteByPrimaryKey(id);
		
	}

	public void deleteBatch(List<Integer> ids) {
		
		EmployeeExample example =new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
