package com.example.dataanalysermicroservice.service;

import com.example.dataanalysermicroservice.model.Data;

public interface KafkaDataService {

    void handle(Data data);

}
