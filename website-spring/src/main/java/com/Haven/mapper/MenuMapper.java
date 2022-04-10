package com.Haven.mapper;

import com.Haven.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 菜单数据库接口 MenuMapper
 *
 * @author HavenJust
 * @date 23:40 周六 09 四月 2022年
 */

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
}
