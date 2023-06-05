package com.example.gradleReddit;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
//import org.jetbrains.annotations.NotNull;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.*;

abstract public class AlwaysOn implements Runnable
{
    public void run()
    {
//        System.out.println("In this thread");

        String connectionString = "mongodb+srv://new_user:test1234@cluster0.kszgqll.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {

                int lp = 1;

                while(lp > 0) {
                    ArrayList<String> myposts = GetAllCollections.myPosts();
//                    System.out.println("in the loop");
                    // Send a ping to confirm a successful connection

                        while(NewApplication.reader.get() != 0)
                        {;}

                        NewApplication.writer.set(1);

                        MongoCollection<Document> collection = mongoClient.getDatabase("reddit").getCollection("myposts");

                        // Get the count of documents in the collection
                        long count = collection.countDocuments();

                        if (count != myposts.size()) {
                            System.out.println("Now inserting");
                            collection.deleteMany(new Document());
                            for (String x : myposts) {
                                collection.insertOne(Document.parse(x));
                            }
                        }

                        NewApplication.writer.set(0);


                }

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
