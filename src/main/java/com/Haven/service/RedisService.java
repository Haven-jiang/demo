package com.Haven.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface RedisService {
    /**
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     *
     * @param key
     * @param value
     * @param time
     */
    void set(String key, Object value, long time);

    /**
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     *
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     *
     * @param keys
     * @return
     */
    Long delete(List<String> keys);

    /**
     *
     * @param key
     * @param time
     * @return
     */
    Boolean setExpire(String key, long time);

    /**
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     *
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key, long delta);

    /**
     *
     * @param key
     * @param time
     * @return
     */
    Long incExpire(String key, long time);

    /**
     *
     * @param key
     * @param delta
     * @return
     */
    Long decrement(String key, long delta);

    /**
     *
     * @param key
     * @param hashkey
     * @return
     */
    Object getHash(String key, String hashkey);

    /**
     *
     * @param key
     * @param hashcode
     * @param value
     * @param time
     * @return
     */
    Boolean setHash(String key, String hashcode, Object value, long time);

    /**
     *
     * @param key
     * @param hashcode
     * @param value
     */
    void setHash(String key, String hashcode, Object value);

    /**
     *
     * @param key
     * @return
     */
    Map<String, Object> getAllHash(String key);

    /**
     *
     * @param key
     * @param map
     * @param time
     * @return
     */
    Boolean setAllHash(String key, Map<String, Object> map, long time);

    /**
     *
     * @param key
     * @param map
     */
    void setAllHash(String key, Map<String, Object> map);
    /**
     *
     */
}
