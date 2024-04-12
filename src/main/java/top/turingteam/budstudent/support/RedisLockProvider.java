package top.turingteam.budstudent.support;

/**
 * Redis锁提供者
 * @author Raqtpie
 */
public interface RedisLockProvider {
    /**
     * 尝试获取锁
     * @param key 锁的key
     * @param value 锁的value
     * @param expireTime 锁的过期时间
     * @return 是否获取成功
     */
    boolean tryLock(String key, String value, long expireTime);

    /**
     * 释放锁
     * @param key 锁的key
     * @param value 锁的value
     */
    void releaseLock(String key, String value);
}
