package de.adesso.projects.sapshowcase.service;

import de.adesso.projects.sapshowcase.dao.NativeSQL;
import de.adesso.projects.sapshowcase.model.JobExecutionStatus;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class NativeSQLServiceTest {

    @Mock
    private NativeSQL mockNativeSQL;

    @InjectMocks
    private NativeSQLService nativeSQLService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunJobs() {
        JobExecutionStatus jobExecutionStatus = new JobExecutionStatus();
        jobExecutionStatus.setJobId("testJobId");
        jobExecutionStatus.setJobName("InstanceCreationJob");
        jobExecutionStatus.setResult("");
        jobExecutionStatus.setStartTime(System.currentTimeMillis());
        jobExecutionStatus.setStatus("In Progress");

        when(mockNativeSQL.getConnection()).thenReturn(null);
//        verify(mockNativeSQL.getJob("testJobId"));
        doNothing().when(mockNativeSQL).createConnection();
        doNothing().when(mockNativeSQL).insertJob(any(JobExecutionStatus.class));
        doNothing().when(mockNativeSQL).updateJob(any(JobExecutionStatus.class));
        doNothing().when(mockNativeSQL).closeConnection();

        nativeSQLService.runJobs();

        verify(mockNativeSQL, times(1)).createConnection();
        verify(mockNativeSQL, times(NativeSQLService.MAX_RUM)).insertJob(any(JobExecutionStatus.class));
        verify(mockNativeSQL, times(NativeSQLService.MAX_RUM)).updateJob(any(JobExecutionStatus.class));
        verify(mockNativeSQL, times(NativeSQLService.MAX_RUM)).getJob("testJobId");
        verify(mockNativeSQL, times(1)).closeConnection();
    }

}
