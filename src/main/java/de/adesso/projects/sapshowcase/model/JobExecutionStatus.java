package de.adesso.projects.sapshowcase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "\"ECM_JOB_EXECUTION_STATUS\"")
@RequiredArgsConstructor
@Data
public class JobExecutionStatus {

    @Id
    @Column(name = "\"JOB_ID\"", length = 200)
    private String jobId;

    @Column(name = "\"JOB_NAME\"", length = 190)
    private String jobName;

    @Column(name = "\"START_TIME\"")
    private long startTime;

    @Column(name = "\"STATUS\"", length = 16)
    private String status;

    @Column(name = "\"RESULT\"", length = 250)
    private String result;

}
