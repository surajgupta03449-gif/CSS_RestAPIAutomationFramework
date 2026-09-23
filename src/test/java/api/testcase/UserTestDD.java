package api.testcase;

import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

    user userPayload;
    Faker faker = new Faker();
    
    private static final Logger logger =
            LogManager.getLogger("RestAssuredAPIFramework");

    @Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId,
                               String UserName,
                               String fname,
                               String lname,
                               String email,
                               String pwd,
                               String phone) {

    	logger.info("Starting Create User test for username: " + UserName);
    	userPayload = new user();

        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = userEndPoints.createUser(userPayload);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info(
                "Create User executed successfully for username: "
                + UserName
        );
    }

    @Test(priority = 2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testGetUserData(String username) {

    	 logger.info(
                 "Starting Get User test for username: "
                 + username
         );
    	System.out.println("Read User Data");

        Response response = userEndPoints.GetUser(username);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info(
                "Get User executed successfully for username: "
                + username
        );
    }

    @Test(priority = 3)
    public void testUpdateUser() {
    	
    	logger.info("Starting Update User test");

        userPayload.setFirstName(faker.name().firstName());

        Response response = userEndPoints.UpdateUser(
                this.userPayload.getUsername(),
                userPayload);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);

        // Read User data to check if first name is updated
        Response responsePostUpdate = userEndPoints.GetUser(
                this.userPayload.getUsername());

        System.out.println("After Update User Data");

        responsePostUpdate.then().log().all();
        
        logger.info(
                "Update User executed successfully for username: "
                + this.userPayload.getUsername()
        );
    }

    @Test(priority = 4, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username) {

    	 logger.info(
                 "Starting Delete User test for username: "
                 + username
         );

    	
    	System.out.println("Delete User Data");

        Response response = userEndPoints.DeleteUser(username);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info(
                "Delete User executed successfully for username: "
                + username
        );
    }
}