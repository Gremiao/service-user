package br.com.jatai.service_user.service;

import br.com.jatai.dispatcher.KafkaDispatcher;
import br.com.jatai.model.CorrelationId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaService<T> {

    public void sendMessage(String className, T payload, String comments){
        try(var messageDispatcher = new KafkaDispatcher<T>()){

            messageDispatcher.sendAsync(
                    "JATAI_LOG",
                    UUID.randomUUID().toString(),
                    new CorrelationId(className),
                    payload,
                    comments,
                    LocalDateTime.now());
        }
    }
}
