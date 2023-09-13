package com.example.dataanalysermicroservice.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;

    private final XML settings;

    @Bean
    public Map<String, Object> receiverProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                new TextXpath(
                        this.settings, "//groupId"
                ).toString()
        );
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                new TextXpath(
                        this.settings, "//keyDeserializer"
                ).toString()
        );
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                new TextXpath(
                        this.settings, "//valueDeserializer"
                ).toString()
        );
        props.put(
                "spring.json.trusted.packages",
                new TextXpath(
                        this.settings, "//trustedPackages"
                ).toString()
        );
        return props;
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions
                .create(receiverProperties());
        return receiverOptions.subscription(topics)
                .addAssignListener(partitions ->
                        System.out.println("onPartitionAssigned: "
                                + partitions))
                .addRevokeListener(partitions ->
                        System.out.println("onPartitionRevoked: "
                                + partitions));
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(
            ReceiverOptions<String, Object> receiverOptions
    ) {
        return KafkaReceiver.create(receiverOptions);
    }

}
