package com.beyond.board.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    application.yml 의 spring.redis의 정보를 소스코드의 변수로 가져오는 것.
    @Value("${spring.redis.host}")
    public String host;
    @Value("${spring.redis.port}")
    public int port;

//    RedisConnectionFactory는 레디스서버와의 연결을 설정하는 역할
//    LettuceConnectionFactory는 RedisConnectionFactory의 구현체로서 실질적인 역할 수행
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){

//        방법1
//        return new LettuceConnectionFactory(host, port); // yml에 썼는데 왜또 써? 안좋앙. 가지고 오자! // 이건 무조건 레디스 데이터 베이스 0 번 가져왕 비밀번호는?!

//        방법2
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(host);
        redisConfiguration.setPort(port);
//        redisConfiguration.setDatabase(2); // 유연하게 추가 가능
//        redisConfiguration.setPassword("1234"); // 지금은 안써용
        return new LettuceConnectionFactory(redisConfiguration);
    }


//    RedisTemplate 정의
//    RedisTemplate는 레디스와 상호작용할때 레디스 key, value 의 형식을 정의
    @Bean
    public RedisTemplate<String, Object> redisTemplateExec(RedisConnectionFactory redisConnectionFactory){ // 아무거나 들어가도록 Object
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // String 형태를 직렬화 시키겠다! // java에 스트링으로 들어가게
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // 제이슨으로 직렬화
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

//    redisTemplate.opsForValue.set(key, value)
//    redisTemplate.opsForValue.get(key)
//    redisTemplate.opsForValue.increment 또는 decrement

    /*
     redisTemplate를 불러다가 .opsForValue().set(key,value)
     redisTemplate.opsForValue().get(key)
     redisTemplate.opsForValue().increment 또는 decrement
     => redisTemplate를 통해 메서드가 제공됨
     */

}
