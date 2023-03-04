package com.qugem.utilities;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MongoDBUtils {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void main(String[] args) {

      /*
      ConnectionString connectionString =
              new ConnectionString("mongodb+srv://qugem-test-admin:8yHiPiCSBgQmJWaQ@qugem-test-cluster.c4kyo.mongodb.net/qugem-test-db");

      MongoClientSettings settings = MongoClientSettings.builder()
              .applyConnectionString(connectionString)
              .build();

      MongoClient mongoClient = MongoClients.create(settings);
*/
     // MongoClient mongoClient = new MongoClient(new MongoClientURI(ConfigurationReader.get("qugem-test-db-url")));

    // database = mongoClient.getDB("qugem-test-db");

     // MongoCollection<Document> collection = database.getCollection("employees");
//
//      createConnection();
//
//        //MongoCollection<Document> employees = getCollection("employees");
//        MongoCollection<Document> autos = getCollection("autos");

        //ObjectId objectId = new ObjectId("61168008541b4a0016a79058");

        //Bson filter = Filters.eq("_id",objectId);
        //Bson filter2 = Filters.eq("firstname","SAMET");
//        Bson filter1 = Filters.and(Filters.eq("type","LKW"), Filters.eq("status","in_repair"));
//        Bson filter3 = Filters.and(Filters.eq("type","LKW"));
//        //Bson filter2 = Filters.eq("type","LKW");
//        int size=0;
//        for (Document document : autos.find(filter3)) {
//            String s = document.toJson();
//            //String firstname = (String) document.get("firstname");
//            //System.out.println(firstname);
//            System.out.println(s);
//            size++;
//        }
//        System.out.println(size);

/*
      Block<Document> printBlock = new Block<Document>() {
          @Override
          public void apply(final Document document) {
              System.out.println(document.toJson());
          }
      };

      collection.find(eq("firstname","SAMET")).forEach(printBlock);

      collection.find(eq("employeeNumber", "2323"))
              .projection(new Document("name", 1)
                      .append("firstname", 1)
                      .append("lastname",1))
              .forEach(printBlock);
*/
/*
      DBObject query = new BasicDBObject("_id", "61168008541b4a0016a79058");
      DBCursor cursor = collection.find(query);
      DBObject jo = cursor.one();
      (String) cursor.one().get("name");

      */

//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMAN);
//        String strDate = formatter.format(date);
//        System.out.println(strDate);
  }

    public static void createConnection() {
       try{
           mongoClient = new MongoClient(new MongoClientURI(ConfigurationReader.get("qugem-test-db-url")));
           database = mongoClient.getDatabase(ConfigurationReader.get("db-test-db-name"));
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static MongoClient getMongoClient(){
      return mongoClient;
    }

    public static MongoDatabase getDatabase(){
        return database;
    }

    public static MongoCollection<Document> getCollection(String collectionName){
        return database.getCollection(collectionName);
    }

    public static Document getObjectDataByObjectId(MongoCollection<Document> collection, String objectID){
        ObjectId objectId = new ObjectId(objectID);
        Bson filter = Filters.eq("_id",objectId);
        Document objectDataDocument = null;
        try {
            for (Document document : collection.find(filter)) {
                objectDataDocument = document;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectDataDocument;
    }

    public static Document getObjectDataByFilter(MongoCollection<Document> collection, Bson filter){
        Document objectDataDocument = null;
        try {
            for (Document document : collection.find(filter)) {
                objectDataDocument = document;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectDataDocument;
    }

    public static int getCountOfObjectsInaDocument(MongoCollection<Document> collection, Bson filter){
        int count = 0;
        try {
            for (Document document : collection.find(filter)) {
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }







}
