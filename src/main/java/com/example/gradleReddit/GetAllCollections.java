package com.example.gradleReddit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetAllCollections {

    static String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg2MDM2NDA4Ljc1MTM4NywiaWF0IjoxNjg1OTUwMDA4Ljc1MTM4NiwianRpIjoiMmREeGN2NEVKRXFEZmdjN3RDMC1VZ3Y1SDhzcE9BIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.ZSEeN28X68Fme80D9dwyFk7D6CKqzW7LxUh7vZj55p81f7W8QDCpXC2147J4NeGM21uzd0vCypbC5RPtdpBWeHCEIMYg2m7NQYzyi9z7lRr8mnPqAJUTsXrS4u5dpI8tYeOg-Di1XqsT8zlyCO5z36ATRCGEFFuMC3PneYDM45C9nbFM-ZvQyzlnX3L0OhB-RrRAXmSQIUC4if6W8bJRAd_yqXjnEaq-TjFMrh58eAcnwYpkISW4BOnZuiJN0eNRPTdNfn-8m966AyxVGgUY12Vxe6A-9YIUC65LOKTGaBB_Qih7yTqNVYzn3rKxCnViBn2s1r1xjmTlv6zC4u7gKQ";
    public static ArrayList<String> community() {
//        String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg1OTAxMDEwLjQ2NzczOSwiaWF0IjoxNjg1ODE0NjEwLjQ2NzczOCwianRpIjoiVWkwZGZZTDVLdFlPYUZjZXlCLWxUTXRhVTFFQU5RIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.iTV6Ob4axkfiXCA4yE-OSRONFJOqftH69JBpUnFAKedgEB2GK_qQe5Yh0Xe_6g7HivDAtXnMpy3dh1-n5EU9OhsKErAJGGHush6knNlaDVbJFUFk_AzAJT3KTqZ5xo4cakRG-Dik3_yCamW6vY7B9pTTAcLZ83s_cxaQoCKuwyxqtgCkrqRhgVplpQ9PRRmmj6YFnKJgNgxNXn0Bxwxfpj9eqH0yeDR0ZWl_JG5JZ1H0unAQpL5Hmx0oDO02G1zcykEIsudWL9VcZ0Q5GBFpPgJ7xiFyyYbiLOzH9VqW7ingrO0D9-WsEc8RADuSxZlqm1QB9SUAwmGNxIJh79xoXw"; // Replace with your actual access token

        try {
            // Create URL object

            URL url = new URL("https://oauth.reddit.com//subreddits/mine/subscriber");


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
//            System.out.println("Response Code: " + responseCode);
//            System.out.println("Response Body: " + response.toString());

            // Close the connection
            conn.disconnect();

            String redditCollections = response.toString();
            int startIndex = redditCollections.indexOf("children");

            while(redditCollections.charAt(startIndex) != '[')
                startIndex++;

            startIndex++;

            int endIndex = redditCollections.length()-1;
            while(redditCollections.charAt(endIndex) != ']')
                endIndex--;

            String redditCollectionsSubstring = redditCollections.substring(startIndex, endIndex);
            int numOfPar = 0;
            ArrayList<String> redditCollectionsDocuments = new ArrayList<String>();
            String temp = "";
            for(int i=0;i<redditCollectionsSubstring.length();i++)
            {
                if(redditCollectionsSubstring.charAt(i) == ',')
                {
                    if(numOfPar == 0)
                    {
                        redditCollectionsDocuments.add(temp);
                        temp = "";
                        continue;
                    }

                }
                else if(redditCollectionsSubstring.charAt(i) =='{')
                    numOfPar++;
                else if(redditCollectionsSubstring.charAt(i) =='}')
                    numOfPar--;


                temp += redditCollectionsSubstring.charAt(i);

            }
            if(temp.length() != 0)
                    redditCollectionsDocuments.add(temp);

            return redditCollectionsDocuments;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static ArrayList<String> posts(ArrayList<String> pathUrls) {
//        String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg1OTAxMDEwLjQ2NzczOSwiaWF0IjoxNjg1ODE0NjEwLjQ2NzczOCwianRpIjoiVWkwZGZZTDVLdFlPYUZjZXlCLWxUTXRhVTFFQU5RIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.iTV6Ob4axkfiXCA4yE-OSRONFJOqftH69JBpUnFAKedgEB2GK_qQe5Yh0Xe_6g7HivDAtXnMpy3dh1-n5EU9OhsKErAJGGHush6knNlaDVbJFUFk_AzAJT3KTqZ5xo4cakRG-Dik3_yCamW6vY7B9pTTAcLZ83s_cxaQoCKuwyxqtgCkrqRhgVplpQ9PRRmmj6YFnKJgNgxNXn0Bxwxfpj9eqH0yeDR0ZWl_JG5JZ1H0unAQpL5Hmx0oDO02G1zcykEIsudWL9VcZ0Q5GBFpPgJ7xiFyyYbiLOzH9VqW7ingrO0D9-WsEc8RADuSxZlqm1QB9SUAwmGNxIJh79xoXw"; // Replace with your actual access token

        try {
            // Create URL object

//            URL url = new URL("https://oauth.reddit.com//subreddits/mine/subscriber");
            ArrayList<String> redditCollectionsDocuments = new ArrayList<String>();

            for (String x : pathUrls) {

                URL url = new URL("https://oauth.reddit.com" + x);
//                System.out.println("-------\n"+url.toString()+"\n--------");

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
//                System.out.println("Response Code: " + responseCode);
//                System.out.println("Response Body: " + response.toString());

                // Close the connection
                conn.disconnect();
                String redditCollections = response.toString();
                int startIndex = redditCollections.indexOf("children");

                while (redditCollections.charAt(startIndex) != '[')
                    startIndex++;

                startIndex++;

                int endIndex = redditCollections.length() - 1;
                while (redditCollections.charAt(endIndex) != ']')
                    endIndex--;

                String redditCollectionsSubstring = redditCollections.substring(startIndex, endIndex);
                int numOfPar = 0;
                String temp = "";
                for (int i = 0; i < redditCollectionsSubstring.length(); i++) {
                    if (redditCollectionsSubstring.charAt(i) == ',') {
                        if (numOfPar == 0) {
                            redditCollectionsDocuments.add(temp);
//                            System.out.println(temp);
                            temp = "";
                            continue;
                        }

                    } else if (redditCollectionsSubstring.charAt(i) == '{')
                        numOfPar++;
                    else if (redditCollectionsSubstring.charAt(i) == '}')
                        numOfPar--;


                    temp += redditCollectionsSubstring.charAt(i);

                }

                if(temp.length() != 0)
                        redditCollectionsDocuments.add(temp);


            }


            return redditCollectionsDocuments;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();

    }

    public static ArrayList<String> myPosts() {
//        String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg1OTAxMDEwLjQ2NzczOSwiaWF0IjoxNjg1ODE0NjEwLjQ2NzczOCwianRpIjoiVWkwZGZZTDVLdFlPYUZjZXlCLWxUTXRhVTFFQU5RIiwiY2lkIjoiTjdwZHpqQkxmdnl2ejktNUxGY3ZGZyIsImxpZCI6InQyX2NsNzZjbWlwYSIsImFpZCI6InQyX2NsNzZjbWlwYSIsImxjYSI6MTY4NTcyOTA3MzAwMCwic2NwIjoiZUp5S1Z0SlNpZ1VFQUFEX193TnpBU2MiLCJmbG8iOjl9.iTV6Ob4axkfiXCA4yE-OSRONFJOqftH69JBpUnFAKedgEB2GK_qQe5Yh0Xe_6g7HivDAtXnMpy3dh1-n5EU9OhsKErAJGGHush6knNlaDVbJFUFk_AzAJT3KTqZ5xo4cakRG-Dik3_yCamW6vY7B9pTTAcLZ83s_cxaQoCKuwyxqtgCkrqRhgVplpQ9PRRmmj6YFnKJgNgxNXn0Bxwxfpj9eqH0yeDR0ZWl_JG5JZ1H0unAQpL5Hmx0oDO02G1zcykEIsudWL9VcZ0Q5GBFpPgJ7xiFyyYbiLOzH9VqW7ingrO0D9-WsEc8RADuSxZlqm1QB9SUAwmGNxIJh79xoXw"; // Replace with your actual access token

        try {
            // Create URL object

//            URL url = new URL("https://oauth.reddit.com//subreddits/mine/subscriber");
            URL url = new URL("https://oauth.reddit.com/user/" + "RedditGawdApi" + "/submitted");


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
//            System.out.println("Response Code: " + responseCode);
//            System.out.println("Response Body: " + response.toString());

            // Close the connection
            conn.disconnect();

            String redditCollections = response.toString();
            int startIndex = redditCollections.indexOf("children");

            while(redditCollections.charAt(startIndex) != '[')
                startIndex++;

            startIndex++;

            int endIndex = redditCollections.length()-1;
            while(redditCollections.charAt(endIndex) != ']')
                endIndex--;

            String redditCollectionsSubstring = redditCollections.substring(startIndex, endIndex);
            int numOfPar = 0;
            ArrayList<String> redditCollectionsDocuments = new ArrayList<String>();
            String temp = "";
            for(int i=0;i<redditCollectionsSubstring.length();i++)
            {
                if(redditCollectionsSubstring.charAt(i) == ',')
                {
                    if(numOfPar == 0)
                    {
                        redditCollectionsDocuments.add(temp);
                        temp = "";
                        continue;
                    }

                }
                else if(redditCollectionsSubstring.charAt(i) =='{')
                    numOfPar++;
                else if(redditCollectionsSubstring.charAt(i) =='}')
                    numOfPar--;


                temp += redditCollectionsSubstring.charAt(i);

            }

            if(temp.length() !=  0)
                redditCollectionsDocuments.add(temp);

            return redditCollectionsDocuments;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}

