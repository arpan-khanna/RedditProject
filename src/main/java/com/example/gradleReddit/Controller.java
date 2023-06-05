package com.example.gradleReddit;

import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

//import com.mongodb.util.JSON;

@RestController
public class Controller {

    @GetMapping(path = "/posts/show/keyword/{id_val}")
    public String postsWithKeyword(@PathVariable String id_val)
    {
        Set<Document> arr = FunctionCalls.findPostsWithKeyword(id_val);
        String ret = "";

        for(Document x:arr)
        {
            ret +=  x.toJson() + "<br>-------------------------------<br>";
        }

        return ret;
    }

    @GetMapping(path = "/posts/show/username/{id_val}")
    public String postsByUsername(@PathVariable String id_val)
    {

        List<Document> arr =  FunctionCalls.findPostsByUserName(id_val);
        String ret = "";

        for(Document x:arr)
        {
            ret +=  x.toJson() + "<br>-------------------------------<br>";
        }

        return ret;
    }

    @GetMapping(path = "/posts/delete/username/{id_val}")
    public DeleteResult deletePostByUsername(@PathVariable String id_val)
    {
        return FunctionCalls.deletePostsByUsers(id_val);
    }

    @GetMapping(path = "/posts/show/sort")
    public String sortedPosts()
    {
        return MongoClientConnection.sortByDate();
    }

    @PostMapping("/post")
    public String createUser(@RequestBody Document doc)
    {
        return PostReddit.main(doc);
    }

//    @PostMapping(path = "/user")
//    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
//    {
//        User sevedUser=usd.addUser(user);
//        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
//
//    @DeleteMapping(path = "user/{id}")
//    public void deleteUser(@PathVariable Integer id)
//    {
//        usd.deleteUserById(id);
//    }
}

