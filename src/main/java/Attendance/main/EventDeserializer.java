package Attendance.main;

import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventDeserializer implements Deserializer<Event> {
    @Override
    public Event deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Event event = null;
        try {
            event = mapper.readValue(data, Event.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}
