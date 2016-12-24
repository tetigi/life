package com.monzo.api

class MonzoRefreshRequest(val client_id: String, val client_secret: String, val refresh_token: String) {
    val grant_type: String = "refresh_token"
}
