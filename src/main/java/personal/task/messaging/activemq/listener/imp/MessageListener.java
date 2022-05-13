package personal.task.messaging.activemq.listener.imp;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import personal.task.messaging.activemq.listener.IMessageListener;
import personal.task.services.IProcessMessage;

import javax.jms.TextMessage;

@Component
public class MessageListener implements IMessageListener {
    private final IProcessMessage processMessage;

    public MessageListener(IProcessMessage processMessage) {
        this.processMessage = processMessage;
    }

    @Override
    @JmsListener(destination = "${activemq.queue.name}", containerFactory = "jmsListenerContainerFactory")
    public void receive(TextMessage message) {
        System.out.println(message);
        processMessage.processMessage(message);
    }
}
