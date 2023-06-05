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

public class MongoClientConnection{

    public static void setCommunity(ArrayList<String> varCom) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));

                while(NewApplication.reader.get() != 0 || NewApplication.writer.get() != 0)
                {}

                NewApplication.writer.set(1);

                MongoCollection<Document> collection = database.getCollection("community");

               for(String x:varCom)
               {
                   Document document = Document.parse(x);
                   collection.insertOne(document);
               }

               NewApplication.writer.set(1);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> getCommunity() {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");

                while(NewApplication.writer.get() != 0)
                {}

                NewApplication.reader.incrementAndGet();
//                database.runCommand(new Document("ping", 1));
                MongoCollection<Document> collection = database.getCollection("community");

                /* --------- working code --------------

                Document projection = new Document("data.url", 1);

                // Execute the query with the projection and retrieve the matching documents
                MongoCursor<Document> cursor1 = collection.find().projection(projection).iterator();

                // Iterate over the documents
                System.out.println("Matching documents:");
                while (cursor1.hasNext()) {
                    Document document = cursor1.next();
                    System.out.println(document.toJson());
                }

                --------------------------------------------*/



                MongoCursor<Document> cursor = collection.find().iterator();
                ArrayList<String> urlArr = new ArrayList<String>();

                // Iterate over the cursor
                while (cursor.hasNext()) {
                    Document document = cursor.next();
//                    Object fieldValue = document.get("data");
                    Document subdocument = document.get("data", Document.class);
//                    Object fieldValue = document.get("url");
//                    for (String field : subdocument.keySet()) {
//
//                        if(field == "url")
//                            System.out.println("-"+field+"-");
//                    }

                    // Do something with the fieldValue
//                    System.out.println(fieldValue);

                    int index = subdocument.toJson().indexOf("url");
                    while(subdocument.toJson().charAt(index) != '"')
                        index++;
                    index += 4;
//                    System.out.println(subdocument.toJson().charAt(index));

                    String tempstr = "";
                    while(subdocument.toJson().charAt(index) != '"'){
                        tempstr += subdocument.toJson().charAt(index);
                        index++;
                    }

                    urlArr.add(tempstr);
                }

                NewApplication.reader.decrementAndGet();

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();
                return  urlArr;



            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<String>();
    }

    public static void setPosts(ArrayList<String> varCom) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));

                while(NewApplication.reader.get() != 0 || NewApplication.writer.get() != 0)
                {}

                NewApplication.writer.set(1);
                MongoCollection<Document> collection = database.getCollection("posts");
                for(String x:varCom)
                {
                    Document document = Document.parse(x);
                    collection.insertOne(document);
                }


                mongoClient.close();

                NewApplication.writer.set(0);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setMyPosts(ArrayList<String> varCom) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));
                while(NewApplication.reader.get() != 0 || NewApplication.writer.get() != 0)
                {}

                NewApplication.writer.set(1);

                MongoCollection<Document> collection = database.getCollection("myposts");
                for(String x:varCom)
                {
                    Document document = Document.parse(x);
                    collection.insertOne(document);
                }

                mongoClient.close();

                NewApplication.writer.set(0);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

    public static Set<Document> keyWords(String keyword) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));
                while(NewApplication.writer.get() != 0)
                {}

                NewApplication.reader.incrementAndGet();

                MongoCollection<Document> collection = database.getCollection("myposts");

                // Build the query
                Document query = new Document("data.selftext", new Document("$regex", keyword));
                Document query1 = new Document("data.title", new Document("$regex", keyword));

                // Execute the query and retrieve the matching documents
                MongoCursor<Document> cursor1 = collection.find(query).iterator();
                MongoCursor<Document> cursor2 = collection.find(query1).iterator();


                Set<Document> union = new HashSet<>();

                // Iterate over the first cursor and add each document to the set
                while (cursor1.hasNext()) {
                    union.add(cursor1.next());
                }

                // Iterate over the second cursor and add each document to the set
                while (cursor2.hasNext()) {
                    union.add(cursor2.next());
                }

                collection = database.getCollection("posts");
                // Build the query
                 query = new Document("data.selftext", new Document("$regex", keyword));
                 query1 = new Document("data.title", new Document("$regex", keyword));

                // Execute the query and retrieve the matching documents
                 cursor1 = collection.find(query).iterator();
                 cursor2 = collection.find(query1).iterator();


                // Iterate over the first cursor and add each document to the set
                while (cursor1.hasNext()) {
                    union.add(cursor1.next());
                }

                // Iterate over the second cursor and add each document to the set
                while (cursor2.hasNext()) {
                    union.add(cursor2.next());
                }


                mongoClient.close();
                NewApplication.reader.decrementAndGet();
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                return union;


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

        return new HashSet<Document>();
    }

    public static List<Document> findPostsByUser(String user) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));
                while(NewApplication.writer.get() != 0)
                {}

                NewApplication.reader.incrementAndGet();

                MongoCollection<Document> collection1 = database.getCollection("posts");
                MongoCollection<Document> collection2 = database.getCollection("myposts");

                // Define the query
                Document query = new Document("data.author_fullname", user);


                // Execute the query
                MongoCursor<Document> cursor1 = collection1.find(query).iterator();
                MongoCursor<Document> cursor2 = collection2.find(query).iterator();


                // Create a list to store the union of documents
                List<Document> union = new ArrayList<>();

                // Iterate over the first cursor and add each document to the list
                while (cursor1.hasNext()) {
                    union.add(cursor1.next());
                }

                // Iterate over the second cursor and add each document to the list
                while (cursor2.hasNext()) {
                    union.add(cursor2.next());
                }

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();
                NewApplication.reader.decrementAndGet();
                return union;

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<Document>();
    }

    public static DeleteResult deletePostsByUser(String user) {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
                while(NewApplication.reader.get() != 0 || NewApplication.writer.get() != 0)
                {}

                NewApplication.writer.set(1);
//                database.runCommand(new Document("ping", 1));
                MongoCollection<Document> collection = database.getCollection("posts");

                // Define the query
                Document query = new Document("data.author_fullname", user);

                // Delete the matching documents
                DeleteResult deleteResult = collection.deleteMany(query);
                System.out.println(deleteResult);

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();
                NewApplication.writer.set(0);
                return deleteResult;


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    public static String sortByDate() {
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
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("reddit");
//                database.runCommand(new Document("ping", 1));
                // Access the first collection
//                MongoCollection<Document> collection1 = database.getCollection("posts");
/*
                // Access the second collection
                MongoCollection<Document> collection2 = database.getCollection("myposts");

                // Retrieve documents from the first collection
                FindIterable<Document> documents1 = collection1.find().sort({});
                List<Document> combinedDocuments = new ArrayList<>();
                for (Document document : documents1) {
                    combinedDocuments.add(document);
                }

                // Retrieve documents from the second collection and append them to the combined list
                FindIterable<Document> documents2 = collection2.find();
                for (Document document : documents2) {
                    combinedDocuments.add(document);
                }

                // Sort the combined list of documents based on a specified field (e.g., "fieldName")
//                Collections.sort(combinedDocuments, Comparator.comparing(doc -> doc.getString("data.created_utc")));
                Collections.sort(combinedDocuments, Comparator.nullsFirst(Comparator.comparing(doc -> {
                    Long epoch = doc.getLong("data.created_utc");
                    if (epoch != null) {
                        return new Date(epoch * 1000); // Convert epoch to milliseconds
                    } else {
                        return null;
                    }
                })));
                // Iterate over the sorted list and perform desired operations
                for (Document document : combinedDocuments) {
                    // Process each document
                    System.out.println(document);
                }
*/

                // Sort the collection in ascending order by a specific field
//                Document sortCriteria = new Document("data.created_utc", 1);
//                FindIterable<Document> sortedCollection = collection1.find().sort(sortCriteria);
//
//                // Iterate over the sorted collection and perform desired operations
//                for (Document document : sortedCollection) {
//                    // Process each document
//                    System.out.println(document);
//                }
                while(NewApplication.writer.get() != 0)
                {}

                NewApplication.reader.incrementAndGet();

                // Access the collections
                MongoCollection<Document> collection1 = database.getCollection("posts");
                MongoCollection<Document> collection2 = database.getCollection("myposts");

                // Define the aggregation pipeline
                Document match1 = new Document("$match", new Document());
                Document match2 = new Document("$match", new Document());
                Document union = new Document("$unionWith", new Document("coll", "myposts"));
                Document sort = new Document("$sort", new Document("data.created_utc", 1));

                // Perform the aggregation
                AggregateIterable<Document> result = collection1.aggregate(Arrays.asList(match1, union, match2, sort));

                String retValue = "";
                // Iterate over the sorted documents and perform desired operations
                for (Document document : result) {
                    // Process each document
                    retValue += document.toJson() + "<br>-------------------------------<br>";
                }

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                mongoClient.close();
                NewApplication.reader.decrementAndGet();
                return retValue;


            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

        return new String();
    }
}
