package de.adesso.projects.sapshowcase.controller;

import de.adesso.projects.sapshowcase.service.NativeSQLService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class JobExecutionControllerTest {

    @Mock
    private NativeSQLService mockNativeSQLService;

    @InjectMocks
    private JobExecutionController jobExecutionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHello() {
        String result = jobExecutionController.hello();
        Assertions.assertEquals("Hello!", result);
    }

    @Test
    void testTestNativeSQL() {
        doNothing().when(mockNativeSQLService).runJobs();
        String result = jobExecutionController.test_native_sql();
        Assertions.assertEquals("Test Native SQL Started!", result);
        verify(mockNativeSQLService, times(1)).runJobs();
    }

}
