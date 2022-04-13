package com.Haven.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 首页面向传输类 HomePageDTO
 *
 * @author HavenJust
 * @date 23:51 周一 11 四月 2022年
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomePageDTO implements Serializable {
    private List<functionPlate> headPlate;
    private class functionPlate {
        private Integer value;
        private String describe;
        private String icon;
    }
}
