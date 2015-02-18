package com.example.catherinemorris.shoppinwithfriends;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClientURI;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * The user1 class creates static lists of usernames and passwords when a user registers
 *This will be transferred into the database 
 */
public class User1 {

    //connecting to database right here
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));


    public void addUser(String un, String pw, String em) {
        MongoDatabase database = mongoClient.getDatabase( "baiyitschyes" );
        MongoCollection<Document> collection = database.getCollection("users");
        Document doc = new Document("username", un)
                .append("name", un)
                .append("password", pw)
                .append("email", em)
                .append("sales", 0)
                .append("rating", -1);
        collection.insertOne(doc);
    }

    public boolean findUsername(String um) {
        MongoDatabase database = mongoClient.getDatabase( "baiyitschyes" );
        MongoCollection<Document> collection = database.getCollection("users");
        Document myDoc = collection.find(new Document("username", um)).first();
        if (myDoc != null) {
            return true;
        }
        return false;
    }

    public boolean findPassWord(String pw) {
        MongoDatabase database = mongoClient.getDatabase( "baiyitschyes" );
        MongoCollection<Document> collection = database.getCollection("users");
        Document myDoc = collection.find(new Document("password", pw)).first();
        if (myDoc != null) {
            return true;
        }
        return false;
    }


}
