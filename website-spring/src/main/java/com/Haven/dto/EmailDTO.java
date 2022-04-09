package com.Haven.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 邮件 DTO (中间件)
 *
 * @author HavenJust
 * @date 2022/4/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    /**
     * receivers 接收者地址
     */
    private String receivers;
    /**
     * subject 标题
     */
    private String subject;
    /**
     * content 内容
     */
    private String content;
}
