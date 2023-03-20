package com.microgram.project;

import com.microgram.project.util.FileServiceImpl;
import com.microgram.project.util.UtilityClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	@Resource
	FileServiceImpl fileService;

/*	FOR TESTING PURPOSES ONLY
	Uncomment the code below if the tables don't exist or if test data is needed.
 */
//		@Resource
//		UtilityClass util;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Override
	public void run(String... args) {
		fileService.init();
		/* FOR TESTING PURPOSES ONLY
			Uncomment the code below if the tables don't exist
		*/
//			util.createUsersTable();
//			util.createPostsTable();
//			util.createCommentsTable();
//			util.createLikesTable();
//			util.createSubscriptionsTable();

		/* FOR TESTING PURPOSES ONLY
			Uncomment the code below if test data is needed
		*/
//			util.insertIntoUsers();
//			util.insertIntoPosts();
//			util.insertIntoComments();
//			util.insertIntoLikes();
//			util.insertIntoSubs();
//			util.updateUsers();
	}
}
