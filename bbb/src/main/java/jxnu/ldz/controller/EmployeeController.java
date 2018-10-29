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
	 * ��ҳ��ѯԱ��
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getgetEmpsWhithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//����Json�ķ�ʽ
		//����PageHelper��ҳ���
		//�ڲ�ѯǰֻ�����,����ҳ�뼰size
		PageHelper.startPage(pn, 5);
		//startPage�½����ŵľ��Ƿ�ҳ��ѯ
		List<Employee> emps=employeeService.getAll();
		//ʹ��pageInfo��װ��ѯ����,zֻ�轫pageInfo����ҳ��
		//��װ����ϸ��ҳ��Ϣ����ѯ����������,������ʾҳ��5
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	/*
	 * ���Ա��
	 */
	@RequestMapping(value="/emp.action",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//���У��ʧ��
			Map<String,Object> map=new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("�����ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
	}
	/*
	 * ��ѯԱ����Ϣ
	 */
	@RequestMapping("/editEmp")
	@ResponseBody
	public Msg getEmp(@RequestParam("id")Integer id){
		
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/*
	 * ɾ��Ա��
	 */
	@RequestMapping("/deleteEmp")
	@ResponseBody
	public Msg deleteEmpById(@RequestParam("id")String ids) {
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//��װid����
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			//����ɾ��
			employeeService.deleteBatch(del_ids);
		}else{
			//����ɾ��
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	//�������Ա�����Ƿ��Ѵ���
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(String empName) {
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û�������6-16λ��ĸ�����ӣ�2-5����");
		}
		boolean b= employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û����Ѵ���");
		}		
	}
	/*
	 * �޸�Ա��
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
		//����PageHelper��ҳ���
		//�ڲ�ѯǰֻ�����,����ҳ�뼰size
		PageHelper.startPage(pn, 5);
		//startPage�½����ŵľ��Ƿ�ҳ��ѯ
		List<Employee> emps=employeeService.getAll();
		//ʹ��pageInfo��װ��ѯ����,zֻ�轫pageInfo����ҳ��
		//��װ����ϸ��ҳ��Ϣ����ѯ����������,������ʾҳ��5
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		return "list";
		
	}
}
