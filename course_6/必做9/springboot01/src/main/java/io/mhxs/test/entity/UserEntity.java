package io.mhxs.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sys_user")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String pwd;

    private String realname;

    private String mobile;

    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;


}