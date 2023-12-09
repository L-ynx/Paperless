package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface OcrService {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    void processMessage(String message, String storagePath);
}
