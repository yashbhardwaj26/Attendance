package Attendance.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
    }

    @Test
    public void testGetAttendanceRecord() throws Exception {
        when(attendanceService.getAttendanceRecord(anyString(), anyString())).thenReturn(new AttendanceRecord());

        mockMvc.perform(get("/api/v1/attendance/employee1/2021-06-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attendanceService, times(1)).getAttendanceRecord(anyString(), anyString());
    }
}

