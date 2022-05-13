package personal.task.services.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import personal.task.infra.http.contrade.Contrade;
import personal.task.infra.http.contrade.PayloadRequest;
import personal.task.infra.http.contrade.PayloadResponse;
import personal.task.services.IProcessMessage;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

@Service
public class ProcessMessage implements IProcessMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessMessage.class);

    private final ObjectMapper objectMapper;
    private final Contrade contrade;

    public ProcessMessage(ObjectMapper objectMapper, Contrade contrade) {
        this.objectMapper = objectMapper;
        this.contrade = contrade;
    }

    @Override
    public void processMessage(TextMessage message) {
        try {
            PayloadRequest payloadRequest = objectMapper.readValue(message.getText(), PayloadRequest.class);
            HttpURLConnection con = contrade.makeConnection(payloadRequest);
            PayloadResponse output = objectMapper.readValue(contrade.getResponseString(con), PayloadResponse.class);
            objectMapper.writeValue(new File(contrade.getFileName()), output);
        } catch (IOException | URISyntaxException | JMSException e) {
            LOGGER.error("Failed creating json file with error= " + e);
            throw new RuntimeException("Failed creating json file with error= " + e);
        }
    }
}
