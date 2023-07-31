package de.adesso.projects.sapshowcase.dao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
@Data
public class Database {

    private String url;
    private String username;
    private String password;

}
