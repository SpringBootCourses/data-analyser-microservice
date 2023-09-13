package com.example.dataanalysermicroservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    @PostConstruct
    private void init() {
        fetch();
    }

    @Override
    public void fetch() {

    }

}
