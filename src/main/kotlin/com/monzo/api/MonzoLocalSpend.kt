package com.monzo.api

class MonzoLocalSpend {
    var spend_today: Int = 0
    lateinit var currency: String

    override fun toString(): String {
        return "MonzoLocalSpend(spend_today=$spend_today, currency='$currency')"
    }
}

