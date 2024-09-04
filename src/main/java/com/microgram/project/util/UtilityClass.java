package com.microgram.project.util;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class UtilityClass implements CommandLineRunner {
    private final DataSource dataSource;
    @Resource
    FileServiceImpl fileService;

    public UtilityClass(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        fileService.init();

        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("db/init.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
