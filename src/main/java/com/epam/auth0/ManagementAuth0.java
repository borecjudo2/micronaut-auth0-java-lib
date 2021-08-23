package com.epam.auth0;

import com.auth0.client.mgmt.EmailTemplatesEntity;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.json.mgmt.EmailTemplate;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public class ManagementAuth0 {

    private final ManagementAPI managementAPI;

    public ManagementAuth0() {
        managementAPI = new Auth0().initManagementByAuthRequest();
    }

    public User createUser(User user) {
        Request<User> userRequest = managementAPI.users().create(user);
        return new Executor<User>().job(userRequest);
    }

    public User getUserById(String userId) {
        Request<User> userGetRequest = managementAPI.users().get(userId, new UserFilter());
        return new Executor<User>().job(userGetRequest);
    }

    public User getUserByEmail(String email) {
        Request<List<User>> requestUserByEmail = managementAPI.users().listByEmail(email, null);
        List<User> users = new Executor<List<User>>().job(requestUserByEmail);
        return getUserOrThrowExceptionWithEmail(users, email);
    }

    private User getUserOrThrowExceptionWithEmail(List<User> users, String email) {
        if(users.size() == 1) {
            return users.stream().findFirst().get();
        } else if (users.isEmpty()) {
            throw new RuntimeException("Haven't user with email " + email);
        }
        throw new RuntimeException("Have any users with email " + email);
    }

    public User updateUser(User user) {
        Request<User> userUpdateRequest = managementAPI.users().update(user.getId(), user);
        return new Executor<User>().job(userUpdateRequest);
    }

    public Object deleteUser(String userId) {
        Request<?> deleteRequest = managementAPI.users().delete(userId);
        return new Executor<>().job(deleteRequest);
    }


    public EmailTemplate getEmailTemplates(String templateName) {
        EmailTemplatesEntity emailTemplatesEntity = managementAPI.emailTemplates();
        Request<EmailTemplate> emailTemplateRequest = emailTemplatesEntity.get(templateName);
        return new Executor<EmailTemplate>().job(emailTemplateRequest);
    }

}
