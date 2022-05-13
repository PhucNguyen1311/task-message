package personal.task.messaging.activemq.listener;

import javax.jms.TextMessage;

public interface IMessageListener {
    void receive(TextMessage message);
}
