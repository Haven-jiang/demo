package com.Haven.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单组装中间件 MenuDTO
 *
 * @author HavenJust
 * @date 23:35 周六 09 四月 2022年
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDTO implements Serializable {
    private String name;
    private String path;
    private String icon;
    private Integer level;
    private List<MenuDTO> child;
}
