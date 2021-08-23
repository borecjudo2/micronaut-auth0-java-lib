package com.epam.auth0;

import com.auth0.exception.Auth0Exception;
import com.auth0.net.Request;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public class Executor<T> {

    public T job(Request<?> request) {
        try {
            return (T) request.execute();
        } catch (Auth0Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
