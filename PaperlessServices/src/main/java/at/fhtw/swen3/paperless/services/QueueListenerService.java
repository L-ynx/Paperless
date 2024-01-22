package at.fhtw.swen3.paperless.services;

public interface QueueListenerService {

    void receiveMessage(String message);
}
