package top.turingteam.budstudent.support.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import top.turingteam.budstudent.support.RedisLockProvider;

import java.util.Collections;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class RedisLockProviderImpl implements RedisLockProvider {
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean tryLock(String key, String value, long expireTime) {
        try {
            RedisScript<String> script = new DefaultRedisScript<>(
                    "return redis.call('set', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2])",
                    String.class);
            String result = stringRedisTemplate.execute(script, Collections.singletonList(key), value, String.valueOf(expireTime));
            return "OK".equals(result);
        } catch (Exception e) {
            log.error("获取锁异常", e);
            return false;
        }
    }

    @Override
    public void releaseLock(String key, String value) {
        try {
            RedisScript<Long> script = new DefaultRedisScript<>(
                    "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
                    Long.class);
            stringRedisTemplate.execute(script, Collections.singletonList(key), value);
        } catch (Exception e) {
            log.error("释放锁异常", e);
        }
    }
}
