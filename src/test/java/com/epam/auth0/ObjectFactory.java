package com.epam.auth0;

import com.auth0.json.mgmt.users.User;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
class ObjectFactory {

    private static final String USER_CONNECTION = "Username-Password-Authentication";
    public static final String DEFAULT_USER_EMAIL = "jhon.doe@example.com";
    private static final String DEFAULT_USER_NAME = "Jhon.Doe";
    private static final String DEFAULT_USER_PASSWORD = "password";

    public static final String EXIST_USER_EMAIL = "karpeco68@gmail.com";

    static User createDefaultUserAuth0(){
        User user = new User(USER_CONNECTION);
        user.setEmail(DEFAULT_USER_EMAIL);
        user.setUsername(DEFAULT_USER_NAME);
        user.setPassword(DEFAULT_USER_PASSWORD.toCharArray());
        user.setVerifyEmail(true);
        return user;
    }

    static User createExistUserAuth0(){
        User user = new User(USER_CONNECTION);
        user.setEmail(EXIST_USER_EMAIL);
        user.setUsername(DEFAULT_USER_NAME);
        user.setPassword(DEFAULT_USER_PASSWORD.toCharArray());
        return user;
    }

    static User createUserWithoutConnectionAuth0(){
        User user = new User();
        user.setEmail(DEFAULT_USER_EMAIL);
        user.setUsername(DEFAULT_USER_NAME);
        user.setPassword(DEFAULT_USER_PASSWORD.toCharArray());
        return user;
    }

    static User createUserWithoutEmailAuth0(){
        User user = new User(USER_CONNECTION);
        user.setUsername(DEFAULT_USER_NAME);
        user.setPassword(DEFAULT_USER_PASSWORD.toCharArray());
        return user;
    }

    static User createUserWithoutNameAuth0(){
        User user = new User(USER_CONNECTION);
        user.setEmail(DEFAULT_USER_EMAIL);
        user.setPassword(DEFAULT_USER_PASSWORD.toCharArray());
        return user;
    }

    static User createUserWithoutPasswordAuth0(){
        User user = new User(USER_CONNECTION);
        user.setEmail(DEFAULT_USER_EMAIL);
        user.setUsername(DEFAULT_USER_NAME);
        return user;
    }

}
