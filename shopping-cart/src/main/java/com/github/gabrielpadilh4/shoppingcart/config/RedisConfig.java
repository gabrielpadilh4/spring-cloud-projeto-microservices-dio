package com.github.gabrielpadilh4.shoppingcart.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public HealthIndicator redisHealthIndicator() { // Custom Health Indicator to Actuator
        return () -> {
            final long start = System.currentTimeMillis();
            RedisConnection connection = null;
            try {
                connection = RedisConnectionUtils.getConnection(jedisConnectionFactory());
                final String response = connection.ping();
                final Builder builder = "PONG".equals(response) ? Health.up() : Health.down();
                return builder.withDetail("time", System.currentTimeMillis() - start)
                        .withDetail("response", response).build();
            } catch (final Exception e) {
                return Health.down().withDetail("time", System.currentTimeMillis() - start)
                        .withDetail("error", e.getMessage()).build();
            } finally {
                RedisConnectionUtils.releaseConnection(connection, jedisConnectionFactory());
            }
        };
    }
}
