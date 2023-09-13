package com.example.dataanalysermicroservice.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class BeanConfig {

    @SneakyThrows
    @Bean
    public XML consumerXml() {
        return new XMLDocument(
                new File("src/main/resources/kafka/consumer.xml")
        );
    }

}
