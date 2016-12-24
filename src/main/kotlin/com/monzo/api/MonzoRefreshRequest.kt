package com.monzo.api

class MonzoRefreshRequest(client_id: String, client_secret: String, refresh_token: String)
    : MonzoTokenRequest(client_id, client_secret, refresh_token) {
    override val grant_type: String = "refresh_token"
}
