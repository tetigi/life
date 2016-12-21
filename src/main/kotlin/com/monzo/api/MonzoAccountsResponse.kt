package com.monzo.api

class MonzoAccountsResponse {
    lateinit var accounts: List<MonzoAccount>

    override fun toString(): String {
        return "MonzoAccountsResponse(accounts=$accounts)"
    }
}
