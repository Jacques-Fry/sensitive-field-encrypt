package com.huihe.sensitive.core.utils;

import com.huihe.sensitive.core.annotation.SensitiveEntity;
import com.huihe.sensitive.core.annotation.SensitiveField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.SecureRandom;

/**
 * 加密工具类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/02/01 10:55
 */
public class SensitiveAesUtil {

    /**
     * 默认密钥
     */
    public static final String DEFAULT_KEY = "jacques";
    /**
     * 默认字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 加密位数
     */
    private static final int KEY_SIZE_AES = 128;

    /**
     * 处理一个加密/解密请求
     *
     * @param data        数据
     * @param secretKey   密钥
     * @param isEncrypted 加密/解密
     * @author Jacques·Fry
     * @since 2021/02/01 13:27
     */
    public static void processOne(Object data, String secretKey, boolean isEncrypted) throws IllegalAccessException {

        // 排除不带 @SensitiveEntity 注解的实体类
        if (null == AnnotationUtils.findAnnotation(data.getClass(), SensitiveEntity.class)) {
            return;
        }

        // 捕获类中的所有字段
        Field[] fields = data.getClass().getDeclaredFields();

        // 遍历所有字段
        for (Field field : fields) {
            // 若该字段被SensitiveField注解,则进行解密/加密
            Class<?> fieldType = field.getType();

            /// log.info("字段类型：{} ， 字段名称：{}", fieldType, field.getName());

            // 只针对String类型的字段加解密
            if (fieldType == String.class) {
                if (null != AnnotationUtils.findAnnotation(field, SensitiveField.class)) {
                    // 设置private类型允许访问
                    field.setAccessible(Boolean.TRUE);
                    handleField(data, field, secretKey, isEncrypted);
                    field.setAccessible(Boolean.FALSE);
                }
            }
        }
    }

    /**
     * 处理字段加密
     *
     * @param item        属性值
     * @param field       字段
     * @param secretKey   密钥
     * @param isEncrypted 加密/解密
     * @author Jacques·Fry
     * @since 2021/02/01 10:41
     */
    private static void handleField(Object item, Field field, String secretKey, boolean isEncrypted)
            throws IllegalAccessException {

        if (null == item) {
            return;
        }
        String dataStr = (String) field.get(item);
        if (StringUtils.isBlank(dataStr)) {
            return;
        }
        String value;
        if (isEncrypted) {
            value = aesEncode(dataStr, secretKey);
        } else {
            value = aesDeCode(dataStr, secretKey);
        }
        field.set(item, value);

    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     */
    public static String aesEncode(String data, String key) {
        return keyGeneratorEs(data, key, true);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     */
    public static String aesDeCode(String data, String key) {
        return keyGeneratorEs(data, key, false);
    }

    /**
     * 加解密处理
     *
     * @param data     需要加密的数据
     * @param key      密钥
     * @param isEncode 是编码
     * @return {@link String }
     * @author Jacques·Fry
     * @since 2021/02/01 10:38
     */
    private static String keyGeneratorEs(String data, String key, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            byte[] keyBytes;
            if (SensitiveAesUtil.KEY_SIZE_AES == 0) {
                keyBytes = DEFAULT_CHARSET == null ? key.getBytes() : key.getBytes(DEFAULT_CHARSET);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(SensitiveAesUtil.KEY_SIZE_AES);
            } else {
                keyBytes = key.getBytes(DEFAULT_CHARSET);
                kg.init(SensitiveAesUtil.KEY_SIZE_AES, new SecureRandom(keyBytes));
            }

            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            if (isEncode) {
                cipher.init(1, sks);
                byte[] resBytes = data.getBytes(DEFAULT_CHARSET);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(2, sks);
                String result = new String(cipher.doFinal(parseHexStr2Byte(data)));
                return StringUtils.isBlank(result) ? data : result;
            }
        } catch (Exception var10) {
            return data;
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buff 需要转换的二进制数据
     */
    private static String parseByte2HexStr(byte[] buff) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buff) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 转换的字符
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (StringUtils.isBlank(hexStr)) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
