package com.example.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Service.CarService;
import com.example.common.R;
import com.example.entity.Car;
import com.example.entity.Employee;
import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/car")
@Slf4j
public class CarController {
    @Autowired
    private CarService carService;


    @PostMapping("/list")
    public R page(@RequestBody Map map) {
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        String carNumber = (String) map.get("carNumber");
        Integer status = (Integer) map.get("status");
        Page<Car> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Car> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(carNumber != null, Car::getCarNumber, carNumber);
        lambdaQueryWrapper.eq(status != null, Car::getStatus, status);
        carService.page(pageinfo, lambdaQueryWrapper);
        pageinfo.setTotal(carService.count(lambdaQueryWrapper));
        return R.success(pageinfo);
    }


    @DeleteMapping("/delete")
    public R delete(@RequestBody Map map) {
        String carNumber = (String) map.get("carNumber");
        LambdaQueryWrapper<Car> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Car::getCarNumber, carNumber);

        boolean remove = carService.remove(lambdaQueryWrapper);
        if (remove) {
            return R.success("nice");
        } else {
            return R.error("error");
        }
    }

    @PostMapping("/add")
    public R add(@RequestBody Car car) {
        boolean save = carService.save(car);
        if (save)
            return R.success("");
        else
            return R.error("hello world！");
    }

    @PostMapping("/update")
    public R update(@RequestBody Car car) {
        LambdaQueryWrapper<Car> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Car::getCarNumber, car.getCarNumber());
        boolean update = carService.update(car, lambdaQueryWrapper);

        if (update)
            return R.success("");
        else
            return R.error("hello world！");
    }
}
