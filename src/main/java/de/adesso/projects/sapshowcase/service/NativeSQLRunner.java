package de.adesso.projects.sapshowcase.service;

import de.adesso.projects.sapshowcase.dao.NativeSQL;
import de.adesso.projects.sapshowcase.model.JobExecutionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NativeSQLRunner {
    private static final int MAX_RUM = 100;
    private final NativeSQL nativeSQL;

    @Async
    public void startTest() {
        nativeSQL.createConnection();
        for (int i = 0; i < MAX_RUM; i++) {
            JobExecutionStatus job = new JobExecutionStatus();
            job.setJobId(UUID.randomUUID().toString());
            job.setJobName("InstanceCreationJob");
            job.setResult("");
            job.setStartTime(System.currentTimeMillis());
            job.setStatus("In Progress");

            nativeSQL.insertJob(job);

            job.setResult("Instance creation successfully completed");
            job.setStatus("Succeeded");

            nativeSQL.updateJob(job);

            nativeSQL.getJob(job.getJobId());
        }
        nativeSQL.closeConnection();
    }
}
