package org.inamsay.net.appredissentinel;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
public class RedisConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
  }

  @Bean
  public RedisSentinelConfiguration redisSentinelConfiguration() {
    RedisSentinelConfiguration config = new RedisSentinelConfiguration()
        .master("localhost")
        .sentinel("localhost", 26379)
        .sentinel("localhost", 26380)
        .sentinel("localhost", 26381);
    return config;
  }
}
