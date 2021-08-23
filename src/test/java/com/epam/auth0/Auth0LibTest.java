package com.epam.auth0;

import com.auth0.client.mgmt.EmailTemplatesEntity;
import com.auth0.json.mgmt.EmailTemplate;
import com.auth0.json.mgmt.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Auth0LibTest {

    ManagementAuth0 managementAuth0;

    @BeforeEach
    void initAuth0() {
        managementAuth0 = new ManagementAuth0();
    }

    @Test
    @Order(1)
    void testCreateUser() {
        User defaultUserAuth0 = ObjectFactory.createDefaultUserAuth0();

        User createdUser = managementAuth0.createUser(defaultUserAuth0);

        assertThat(createdUser).isNotNull();
    }

    @Test
    @Order(2)
    void testGetUserByEmail() {
        String email = ObjectFactory.DEFAULT_USER_EMAIL;

        User user = managementAuth0.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    @Order(3)
    void testGetUserById() {
        String email = ObjectFactory.DEFAULT_USER_EMAIL;
        User user = managementAuth0.getUserByEmail(email);

        User userById = managementAuth0.getUserById(user.getId());

        assertThat(userById).isNotNull();
    }


    @Test
    @Order(4)
    void testUpdateUser() {
        // TODO: 8/23/2021 Need add required update fields.
        //  Additional properties not allowed: identities,updated_at,created_at,user_id
        //  (consider storing them in app_metadata or user_metadata.
        //  See "Users Metadata" in https://auth0.com/docs/api/v2/changes for more details)'

//        String email = ObjectFactory.DEFAULT_USER_EMAIL;
//
//        User user = managementAuth0.getUserByEmail(email);
//        user.setFamilyName("Vlad");
//        User updatedUser = managementAuth0.updateUser(user);
//
//        assertThat(updatedUser).isNotNull();
    }

    @Test
    @Order(5)
    void testDeleteUser() {
        String email = ObjectFactory.DEFAULT_USER_EMAIL;
        User user = managementAuth0.getUserByEmail(email);

        Object deletedUser = managementAuth0.deleteUser(user.getId());

        assertThat(deletedUser).isNull();
    }

    @Test
    void testCreateExistUser() {
        User defaultUserAuth0 = ObjectFactory.createExistUserAuth0();
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.createUser(defaultUserAuth0));

        String expectedMessage = "Request failed with status code 409: The user already exists.";
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testCreateUserWithoutConnection() {
        User defaultUserAuth0 = ObjectFactory.createUserWithoutConnectionAuth0();
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.createUser(defaultUserAuth0));

        String expectedMessage = "Request failed with status code 400: Payload validation error: 'Missing required property: connection'.";
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testCreateUserWithoutEmail() {
        User defaultUserAuth0 = ObjectFactory.createUserWithoutEmailAuth0();
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.createUser(defaultUserAuth0));

        String expectedMessage = "Request failed with status code 400: \"email\" is required";
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testCreateUserWithoutName() {
        User defaultUserAuth0 = ObjectFactory.createUserWithoutNameAuth0();
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.createUser(defaultUserAuth0));

        String expectedMessage = "Request failed with status code 400: Payload validation error: 'Missing required property: username'.";
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testCreateUserWithoutPassword() {
        User defaultUserAuth0 = ObjectFactory.createUserWithoutPasswordAuth0();
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.createUser(defaultUserAuth0));

        String expectedMessage = "Request failed with status code 400: \"password\" is required";
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testGetNotExistUser() {
        String notExistUserEmail = ObjectFactory.DEFAULT_USER_EMAIL;
        Exception runtimeException = assertThrows(RuntimeException.class, () -> managementAuth0.getUserByEmail(notExistUserEmail));

        String expectedMessage = "Haven't user with email " + notExistUserEmail;
        String actualMessage = runtimeException.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testGetEmailTemplatesFromAuth0(){
        EmailTemplate emailTemplate = managementAuth0.getEmailTemplates(EmailTemplatesEntity.TEMPLATE_WELCOME_EMAIL);

        assertThat(emailTemplate).isNotNull();
    }

}
