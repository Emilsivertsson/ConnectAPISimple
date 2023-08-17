package org.CodeForPizza;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            URL url = new URL("https://catfact.ninja/fact");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //check if connection is successful
            int responseCode = conn.getResponseCode();
            //System.out.println(responseCode);

            //check it response code is 200, its means connection is OK
            if(responseCode != 200){
                throw new RuntimeException("HttpResponse: " + responseCode);
            } else {

                //initiate stringbuilder and add Json information to is.
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }

                //close the scanner
                scanner.close();

                //System.out.println(informationString);


                //convert the informationString into Json
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(informationString.toString());

                //System.out.println(dataObject);

                String fact = (String) dataObject.get("fact");
                double length = fact.length();

                System.out.println("The fact is: " + fact);
                System.out.println("The length of the fact is: " + length);


            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}