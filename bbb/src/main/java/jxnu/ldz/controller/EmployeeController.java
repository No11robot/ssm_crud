package jxnu.ldz.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxnu.ldz.bean.*;
import jxnu.ldz.service.EmployeeService;


@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	/*
	 * 分页查询员工
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getgetEmpsWhithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//返回Json的方式
		//引入PageHelper分页插件
		//在查询前只需调用,传入页码及size
		PageHelper.startPage(pn, 5);
		//startPage下紧跟着的就是分页查询
		List<Employee> emps=employeeService.getAll();
		//使用pageInfo包装查询后结果,z只需将pageInfo交给页面
		//封装了详细分页信息及查询出来的数据,连续显示页数5
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	/*
	 * 添加员工
	 */
	@RequestMapping(value="/emp.action",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//后端校验失败
			Map<String,Object> map=new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
	}
	/*
	 * 查询员工信息
	 */
	@RequestMapping("/editEmp")
	@ResponseBody
	public Msg getEmp(@RequestParam("id")Integer id){
		
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/*
	 * 删除员工
	 */
	@RequestMapping("/deleteEmp")
	@ResponseBody
	public Msg deleteEmpById(@RequestParam("id")String ids) {
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id数组
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			//批量删除
			employeeService.deleteBatch(del_ids);
		}else{
			//单个删除
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	//检查新增员工名是否已存在
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(String empName) {
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须6-16位字母及数子，2-5汉字");
		}
		boolean b= employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名已存在");
		}		
	}
	/*
	 * 修改员工
	 */
	@RequestMapping("/updateEmp")
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//引入PageHelper分页插件
		//在查询前只需调用,传入页码及size
		PageHelper.startPage(pn, 5);
		//startPage下紧跟着的就是分页查询
		List<Employee> emps=employeeService.getAll();
		//使用pageInfo包装查询后结果,z只需将pageInfo交给页面
		//封装了详细分页信息及查询出来的数据,连续显示页数5
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		return "list";
		
	}
}
