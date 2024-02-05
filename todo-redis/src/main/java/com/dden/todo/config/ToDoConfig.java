package com.dden.todo.config;

import com.dden.todo.domain.ToDo;
import com.dden.todo.redis.ToDoConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * ToDoConfig
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
public class ToDoConfig {

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter,
                                                   @Value("${todo.redis.topic}") String topic){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topic));
        return container;

    }

    @Bean
    MessageListenerAdapter listenerAdapter(ToDoConsumer consumer){
        MessageListenerAdapter adapter = new MessageListenerAdapter(consumer);
        adapter.setSerializer(new Jackson2JsonRedisSerializer<>(ToDo.class));
        return adapter;
    }

    @Bean
    RedisTemplate<String, ToDo> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, ToDo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(ToDo.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
