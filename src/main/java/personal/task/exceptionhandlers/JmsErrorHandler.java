package personal.task.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;
import personal.task.services.imp.ProducerService;

@Component
public class JmsErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    @Override
    public void handleError(Throwable t) {
        LOGGER.warn("In default jms error handler...");
        LOGGER.error("Error Message : {}", t.getMessage());
    }
}
