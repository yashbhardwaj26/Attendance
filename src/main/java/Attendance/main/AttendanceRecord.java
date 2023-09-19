package Attendance.main;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@jakarta.persistence.Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeId;
    private String date; // in YYYY-MM-DD format
    private double totalHours;
    private long swipeIn;

    // constructors, getters, setters, etc.
}
