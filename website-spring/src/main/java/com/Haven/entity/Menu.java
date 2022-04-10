package com.Haven.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单类
 *
 * @author HavenJust
 * @date 2022/4/7
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu implements Serializable {
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
    private String pictureId;
    /**
     * 子菜单id
     */
    private List<Integer> childId;
    private Integer parentId;

    public List<Integer> getChild() {
        return childId;
    }

    public String getChildId() {
        return JSON.toJSONString(childId);
    }

    public void setChildId(String childId) {
        this.childId = JSON.parseObject(childId, List.class);
    }
}
