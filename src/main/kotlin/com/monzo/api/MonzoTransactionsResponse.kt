package com.monzo.api

class MonzoTransactionsResponse {
    lateinit var transactions: List<MonzoTransaction>

    override fun toString(): String {
        return "MonzoTransactionsResponse(transactions=$transactions)"
    }
}


