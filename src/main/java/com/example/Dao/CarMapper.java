package com.example.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Car;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper extends BaseMapper<Car> {
}
