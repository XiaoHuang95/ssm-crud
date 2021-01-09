package com.blank.service;

import com.blank.bean.Employee;
import com.blank.bean.EmployeeExample;
import com.blank.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    //按id查询员工信息
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 查出所有员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 员工保存方法
     * @param employee
     * @return
     */
    public void saveEmp(Employee employee) {
       employeeMapper.insertSelective(employee);
    }

    /**
     * 校验用户名是否正确
     * @param empName
     * @return 0 true可用，1 false不可用
     */
    public boolean checkName(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 员工更新
     * @param employee
     */
    public void updateEmp(Employee employee) {
        //按照主键有选择的更新
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 按照员工id删除
     * @param id
     */
    public void deleteEmpById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    //批量删除
    public void deleteBatch(List<Integer> ids){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
