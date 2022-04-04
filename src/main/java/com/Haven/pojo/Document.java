package com.Haven.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    /**
     * Document 唯一标识
     */
    private Integer id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小 单位字节
     */
    private Integer fileSize;
    /**
     * 文件类型:txt jpg png ...
     */
    private String fileType;
    /**
     * 文件内容:picture中可以为空
     */
    private byte[] content;
    /**
     * 文件描述
     */
    private String describe;
}
