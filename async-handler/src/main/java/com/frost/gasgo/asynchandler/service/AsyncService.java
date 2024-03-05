package com.frost.gasgo.asynchandler.service;

import com.frost.gasgo.commonlib.async.event.EventControllerResponse;
import com.frost.gasgo.commonlib.async.event.StatusEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);


//    @Value("${Async-event.kafka.topic-async-event}")
    private final String TOPIC = "external_async_event_t1";

    @Autowired
    private KafkaTemplate<String, StatusEvent> asyncKafkaTemplate;


    public ResponseEntity<EventControllerResponse> publishAsync(StatusEvent event) {
        System.out.println(TOPIC);
        asyncKafkaTemplate.send(TOPIC, event);
        LOGGER.info("published Kafka Evnet : TOPIC : " + TOPIC + " Message : " + event);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        EventControllerResponse
                                .builder()
                                .status("Event Received")
                                .build());
    }

    @Bean
    public NewTopic createTopic() {
        LOGGER.info("Creating TOPIC : " + TOPIC);
        return new NewTopic(TOPIC, 3, (short) 1);
    }
}
