package Attendance.main;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerConfigTest {

    @Mock
    private ConsumerFactory<String, Event> consumerFactory;

    @Mock
    private ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory;

    @InjectMocks
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Test
    public void testConsumerFactory() {
        ConsumerFactory<String, Event> factory = kafkaConsumerConfig.consumerFactory();
        assertNotNull(factory);
    }

    @Test
    public void testKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = kafkaConsumerConfig.kafkaListenerContainerFactory();
        assertNotNull(factory);
    }
}

