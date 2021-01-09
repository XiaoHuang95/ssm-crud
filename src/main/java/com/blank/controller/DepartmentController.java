package com.blank.controller;

import com.blank.bean.Department;
import com.blank.bean.Msg;
import com.blank.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 查询部门信息
 */
@Controller
public class DepartmentController {
    @Autowired
   DepartmentService departmentService;
    @RequestMapping(value = "/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list = departmentService.getDepartment();
        return Msg.success().add("depts",list);
    }
}
