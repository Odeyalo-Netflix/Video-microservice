package com.odeyalo.analog.netflix.video.service.video;

public interface SagaOrchestrationManager<T> {

    void processSagaTransaction(T t);

}
