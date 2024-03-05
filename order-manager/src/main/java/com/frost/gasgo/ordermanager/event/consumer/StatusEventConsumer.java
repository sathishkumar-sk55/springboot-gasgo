package com.frost.gasgo.ordermanager.event.consumer;

import com.frost.gasgo.commonlib.async.event.StatusEvent;
import com.frost.gasgo.ordermanager.customexpection.OrderNotFoundException;
import com.frost.gasgo.ordermanager.entity.RefilOrder;
import com.frost.gasgo.ordermanager.repository.RefilOrderRepository;
import com.frost.gasgo.ordermanager.util.DateConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusEventConsumer {

    @Autowired
    private RefilOrderRepository refilOrderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusEventConsumer.class);

    private static final String TOPIC = "external_async_event_t1";


    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void listen(StatusEvent event) throws InterruptedException, OrderNotFoundException {
        LOGGER.info("Status Event Recieved : "+event.toString());
        Optional<RefilOrder> fetchedOrder = refilOrderRepository.findById(event.getOrderId());

        if (fetchedOrder.isEmpty()) {
            LOGGER.error("Status Event -> order id : "+event.getOrderId()+" Not Found " + event.toString());
            return;
        }
        RefilOrder order = fetchedOrder.get();
        order.setStatus(event.getStatus());
        order.setEstimatedDeliveryDate(DateConversion.convertStringToDate(event.getEstimatedDeliveryDate()));
        refilOrderRepository.save(order);
        LOGGER.info("Status Event -> order id : "+event.getOrderId()+" processed Successfully " + event.toString());
        Thread.sleep(10000);
    }
}
