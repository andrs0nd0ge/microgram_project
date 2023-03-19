package com.microgram.project;

import com.microgram.project.util.FileServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	@Resource
	FileServiceImpl fileService;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Override
	public void run(String... args) {
		fileService.init();
	}
}
