package com.Haven.entity;

import java.time.LocalDateTime;
/**
 * 文章pojo类
 *
 * @author HavenJust
 * @date 2022/4/7
 */
public class Article {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 作者id
     */
    private Integer userId;
    /**
     * 文章分类
     */
    private Integer typeId;
    /**
     * 标题
     */
    private String articleTitle;
    /**
     * 内容
     */
    private String articleContent;
    /**
     * 是否置顶
     */
    private Integer isTop;
//    /**
//     * 是否删除
//     */
//    private Integer isDelete;
    /**
     * 文章访问权限
     */
    private Integer accessPrem;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
