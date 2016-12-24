package com.monzo.api

abstract class MonzoTokenRequest(val client_id: String, val client_secret: String, val refresh_token: String) {
    abstract val grant_type: String
}

