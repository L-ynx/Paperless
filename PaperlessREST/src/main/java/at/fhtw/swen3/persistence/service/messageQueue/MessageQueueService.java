package at.fhtw.swen3.persistence.service.messageQueue;

public interface MessageQueueService {
    void processMessage(String message);
}
