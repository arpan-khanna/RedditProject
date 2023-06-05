package com.example.gradleReddit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedditApiFetch {

    public static void main(String[] args) {
        String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg1OTAxMDEwLjQ2NzczOSwiaWF0IjoxNjg1ODE0NjEwLjQ2NzczOCwianRpIjoiVWkwZGZZTDVLdFlPYUZjZXlCLWxUTXRhVTFFQU5RIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.iTV6Ob4axkfiXCA4yE-OSRONFJOqftH69JBpUnFAKedgEB2GK_qQe5Yh0Xe_6g7HivDAtXnMpy3dh1-n5EU9OhsKErAJGGHush6knNlaDVbJFUFk_AzAJT3KTqZ5xo4cakRG-Dik3_yCamW6vY7B9pTTAcLZ83s_cxaQoCKuwyxqtgCkrqRhgVplpQ9PRRmmj6YFnKJgNgxNXn0Bxwxfpj9eqH0yeDR0ZWl_JG5JZ1H0unAQpL5Hmx0oDO02G1zcykEIsudWL9VcZ0Q5GBFpPgJ7xiFyyYbiLOzH9VqW7ingrO0D9-WsEc8RADuSxZlqm1QB9SUAwmGNxIJh79xoXw"; // Replace with your actual access token
//        String username = "Dhruvshah1015"; // Replace with the username for which you want to retrieve posts

        try {
            // Create URL object
//            URL url = new URL("https://oauth.reddit.com/user/" + "RedditGawdApi" + "/submitted");
//            URL url = new URL("https://oauth.reddit.com/api/v1/me");
//            URL url = new URL("https://oauth.reddit.com/user/" + username + "/about");
//            URL url = new URL("https://oauth.reddit.com/user/" + username + "/overview");
            URL url = new URL("https://oauth.reddit.com//subreddits/mine/subscriber");
//            URL url = new URL("https://oauth.reddit.com/r/Cricket");


            // Create HttpURLConnection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("GET");

            // Set User-Agent header
            conn.setRequestProperty("User-Agent", "YourApp/1.0");

            // Set authorization header with access token
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            // Get response code
            int responseCode = conn.getResponseCode();

            // Read response body
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print response
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());

            // Close the connection
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
