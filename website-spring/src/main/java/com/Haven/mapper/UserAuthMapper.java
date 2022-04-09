package com.Haven.mapper;

import com.Haven.entity.UserAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserAuthMapper extends BaseMapper<UserAuth> {

}
