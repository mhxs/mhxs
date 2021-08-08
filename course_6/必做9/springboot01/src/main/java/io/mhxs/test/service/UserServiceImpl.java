package io.mhxs.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.mhxs.test.entity.UserEntity;
import io.mhxs.test.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public void insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile("1111");
        userEntity.setUsername("1111");
        userEntity.setRealname("1111");
        userEntity.setCreateBy(-1L);
        userEntity.setUpdateBy(-1L);
        userEntity.setCreateTime(new Date());
        userEntity.setPwd("111");
        userEntity.setUpdateTime(new Date());
        baseMapper.insert(userEntity);
    }

    @Override
    public UserEntity getbyId(Long id) {
        return baseMapper.selectById(id);
    }
}
