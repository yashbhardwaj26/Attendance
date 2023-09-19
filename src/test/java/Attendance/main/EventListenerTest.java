package Attendance.main;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventListenerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private EventListener eventListener;

    @Test
    public void testListen() {
        Event event = new Event("employee1", "swipeIn", 1622527200); // 1st June 2021 00:00:00 GMT
        eventListener.listen(event);

        verify(attendanceService, times(1)).processEvent(any(Event.class));
    }
}

