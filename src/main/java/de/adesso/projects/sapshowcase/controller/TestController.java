package de.adesso.projects.sapshowcase.controller;


import de.adesso.projects.sapshowcase.service.NativeSQLRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

   private final NativeSQLRunner nativeSQLRunner;

    @RequestMapping("/")
    public String hello() {

        return "Hello!";
    }

    @RequestMapping("/test_native_sql")
    public String test_native_sql() {

        nativeSQLRunner.startTest();

        return "Test Native SQL Started!";
    }

}
