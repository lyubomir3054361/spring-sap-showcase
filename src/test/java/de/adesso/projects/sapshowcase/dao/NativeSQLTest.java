package de.adesso.projects.sapshowcase.dao;

import de.adesso.projects.sapshowcase.model.JobExecutionStatus;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
public class NativeSQLTest {

    @Mock
    private Database mockDb;

    @InjectMocks
    private NativeSQL nativeSQL;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConnection_SuccessfulConnection() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        Mockito.when(mockDb.getUrl()).thenReturn("jdbc:h2:mem:testdb");
        Mockito.when(mockDb.getUsername()).thenReturn("testuser");
        Mockito.when(mockDb.getPassword()).thenReturn("testpass");
        Mockito.mockStatic(DriverManager.class);
        Mockito.when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockConnection);
        nativeSQL.createConnection();

        Assertions.assertNotNull(nativeSQL.getConnection());
        Mockito.verify(mockConnection).close();
    }

    @Test
    void testCreateConnection_ConnectionFailed() throws SQLException {
        Mockito.when(mockDb.getUrl()).thenReturn("invalidUrl");
        nativeSQL.createConnection();
        Assertions.assertNull(nativeSQL.getConnection());
    }

    @Test
    void testInsertJob_SuccessfulInsert() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        Mockito.when(mockDb.getUrl()).thenReturn("jdbc:h2:mem:testdb");
        Mockito.when(mockDb.getUsername()).thenReturn("testuser");
        Mockito.when(mockDb.getPassword()).thenReturn("testpass");
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(Mockito.mock(PreparedStatement.class));
        nativeSQL.setConnection(mockConnection);

        JobExecutionStatus testJob = new JobExecutionStatus();

        nativeSQL.insertJob(testJob);

        // Mockito.verify(mockPreparedStatement).executeUpdate();
    }

}
