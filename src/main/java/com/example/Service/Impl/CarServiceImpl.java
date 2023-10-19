package com.example.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Dao.CarMapper;
import com.example.Service.CarService;
import com.example.entity.Car;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
}
