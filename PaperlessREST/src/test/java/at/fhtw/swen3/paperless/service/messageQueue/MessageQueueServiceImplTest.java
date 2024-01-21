package at.fhtw.swen3.paperless.service.messageQueue;

import at.fhtw.swen3.persistence.service.messageQueue.MessageQueueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

class MessageQueueServiceImplTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private MessageQueueServiceImpl messageQueueService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageQueueServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessMessage() {
        // Arrange
        String message = "Test Message";

        // Act
        messageQueueService.processMessage(message);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), eq(message));
        LOGGER.info("Message sent: {}", message);
    }
}
