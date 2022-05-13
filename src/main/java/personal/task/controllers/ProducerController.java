package personal.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.task.infra.http.contrade.PayloadRequest;
import personal.task.services.IProducer;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
public class ProducerController {

    @Autowired
    private IProducer producer;

    @PostMapping(path = "/message", consumes = APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody PayloadRequest payloadRequest) {
        producer.sendMessage(payloadRequest);
        return ResponseEntity.ok("Succeed");
    }
}
