package com.Haven.vo;

import com.Haven.dto.MenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单前端类 MenuVO
 *
 * @author HavenJust
 * @date 23:19 周六 09 四月 2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVO {
    private List<MenuDTO> menuList;
}
