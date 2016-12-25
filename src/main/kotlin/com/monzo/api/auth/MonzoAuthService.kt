package com.monzo.api.auth

import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.monzo.api.MonzoAuthorizationRequest
import com.monzo.api.MonzoRefreshRequest
import com.monzo.api.MonzoTokenResponse
import com.palantir.remoting1.ext.jackson.ObjectMappers

class MonzoAuthService {
    val MONZO_URL: String = "https://api.monzo.com/oauth2/token"

    init {
        Unirest.setObjectMapper(object : ObjectMapper {
            val mapper = ObjectMappers.guavaJdk7()
            override fun writeValue(value: Any?): String = mapper.writeValueAsString(value)

            override fun <T : Any?> readValue(value: String?, valueType: Class<T>?): T = mapper.readValue(value, valueType)
        })
    }

    fun authorizeToken(request: MonzoAuthorizationRequest): MonzoTokenResponse =
        Unirest.post(MONZO_URL)
                .field("client_id", request.client_id)
                .field("client_secret", request.client_secret)
                .field("code", request.code)
                .field("grant_type", request.grant_type)
                .field("redirect_uri", request.redirect_uri)
                .asObject(MonzoTokenResponse::class.java).body


    fun refreshToken(request: MonzoRefreshRequest): MonzoTokenResponse =
            Unirest.post(MONZO_URL)
                    .field("client_id", request.client_id)
                    .field("client_secret", request.client_secret)
                    .field("refresh_token", request.refresh_token)
                    .field("grant_type", request.grant_type)
                    .asObject(MonzoTokenResponse::class.java).body

}
