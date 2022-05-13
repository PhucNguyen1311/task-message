package personal.task.services;


import personal.task.infra.http.contrade.PayloadRequest;

public interface IProducer {
    void sendMessage(PayloadRequest payload);
}
