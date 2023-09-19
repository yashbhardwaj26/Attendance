package Attendance.main;

import lombok.Data;

@Data
public class Event {
    public Event(String string, String string2, int i) {
		// TODO Auto-generated constructor stub
	}
	private String employeeId;
    private String eventType; // "swipeIn" or "swipeOut"
    private long timestamp; // Unix timestamp

    // constructors, getters, setters, etc.
}
