package jxnu.ldz.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jxnu.ldz.bean.Department;
import jxnu.ldz.bean.Employee;
import jxnu.ldz.dao.DepartmentMapper;
import jxnu.ldz.dao.EmployeeMapper;

/*
 * ����dao
 * �Ƽ�Spring��Ŀ�Ϳ���ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע����Ҫ�����
 * 1.����SpringTest��jar��
 * 2.@ContextConfigurationָ��Spring�����ļ�λ��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCrud() {
	/*	//1.����SpringIOC����
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.�������л�ȡmapper
		DepartmentMapper bean=ioc.getBean(DepartmentMapper.class);
	*/
		System.out.println(departmentMapper);
		//���뼸������
		/*departmentMapper.insertSelective(new Department(1,"������"));
		departmentMapper.insertSelective(new Department(2,"���Բ�"));*/
		//Ա������
//		employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));
	//3.����������Ա����ʹ�ÿ���ִ������������sqlSession��
		
		/*for() {
			employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));
		}*/
		/*EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@sss.com",1));
		}
		System.out.println("�ɹ�");*/
		//��ѯ����
		Employee employee=employeeMapper.selectByPrimaryKeyWhithDept(1);
		System.out.println(employee.getEmpId()+employee.getEmpName()+employee.getDepartment().getDeptName());
	}
}
