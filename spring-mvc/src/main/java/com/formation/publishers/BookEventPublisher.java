package com.formation.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.formation.config.RabbitConfig;
import com.formation.messaging.BookCreatedEvent;

@Component
public class BookEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public BookEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCreated(BookCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, event);
    }
}
