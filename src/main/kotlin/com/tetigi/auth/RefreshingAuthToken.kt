package com.tetigi.auth

import com.monzo.api.MonzoRefreshRequest
import com.monzo.api.MonzoService
import com.palantir.tokens.auth.AuthHeader
import com.palantir.tokens.auth.BearerToken
import java.time.Clock
import java.time.Instant

class RefreshingAuthToken(val monzoService: MonzoService, var refreshToken: String,
                          val clientId: String, val clientSecret: String) : AuthHeader() {

    private var nextRefresh = Instant.MIN
    private var expiry = Instant.MIN
    private lateinit var cachedBearerToken: BearerToken

    internal fun getClock(): Clock {
        return Clock.systemUTC()
    }

    private fun shouldRefresh(): Boolean {
        return getClock().instant().isAfter(nextRefresh)
    }

    override fun getBearerToken(): BearerToken {
        if (shouldRefresh()) {
            val tokenResponse = monzoService.refreshToken(MonzoRefreshRequest(clientId, clientSecret, refreshToken))
            cachedBearerToken = BearerToken.valueOf(tokenResponse.access_token)
            nextRefresh = getClock().instant().plusSeconds(tokenResponse.expires_in.toLong() / 2)
            expiry = getClock().instant().plusSeconds(tokenResponse.expires_in.toLong())
            refreshToken = tokenResponse.refresh_token
        }

        return cachedBearerToken
    }
}