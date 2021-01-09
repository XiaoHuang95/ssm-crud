package com.blank.service;

import com.blank.bean.Department;
import com.blank.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    /**
     * 查出所有部门信息
     */
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getDepartment(){
        return departmentMapper.selectByExample(null);
    }
}
