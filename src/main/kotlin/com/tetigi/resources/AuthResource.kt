package com.tetigi.resources

import com.monzo.api.MonzoAuthorizationRequest
import com.monzo.api.MonzoService
import com.monzo.api.MonzoTokenResponse
import com.palantir.tokens.auth.AuthHeader
import com.tetigi.api.AuthService
import com.tetigi.auth.RefreshingAuthToken

class AuthResource(val clientId: String, val monzoService: MonzoService) : AuthService {

    lateinit var authHeader: AuthHeader

    override fun authCallback(authorizationCode: String, stateToken: String): MonzoTokenResponse {
        val request = MonzoAuthorizationRequest(clientId, stateToken, "", authorizationCode)
        val tokenResponse = monzoService.authorizeToken(request)

        authHeader = RefreshingAuthToken(monzoService, tokenResponse.refresh_token, clientId, stateToken)

        return tokenResponse
    }
}
