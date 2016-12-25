package com.tetigi.resources

import com.monzo.api.MonzoAuthorizationRequest
import com.monzo.api.MonzoTokenResponse
import com.monzo.api.auth.MonzoAuthService
import com.palantir.tokens.auth.AuthHeader
import com.tetigi.api.AuthService
import com.tetigi.auth.RefreshingAuthToken

class AuthResource(val clientId: String, val clientSecret: String, val stateToken: String, val redirect: String, val auth: MonzoAuthService) : AuthService {

    lateinit var authHeader: AuthHeader

    override fun authCallback(authorizationCode: String, stateToken: String): MonzoTokenResponse {
        println("Callback received!")
        if (stateToken != this.stateToken) {
            //error("STATE TOKENS DON'T MATCH, ABORT!")
        }

        val tokenResponse = auth.authorizeToken(MonzoAuthorizationRequest(clientId, clientSecret, redirect, authorizationCode))

        authHeader = RefreshingAuthToken(auth, tokenResponse.refresh_token, clientId, clientSecret)

        return tokenResponse
    }
}
