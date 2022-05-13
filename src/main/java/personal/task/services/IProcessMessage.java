package personal.task.services;

import javax.jms.TextMessage;

public interface IProcessMessage {

    void processMessage(TextMessage message);
}
