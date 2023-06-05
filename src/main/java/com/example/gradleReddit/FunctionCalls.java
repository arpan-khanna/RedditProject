package com.example.gradleReddit;

import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.List;
import java.util.Set;

public class FunctionCalls {

    public static Set<Document> findPostsWithKeyword(String keyword)
    {
        Set<Document> postsWithKeyWords =  MongoClientConnection.keyWords(keyword);
        return postsWithKeyWords;

    }

    public static List<Document> findPostsByUserName(String user)
    {
        List<Document> postsWithUser = MongoClientConnection.findPostsByUser(user);

        return postsWithUser;
    }

    public static DeleteResult deletePostsByUsers(String user)
    {
        DeleteResult delres = MongoClientConnection.deletePostsByUser(user);
        return delres;

    }
}
