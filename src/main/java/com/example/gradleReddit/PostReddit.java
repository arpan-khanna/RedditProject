package com.example.gradleReddit;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PostReddit {
    public static String main(Document arg)  {
        // Provide your Reddit API credentials

         String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg2MDM2NDA4Ljc1MTM4NywiaWF0IjoxNjg1OTUwMDA4Ljc1MTM4NiwianRpIjoiMmREeGN2NEVKRXFEZmdjN3RDMC1VZ3Y1SDhzcE9BIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.ZSEeN28X68Fme80D9dwyFk7D6CKqzW7LxUh7vZj55p81f7W8QDCpXC2147J4NeGM21uzd0vCypbC5RPtdpBWeHCEIMYg2m7NQYzyi9z7lRr8mnPqAJUTsXrS4u5dpI8tYeOg-Di1XqsT8zlyCO5z36ATRCGEFFuMC3PneYDM45C9nbFM-ZvQyzlnX3L0OhB-RrRAXmSQIUC4if6W8bJRAd_yqXjnEaq-TjFMrh58eAcnwYpkISW4BOnZuiJN0eNRPTdNfn-8m966AyxVGgUY12Vxe6A-9YIUC65LOKTGaBB_Qih7yTqNVYzn3rKxCnViBn2s1r1xjmTlv6zC4u7gKQ";

        // Submit a post
        String subreddit = arg.getString("subreddit"); // Set the subreddit where you want to post
        String title = arg.getString("title"); // Set the title of the post
        String text = arg.getString("body"); // Set the content of the post
//        return subreddit+"-"+title+"-"+text;

        try{
            URL url = new URL("https://oauth.reddit.com/api/submit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "com.jg.redditapi");
            connection.setDoOutput(true);

            // Build the post submission request body
            String requestBody = "sr=" + subreddit + "&title=" + title + "&text=" + text + "&kind=self" ;

            // Send the post submission request
            connection.getOutputStream().write(requestBody.getBytes(StandardCharsets.UTF_8));

            // Check the response status code
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();



            return "Response code:"+responseCode + "<br>" + "Response Body:" +response.toString()+ "<br>";

        } catch (Exception e){
            return "Failed to submit post.";
        }
    }

}