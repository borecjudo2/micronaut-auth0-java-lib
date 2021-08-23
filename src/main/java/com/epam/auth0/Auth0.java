package com.epam.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public class Auth0 {

    private final static String DOMAIN = "dev-u39euvpx.eu.auth0.com";
    private final static String CLIENT_ID = "ksadbUybFMTIEoDBSmdHzD3UGVuqVO4O";
    private final static String CLIENT_SECRET = "1XAv8OMJ_LcHYvEOml_1TBPiay-zC3Z-WJI0YGWYGlsGf6ms7RxmqRBuH8o9vcp4";

    private final AuthAPI authAPI;

    public Auth0() {
        authAPI = initAuthAPI();
    }

    private AuthAPI initAuthAPI(){
        return new AuthAPI(DOMAIN, CLIENT_ID, CLIENT_SECRET);
    }

    public ManagementAPI initManagementByAuthRequest(){
        AuthRequest authRequest = authAPI.requestToken("https://" + DOMAIN + "/api/v2/");
        TokenHolder tokenHolder = new Executor<TokenHolder>().job(authRequest);
        return new ManagementAPI(DOMAIN, tokenHolder.getAccessToken());
    }




}
