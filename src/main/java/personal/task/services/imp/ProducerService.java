package personal.task.services.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import personal.task.infra.http.contrade.PayloadRequest;
import personal.task.services.IProducer;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class ProducerService implements IProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    private final MessageProducer messageProducer;
    private final Session session;
    private final ObjectMapper objectMapper;

    public ProducerService(MessageProducer messageProducer, Session session, ObjectMapper objectMapper) {
        this.messageProducer = messageProducer;
        this.session = session;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMessage(PayloadRequest payloadRequest) {
        try {

            LOGGER.info("Payload : " + payloadRequest);
            String jsonPayload = toJsonString(payloadRequest);
            LOGGER.info("jsonPayload : " + jsonPayload);
            TextMessage textMessage = session.createTextMessage(jsonPayload);
            messageProducer.send(textMessage);

        } catch (JsonProcessingException | JMSException ex) {
            LOGGER.error("Failed sending message with error=" + ex);
            throw new RuntimeException("Failed sending message with error=" + ex);
        }
    }

    private String toJsonString(PayloadRequest payloadRequest) throws JsonProcessingException {
        ObjectWriter ow = objectMapper.writer();
        return ow.writeValueAsString(payloadRequest);
    }
}
