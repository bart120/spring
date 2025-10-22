package com.formation.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.formation.config.RabbitConfig;
import com.formation.messaging.BookCreatedEvent;

@Component
public class BookEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void onBookCreated(BookCreatedEvent event) {
        System.out.println("!!!!! MESSAGE : titre = " + event.getTitle());
    }
}
