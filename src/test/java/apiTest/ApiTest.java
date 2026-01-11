package apiTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Products;
import utils.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import dbTest.ReadDataFromDb;

import static io.restassured.RestAssured.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiTest extends TestBase {
        @Test(enabled = false)
        public void method1_usingPath() {
                Response response = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Read single value from JSON response using Path()
                int id = response.path("id");

                String title = response.path("title");
                float price = response.path("price");
                System.out.println("id: " + id + ", title: " + title + ", price: " + price);
        }

        @Test(groups = {"smoke"})
        public void method2_UsingJsonPath() {
                Response response = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Get JsonPath object
                JsonPath jsonPath = response.jsonPath();

                // Read the values with type safe methods
                int id = jsonPath.getInt("id");
                String title = jsonPath.getString("title");
                double price = jsonPath.getDouble("price");

                System.out.println("id2: " + id + ", title: " + title + ", price: " + price);

        }

        @Test
        public void method3_DirectExtraction() {
                // extract values directly without Response object
                String title = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().path("title");

                int id = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().path("id");

                float price = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().path("price");

                System.out.println("id2: " + id + ", title: " + title + ", price: " + price);

        }

        @Test
        public void method4_UsingAsString() {
                Response response = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Read single value from JSON response using Path()
                // int id = response.path("id");

                // Get response as String
                String respAsString = response.asString();

                // Then use jsonPath.form()

                JsonPath jsonPath = JsonPath.from(respAsString);
                int id2 = jsonPath.getInt("id");
                String title = jsonPath.getString("title");
                double price = jsonPath.getDouble("price");

                System.out.println("id2: " + id2 + ", title: " + title + ", price: " + price);

        }

        @Test
        public void readNestedArrayFields() {
                Response response = given()
                                .pathParam("id", 1)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Read Array fields from JSON response
                List<String> images = response.path("images");
                List<String> tags = response.path("tags");

                // Read first element from Array
                String firstImage = response.path("images[0]");
                String firstTag = response.path("tags[0]");

                // Read last element from Array
                String lastImage = response.path("images[-1]");
                String lastTag = response.path("tags[-1]");

                System.out.println("Images: " + images);
                System.out.println("First Image: " + firstImage);
                System.out.println("Last Image: " + lastImage);

                System.out.println("Tags: " + tags);
                System.out.println("First Tag: " + firstTag);
                System.out.println("Last Tag: " + lastTag);

        }

        @Test
        public void readMultipleProducts() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Read root level fields
                int total = response.path("total");
                int skip = response.path("skip");
                int limit = response.path("limit");
                System.out.println("Total Products: " + total + ", Skip: " + skip + ", Limit: " + limit);

                // Read Array fields from ist products
                int firstProductIds = response.path("products[0].id");
                int SecondProductIds = response.path("products[1].id");

                System.out.println("First Product ID: " + firstProductIds + "Second Product ID: " + SecondProductIds);

        }

        @Test
        public void readListOfFieldFromMultipleProducts() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // Get all product titles
                List<String> productTitles = response.path("products.title");
                System.out.println("Product Titles: " + productTitles);
                // Get all product prices
                List<Float> productPrices = response.path("products.price");
                System.out.println("Product Prices: " + productPrices);
                // Get all product ids
                List<Integer> productIds = response.path("products.id");
                System.out.println("Product IDs: " + productIds);

                // Read Array fields from ist products
                int firstProductIds = response.path("products[0].id");
                int SecondProductIds = response.path("products[1].id");

                System.out.println("First Product ID: " + firstProductIds + "Second Product ID: " + SecondProductIds);

        }

        @Test
        public void readWithDefaultValues() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                JsonPath json = response.jsonPath();
                String title = json.getString("title");
                title = (title != null) ? title : "Default Title";

                System.out.println("Title: " + title);

        }

        @Test
        public void readAsMap() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                Map<String, Object> productMap = response.jsonPath().getMap("$"); // "$" represents the root of the JSON
                                                                                  // response
                // Accessing some fields from the map
                int total = (Integer) productMap.get("total");
                int limit = (Integer) productMap.get("limit");

                System.out.println("Total: " + total + ", Limit: " + limit);

        }

        @Test
        public void readAllProducts() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                List<Map<String, Object>> productMap = response.jsonPath().getList("products");
                for (Map<String, Object> product : productMap) {
                        System.out.println("Product: " + product);
                        int id = (Integer) product.get("id");
                        String title = (String) product.get("title");
                        float price = (Float) product.get("price");
                        System.out.println("ID: " + id + ", Title: " + title + ", Price: " + price);
                }

        }

        @Test
        public void readAsPojo() {
                Response response = given()
                                .pathParam("id", 3)
                                .when()
                                .get("/products/{id}")
                                .then()
                                .statusCode(200)
                                .extract().response();

                Products product = response.as(Products.class);
                // Accessing some fields from the POJO
                int id = product.getId();
                String title = product.getTitle();
                float price = product.getPrice();

                System.out.println("ID: " + id + ", Title: " + title + ", Price: " + price);
        }

        @Test
        public void readSpecificRange() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // get 5 first products
                List<String> productTitle = response.jsonPath().getList("products[0..4].title");

                // Get specific indexs

                List<String> specificProductTitle = response.jsonPath().getList("products[1,3,5,7].title");

                System.out.println("First 5 Product Titles: " + productTitle);
                System.out.println("Specific Product Titles: " + specificProductTitle);

                // Get prices within a range
                List<Float> productPrices = response.jsonPath().getList("products[0..4].price", Float.class);

                System.out.println("First 5 Product Prices: " + productPrices);

        }

        @Test
        public void practicalExReadandUse() {
                Response response = given()
                                .when()
                                .get("/products")
                                .then()
                                .statusCode(200)
                                .extract().response();

                // read total count
                int totalProducts = response.path("total");
                System.out.println("Total Products: " + totalProducts);

                // read all product Id

                List<Integer> productsId = response.path("products.id");
                System.out.println("Product IDs: " + productsId);
                // Calculate average price of all products

                // productsId.stream().forEach(id->{
                // float price=response.path("products.find{it.id=="+id+"}.price");
                // System.out.println("Product ID: "+id+", Price: "+price);
                // });

                int cost = 10000;
                // productsId.stream().filter(id -> {
                // return response.path("products.find{ it.id == " + id + " && it.price > " +
                // cost + " }") != null;
                // })
                // .forEach(id -> {
                // Float price = response.path("products.find{ it.id == " + id + " }.price");
                // System.out.println("Product ID with price >" + cost + ": " + id + ", Price: "
                // + price);
                // });

                List<String> apiResult = productsId.stream()
                                .filter(id -> response.path(
                                                "products.find{ it.id == " + id + " && it.price > " + cost
                                                                + " }") != null)
                                .map(id -> {
                                        Float price = response.path("products.find{ it.id == " + id + " }.price");
                                        return "ID=" + id + ", Price=" + price;
                                })
                                .collect(Collectors.toList());
                System.out.println(apiResult);

                ReadDataFromDb dbTest = new ReadDataFromDb();
                List<String> dbResult = dbTest.testReadData();

                Assert.assertEquals(apiResult, dbResult, "Mismatch between DB and API data!");

                System.out.println("API and DB data match successfully.");

        }

}