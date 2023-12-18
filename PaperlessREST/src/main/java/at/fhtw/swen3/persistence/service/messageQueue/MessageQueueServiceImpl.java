package at.fhtw.swen3.persistence.service.messageQueue;

import at.fhtw.swen3.paperless.config.SpringDocConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageQueueServiceImpl implements MessageQueueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageQueueServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageQueueServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void processMessage(String message) {
        rabbitTemplate.convertAndSend(SpringDocConfiguration.QUEUE, message);
        
        LOGGER.info("Message sent: {}", message);
    }
}
