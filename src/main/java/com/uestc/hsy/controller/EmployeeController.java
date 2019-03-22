package com.uestc.hsy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uestc.hsy.bean.Department;
import com.uestc.hsy.bean.Employee;
import com.uestc.hsy.bean.Msg;
import com.uestc.hsy.service.DepartmentService;
import com.uestc.hsy.service.EmployeeService;

/**
 * ����Ա��CRUD����
 * @author Shiyou Houn
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids") String ids){
		if(ids.contains("-")){
			String[] str=ids.split("-");
			List<Integer> del_ids = new ArrayList<>();
			for (String string : str) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids); 
			
		}else{
			int id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
		
	}
	
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg SaveEmp(Employee employee){
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName){
		//前端校验
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是6-16数字和字母组合或2-5位中文！");
		}
		//数据库校验
		boolean chechuser = employeeService.checkuser(empName);
		if(chechuser) return Msg.success();
		else return Msg.fail().add("va_msg", "用户名已存在");
	}
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn, Model model) {
		
		//����pageHelper
		//����ҳ�뼰ÿҳ��С
		PageHelper.startPage(pn,5);
 		List<Employee> list =employeeService.getAll();
		PageInfo page = new PageInfo(list,5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			Map<String, Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
		employeeService.saveEmp(employee);
		return  Msg.success();
		}
	}
	/**
	 * 导入jackson包，将对象转为json对象
	 * 返回json，可以用于网页解析和Android以及ios解析，增加扩展性,需要ajax
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(
			@RequestParam(value="pn",defaultValue="1") Integer pn ) {
		
		//����pageHelper
		//����ҳ�뼰ÿҳ��С
		PageHelper.startPage(pn,5);
		List<Employee> list =employeeService.getAll();
		PageInfo<Employee> page = new PageInfo<>(list,5);
		return Msg.success().add("pageInfo", page);
	}
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDeptsWithJson() {
		List<Department> list =departmentService.getDepts();
		return Msg.success().add("department", list);
	}
}
