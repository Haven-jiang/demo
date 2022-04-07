package com.Haven.entity;

import java.util.List;

public class Menu {
    /**
     * 菜单唯一标识
     */
    private Integer id;
    /**
     * 菜单名
     */
    private String MenuName;
    /**
     * 菜单指向路径
     */
    private String path;
    /**
     * 菜单等级
     */
    private Integer menuLevel;
    /**
     * 菜单logo
     */
    private Integer pictureId;
    /**
     * 子菜单id
     */
    private List<Integer> childId;
}
