package com.monzo.api

class MonzoTokenResponse {
    lateinit var access_token: String
    lateinit var client_id: String
    lateinit var refresh_token: String
    lateinit var token_type: String
    lateinit var user_id: String
    var expires_in: Int = 0
}
