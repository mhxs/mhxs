package io.mhxs.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.mhxs.test.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {



}
