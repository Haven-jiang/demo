package com.Haven.service.impl;

import com.Haven.dto.MenuDTO;
import com.Haven.entity.Menu;
import com.Haven.mapper.MenuMapper;
import com.Haven.service.MenuService;
import com.Haven.vo.MenuVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单服务实例 MenuServiceImpl
 *
 * @author HavenJust
 * @date 23:59 周六 09 四月 2022年
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public MenuVO menuBuild() {

        checkMenu();


        Integer maxLevel = getMaxLevel();
        Integer curLevel = maxLevel;
        HashMap<Integer, List<MenuDTO>> map = new HashMap<>();
        List<MenuDTO> mainDTO = new ArrayList<>();

        for (; curLevel-1 >= 0 ; curLevel--) {

            if (curLevel-1 == 0) {
                List<Menu> main = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                        .select()
                        .eq(Menu::getMenuLevel, 0)
                );
                for (Menu menu : main) {
                    MenuDTO menuDTO = dtoBuilder(menu, 1);
                    menuDTO.setChild(map.get(menu.getId()));
                    mainDTO.add(menuDTO);
                }
                break;
            }

            for (Menu parent : menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                    .select()
                    .eq(Menu::getMenuLevel, curLevel-2))) {

                List<Menu> in_menus = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                        .select()
                        .eq(Menu::getParentId, parent.getId())
                );
                if (curLevel.equals(maxLevel)) map.put(parent.getId(), dtoBuilders(in_menus, curLevel));
                else {
                    List<MenuDTO> curDTOs = new ArrayList<>();
                    for (Menu menu : in_menus) {
                        MenuDTO menuDTO = dtoBuilder(menu, curLevel);
                        menuDTO.setChild(map.get(menu.getId()));
                        curDTOs.add(menuDTO);
                    }
                    map.put(parent.getId(), curDTOs);
                }

            }
        }
        return MenuVO
                .builder()
                .menuList(mainDTO)
                .build();

    }

    public void checkMenu() {

        List<Integer> idList = new ArrayList<>();
        for (Menu menu : menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId))) idList.add(menu.getId());
        for (Integer integer : idList) {
            List<Integer> childId = new ArrayList<>();
            for (Menu menu : menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                    .select(Menu::getId, Menu::getParentId)
                    .eq(Menu::getParentId, integer))) childId.add(menu.getId());

            if (!childId.isEmpty())
                menuMapper.update(new Menu(), new LambdaUpdateWrapper<Menu>()
                                                      .set(Menu::getChildId, JSON.toJSONString(childId))
                                                      .eq(Menu::getId, integer)
                );
            else menuMapper.update(new Menu(), new LambdaUpdateWrapper<Menu>()
                        .set(Menu::getChildId, JSON.toJSONString(childId))
                        .set(Menu::getPath, "/pages/new")
                        .eq(Menu::getId, integer)
                        .eq(Menu::getPath, "")
            );
        }
    }

    private Integer getMaxLevel() {

        List<Menu> menus = new ArrayList<>(List.of(new Menu()));
        int level = 0;
        while (menus.size() != 0)
            menus = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                    .select()
                    .eq(Menu::getMenuLevel, level++)
            );
        return level-1;
    }

    private List<MenuDTO> dtoBuilders(List<Menu> menus, Integer level) {
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (Menu menu : menus) {
            menuDTOS.add(dtoBuilder(menu, level));
        }
        return menuDTOS;
    }

    private MenuDTO dtoBuilder(Menu menu, Integer level) {
        return MenuDTO.builder()
                .name(menu.getMenuName())
                .icon(menu.getPictureId())
                .path(menu.getPath())
                .level(level)
                .build();
    }


}
