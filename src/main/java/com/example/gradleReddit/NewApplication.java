package com.example.gradleReddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.ArrayList;

@SpringBootApplication
public class NewApplication {

	public static AtomicInteger reader = new AtomicInteger(0);
	public static AtomicInteger writer = new AtomicInteger(0);

	public static void main(String[] args) {
//		ArrayList<String> redColDocs = GetAllCollections.community();
//		MongoClientConnection.setCommunity(redColDocs);

//		ArrayList<String> urlArr =  MongoClientConnection.getCommunity();

//		ArrayList<String> allPosts = GetAllCollections.posts(urlArr);
//		MongoClientConnection.setPosts(allPosts);

//		ArrayList<String> myPosts  = GetAllCollections.myPosts();
//		MongoClientConnection.setMyPosts(myPosts);

//		FunctionCalls.findPostsWithKeyword("cricket");

//		FunctionCalls.findPostsByUserName("t2_cl76cmipa");

//		FunctionCalls.deletePostsByUsers("t2_6l4z3");

//		MongoClientConnection.sortByDate();

//		AlwaysOn.main();

		AlwaysOn r1 = new AlwaysOn() {
		};
		Thread t1 =new Thread(r1);
		// this will call run() method
		t1.start();
		SpringApplication.run(NewApplication.class, args);
	}

}
