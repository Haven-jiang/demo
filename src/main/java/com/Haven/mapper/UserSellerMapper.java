package com.Haven.mapper;

import com.Haven.entity.UserSeller;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserSellerMapper extends BaseMapper<UserSeller> {
}
