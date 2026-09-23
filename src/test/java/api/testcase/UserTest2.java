package api.testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints2;
import api.payload.user;

import io.restassured.response.Response;

public class UserTest2 {

    Faker faker;
    user userPayload;

    private static final Logger logger =
            LogManager.getLogger("RestAssuredAPIFramework");

    

    @BeforeClass
    public void generateTestData() {

        faker = new Faker();

        userPayload = new user();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Obtain logger
        

        logger.info("Test data generated successfully.");
    }


    @Test(priority = 1)
    public void testCreateUser() {

        logger.info(
                "Starting Create User test"
        );

        Response response =
                userEndPoints2.createUser(
                        userPayload
                );

        System.out.println(
                "Create User Data"
        );

        response.then().log().all();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        logger.info(
                "Create User executed successfully: "
                
        );
    }


    @Test(priority = 2)
    public void testGetUserData() {

        logger.info(
                "Starting Get User test"
        );

        Response response =
                userEndPoints2.GetUser(
                        userPayload.getUsername()
                );

        System.out.println(
                "Get User Data"
        );

        response.then().log().all();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        logger.info(
                "Get User executed successfully: "
                
        );
    }


    @Test(priority = 3)
    public void testUpdateUser() {

        logger.info(
                "Starting Update User test"
        );

        userPayload.setFirstName(
                faker.name().firstName()
        );

        Response response =
                userEndPoints2.UpdateUser(
                        userPayload.getUsername(),
                        userPayload
                );

        response.then().log().all();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        System.out.println(
                "After Update User Data"
        );

        Response responsePostUpdate =
                userEndPoints2.GetUser(
                        userPayload.getUsername()
                );

        responsePostUpdate
                .then()
                .log()
                .all();

        logger.info(
                "Update User executed successfully: "
                
        );
    }


    @Test(priority = 4)
    public void testDeleteUser() {

        logger.info(
                "Starting Delete User test"
        );

        Response response =
                userEndPoints2.DeleteUser(
                        userPayload.getUsername()
                );

        System.out.println(
                "Delete User Data"
        );

        response.then().log().all();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        logger.info(
                "Delete User executed successfully: "
              
        );
    }
}