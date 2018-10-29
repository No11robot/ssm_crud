package jxnu.ldz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import jxnu.ldz.bean.Employee;
import jxnu.ldz.bean.EmployeeExample;
import jxnu.ldz.bean.EmployeeExample.Criteria;
import jxnu.ldz.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	/*
	 * 查询所有员工
	 */
	public List<Employee> getAll(){
		
		return employeeMapper.selectByExampleWhithDept(null);
	}
	//员工保存
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	//检查用户名
	public boolean checkUser(@RequestParam("empName")String empName) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		int count=employeeMapper.countByExample(example);
		return count==0;
	}
	public Employee getEmp(Integer id) {
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	//员工更新
	public void updateEmp(Employee employee) {

		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}
	//删除员工
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
		
	}
	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
}
