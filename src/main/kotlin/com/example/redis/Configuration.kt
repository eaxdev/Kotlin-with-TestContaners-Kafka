package com.example.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
@EnableAutoConfiguration
class Configuration {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = env.getProperty("spring.redis.host")!!
        redisConf.port = Integer.parseInt(env.getProperty("spring.redis.port"))
        return LettuceConnectionFactory(redisConf)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(redisConnectionFactory())
        return template
    }
}