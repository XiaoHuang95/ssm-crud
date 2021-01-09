package com.blank.controller;

import com.blank.bean.Employee;
import com.blank.bean.Msg;
import com.blank.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 查询员工数据，分页查询
     *
     * @return
     */
    @RequestMapping(value = "/emps")
    @ResponseBody
    public Msg getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", page);//会被框架转为json数据
    }

    /**
     * 新增员工信息
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
//    添加数据前线进行后端的数据校验
    public Msg addEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()){
            //校验失败返回失败，提取错误信息返回给前端显示
            //保存错误信息
            Map<String,Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError :fieldErrors){
                System.out.println("错误字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("fieldErrors",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }
    /**
     * 检查用户名的合法性
     * 支持JSR303校验
     * 导入Hibernate-Validator
     */
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkEmpName(@RequestParam("empName") String empName){
        //在检查用户名合法性之前，先校验用户名是否输入正确
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2e80-\\u9fff]{2,5})";
        if (!empName.matches(regx)){
            return Msg.fail().add("va_Msg","用户名应为6-16位英文字符或者2-5五位中文字符");
        }
        //数据库用户名重复校验
        boolean b = employeeService.checkName(empName);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_Msg","用户名不可用");
        }
    }

    /**
     * 获取员工信息，按id查询
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    /**
     * 更新员工数据
     * ajax直接发送PUT请求引发的血案：
     * PUT请求，请求体中的数据，request.getParameter("gender");拿不到
     * Tomcat一看是PUT请求就不会封装m请求体中的数据为map，只有POST请求才会封装
     *
     * 我们要能够支持直接发送PUT类的请求还要封装请求体中的数据
     * 需要在web.xml中配置过滤器FormContentFilter
     * 该过滤器的作用：将请求体中的数据解析包装成一个map
     * request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Msg updateEmp(Employee employee){
        System.out.println(employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String [] str_ids = ids.split("-");
            //组装变量ids集合
            for (String str:str_ids){
                del_ids.add(Integer.parseInt(str));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmpById(id);
        }
        return Msg.success();
    }
    /**
     * 查询员工数据，分页查询
     *
     * @return
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //在查询之前引入分页插件，传入页码，以及每页所展示的条数
        PageHelper.startPage(pn, 5);
        //startPage后的查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就好了
        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", page);
        return "list";
    }
}
