package com.blank.test;

import com.blank.bean.Department;
import com.blank.bean.Employee;
import com.blank.bean.EmployeeExample;
import com.blank.dao.DepartmentMapper;
import com.blank.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐Spring的项目可以使用Spring的单元测试，可以自动注入我们需要的组件
 * <p>
 * 1、导入SpringTest模块
 * 2、@ContextConfiguration指定Spring配置文件的位置
 * ，@RunWith(SpringJUnit4ClassRunner.class)让测试运行于Spring测试环境
 * 3、直接autowired要使用的组件即可
 */
//第二中测试方法
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置文件的路径
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml"})
public class MapperTest {
    //自动注入
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
/*        //1.创建Spring容器
        String config = "conf/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //2.从容器中获取mapper
        DepartmentMapper mapper = ac.getBean(DepartmentMapper.class);
        //插入部门
        int res = mapper.insertSelective(new Department(null,"研发部"));
        System.out.println(res);*/

        //departmentMapper.insertSelective(new Department(null, "测试部"));

        //生成员工数据
        //employeeMapper.insertSelective(new Employee(null,"jerry","Male","Jerry7739@163.com",1));
        //批量插入员工,使用sqlSession
 /*       for (){
            employeeMapper.insertSelective(new Employee(null,"jerry","Male","Jerry7739@163.com",1));

        }*/
        //批量插入员工,使用sqlSession
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i=0;i<1000;i++){
//            String uuid = UUID.randomUUID().toString().substring(0,5)+i;
//            mapper.insertSelective(new Employee(null,uuid,"Female",uuid+"@blank.com",1));
//        }
//        System.out.println("批量插入成功");
        //查询数据
         Employee employee =  employeeMapper.selectByPrimaryKeyWithDept(1);
         System.out.println(employee.getDepartment().getDeptName());
//        修改数据
//         int res = employeeMapper.updateByPrimaryKey(new Employee(1,"jerry","male","jerry3309@163.com",1));
//         System.out.println(res);

//       删除数据
//        int res = employeeMapper.deleteByPrimaryKey(3);
//        System.out.println(res);
    }
}
