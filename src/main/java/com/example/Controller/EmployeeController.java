package com.example.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.DTO.EmployeeDto;
import com.example.Service.EmployeeService;
import com.example.common.R;
import com.example.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/employee")
@CrossOrigin
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private com.example.Controller.loginController loginController;

    @PostMapping("/list")
    public R page(@RequestBody Map map) {
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        String name = (String) map.get("name");
        Integer gender = (Integer) map.get("gender");
        Page<Employee> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(name != null, Employee::getName, name);
        lambdaQueryWrapper.eq(gender != null, Employee::getGender, gender);
        employeeService.page(pageinfo, lambdaQueryWrapper);
        pageinfo.setTotal(employeeService.count(lambdaQueryWrapper));
        return R.success(pageinfo);
    }

    @DeleteMapping("/delete")
    public R delete(@RequestBody Map map) {
        Integer id = (Integer) map.get("id");
        boolean byId = employeeService.removeById(id);
        if (byId == false) {
            return R.error("删除失败");
        } else {
            return R.success("");
        }
    }

    @PostMapping("/add")
    public R add(@RequestBody Employee employee) {
        boolean save = employeeService.save(employee);
        if (save)
            return R.success("");
        else
            return R.error("hello world！");
    }

    @PostMapping("/update")
    public R update(@RequestBody Employee employee) {
        boolean save = employeeService.updateById(employee);
        if (save)
            return R.success("");
        else
            return R.error("hello world！");
    }

}
