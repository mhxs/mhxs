package com.example.redisdemo.mapper;

import com.example.redisdemo.entiey.StockEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StockMapper {

    @ResultType(StockEntity.class)
    @Select("select id,stock_num stockNum from tb_stock where id = #{id}")
    StockEntity find(@Param("id") Long id);

    @Update("update tb_stock set stock_num = stock_num - 1 where id = #{id}")
    void reduceStock(@Param("id") Long id);

}
