package Attendance.main;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventListener {
    private final AttendanceService attendanceService;
    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    public EventListener(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(Event event) {
        logger.info("Received event: {}", event);
        try {
            attendanceService.processEvent(event);
            logger.info("Processed event: {}", event);
        } catch (Exception e) {
            logger.error("Failed to process event: {}", event, e);
        }
    }
}
