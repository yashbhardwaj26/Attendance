package Attendance.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/api/v1/attendance/{employeeId}/{date}")
    public AttendanceRecord getAttendanceRecord(@PathVariable String employeeId, @PathVariable String date) {
        logger.info("Received request for attendance record of employee {} on date {}", employeeId, date);
        AttendanceRecord record = attendanceService.getAttendanceRecord(employeeId, date);
        logger.info("Returning attendance record: {}", record);
        return record;
    }
    
    @GetMapping("/attendance/{employeeId}/{date}")
    public String getAttendanceStatus(@PathVariable String employeeId, @PathVariable String date) {
        AttendanceRecord attendance = attendanceService.getAttendanceRecord(employeeId, date);
        return attendanceService.calculateAttendanceStatus(attendance.getTotalHours());
    }

}
