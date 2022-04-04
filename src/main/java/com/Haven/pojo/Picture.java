package com.Haven.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 图片类 - 继承于文件类
 */
public class Picture extends Document{
    /**
     * 图床url地址 : 与Document中byte[]数据二选一
     */
    private String url;
    /**
     * 是否使用url元素 0 禁用 1 使用
     */
    private Integer inFigure;
    /**
     * 图片类型:头像 轮播图 ...
     */
    private Integer pictureType;
}
