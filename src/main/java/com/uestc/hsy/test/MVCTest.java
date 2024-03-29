package com.uestc.hsy.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.uestc.hsy.bean.Employee;

/**
 * 使用Spring提供的测试请求功能
 * @author Shiyou Houn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {

	//传入SpringMvc的ioc
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求，获取处理结果
	MockMvc mockMvc;
	
	@Before
	public void initMockMvc(){
		
		mockMvc =MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception{
		//模拟请求拿到返回值
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "4")).andReturn();
		//请求成功请求域中有PageInfo
		MockHttpServletRequest request = result.getRequest();
		PageInfo  pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码："+pi.getPageNum());
		System.out.println("总页码："+pi.getPages());
		System.out.println("总记录数："+pi.getTotal());
		System.out.println("连续的页码数：");
		int[] nums = pi.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(" "+ i);
		}
		
		//获取员工数据
		List<Employee> list =pi.getList();
		for (Employee employee : list) {
			System.out.println("ID: "+employee.getEmpId()+"==>Name: "+employee.getEmpName());
		}
	}
}
