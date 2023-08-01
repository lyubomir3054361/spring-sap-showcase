package de.adesso.projects.sapshowcase.controller;


import de.adesso.projects.sapshowcase.service.NativeSQLService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobExecutionController {

   private final NativeSQLService nativeSQLService;

    @RequestMapping("/")
    public String hello() {

        return "Hello!";
    }

    @RequestMapping("/test_native_sql")
    public String test_native_sql() {

        nativeSQLService.runJobs();

        return "Test Native SQL Started!";
    }

}
