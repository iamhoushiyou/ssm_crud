package com.uestc.hsy.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uestc.hsy.bean.Department;
import com.uestc.hsy.bean.Employee;
import com.uestc.hsy.controller.EmployeeController;
import com.uestc.hsy.dao.DepartmentMapper;
import com.uestc.hsy.dao.EmployeeMapper;
import com.uestc.hsy.service.DepartmentService;
import com.uestc.hsy.service.EmployeeService;

/**
 * ����dao��Ĺ���
 * @author Shiyou Houn
 */
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	DepartmentService departmentService;
 
	/**
	 * ����DepartmentMapper
	 */
	 @Test
	public void testCRUD(){
		
	   /*//1������SpringIOC����
		ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
		//2���������л�ȡMapper
		ioc.getBean(DepartmentMapper.class);*/
		//System.out.println(departmentMapper);
		//1�����뼸������
		/*departmentMapper.insertSelective(new Department(null, "������"));
		departmentMapper.insertSelective(new Department(null, "ҵ��"));
		*/
		//2������Ա�����ݡ�����Ա������
		//employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@uestc", 1));
		 //3������������Ա��   uuid sqlSession
		/* EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		 for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@uestc", 1));
		}*/
		 
		 employeeService.saveEmp(new Employee(null, "张三", "M", "zhangsan@qq.com", 1));
		 
	}
}
