package Attendance.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void processEvent(Event event) {
        try {
            String date = getDateFromTimestamp(event.getTimestamp());
            Optional<AttendanceRecord> optionalRecord = attendanceRepository.findByEmployeeIdAndDate(event.getEmployeeId(), date);

            if (optionalRecord.isPresent()) {
                AttendanceRecord record = optionalRecord.get();
                if ("swipeOut".equals(event.getEventType())) {
                    double hours = calculateWorkingHours(record.getSwipeIn(), event.getTimestamp());
                    record.setTotalHours(hours);
                    attendanceRepository.save(record);
                    logger.info("Updated attendance record for employee {} on date {}", event.getEmployeeId(), date);
                }
            } else {
                if ("swipeIn".equals(event.getEventType())) {
                    AttendanceRecord record = new AttendanceRecord();
                    record.setEmployeeId(event.getEmployeeId());
                    record.setDate(date);
                    record.setSwipeIn(event.getTimestamp());
                    attendanceRepository.save(record);
                    logger.info("Created attendance record for employee {} on date {}", event.getEmployeeId(), date);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to process event: {}", event, e);
        }
    }
    
    public AttendanceRecord getAttendanceRecord(String employeeId, String date) {
        Optional<AttendanceRecord> record = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
        return record.orElse(null);
    }
    
    public String calculateAttendanceStatus(double totalHours) {
        if (totalHours < 4) {
            return "Absent";
        } else if (totalHours < 8) {
            return "Half day";
        } else {
            return "Present";
        }
    }



    private String getDateFromTimestamp(long timestamp) {
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString();
    }

    private double calculateWorkingHours(long swipeIn, long swipeOut) {
        return (swipeOut - swipeIn) / 3600.0;
    }
}
