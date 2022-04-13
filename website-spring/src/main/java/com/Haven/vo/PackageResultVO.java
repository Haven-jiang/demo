package com.Haven.vo;

import com.Haven.enums.StatusCodeEnum;
import lombok.*;

import java.io.Serializable;

import static com.Haven.enums.StatusCodeEnum.*;

/**
 * 返回值包装面向前端类 PackageResultVO
 *
 * @author HavenJust
 * @date 22:43 周一 11 四月 2022年
 */

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PackageResultVO<ElemType> implements Serializable {
    private Boolean flag;
    private Integer code;
    private String message;
    private ElemType data;

    private static <ElemType> PackageResultVO<ElemType> success() {
        return packaging(true, SUCCESS.getCode(), SUCCESS.getDescribe(), null);
    }

    private static <ElemType> PackageResultVO<ElemType> success(ElemType data) {
        return packaging(true, SUCCESS.getCode(), SUCCESS.getDescribe(), data);
    }

    private static <ElemType> PackageResultVO<ElemType> success(ElemType data, String message) {
        return packaging(true, SUCCESS.getCode(), message, data);
    }

    public static <ElemType> PackageResultVO<ElemType> error() {
        return packaging(false, FAIL.getCode(), FAIL.getDescribe(), null);
    }

    public static <ElemType> PackageResultVO<ElemType> error(StatusCodeEnum statusCodeEnum) {
        return packaging(false, statusCodeEnum.getCode(), statusCodeEnum.getDescribe(), null);
    }

    public static <ElemType> PackageResultVO<ElemType> error(String message) {
        return packaging(false, message);
    }

    public static <ElemType> PackageResultVO<ElemType> error(ElemType data) {
        return packaging(false, FAIL.getCode(), FAIL.getDescribe(), data);
    }

    public static <ElemType> PackageResultVO<ElemType> error(
            ElemType data
            , String message
    ) {
        return packaging(false, FAIL.getCode(), message, data);
    }

    public static <ElemType> PackageResultVO<ElemType> error(
            Integer code
            , String message
    ) {
        return packaging(false, code, message, null);
    }

    private static <T> PackageResultVO<T> packaging(
            Boolean flag
            , String message
    ) {
        PackageResultVO<T> resultPackage = new PackageResultVO<>();
        resultPackage.setFlag(flag);
        resultPackage.setCode(flag ? SUCCESS.getCode() : FAIL.getCode());
        resultPackage.setMessage(message);
        return resultPackage;
    }

    private static <ElemType> PackageResultVO<ElemType> packaging(
            Boolean flag,
            Integer code,
            String message,
            ElemType data
    ) {
        PackageResultVO<ElemType> resultPackage = new PackageResultVO<>();
        resultPackage.setFlag(flag);
        resultPackage.setCode(code);
        resultPackage.setMessage(message);
        resultPackage.setData(data);
        return resultPackage;
    }
}
