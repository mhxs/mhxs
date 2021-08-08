package io.mhxs.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.mhxs.test.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    void insert();

    UserEntity getbyId(Long id);

}
