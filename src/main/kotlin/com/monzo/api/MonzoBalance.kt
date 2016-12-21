package com.monzo.api

class MonzoBalance {
    var balance: Int = 0
    var spend_today: Int = 0
    var local_exchange_rate: Int = 0

    lateinit var currency: String
    lateinit var local_currency: String
    lateinit var local_spend: List<MonzoLocalSpend>

    override fun toString(): String {
        return "MonzoBalance(balance=$balance, spend_today=$spend_today, local_exchange_rate=$local_exchange_rate, currency='$currency', local_currency='$local_currency', local_spend=$local_spend)"
    }
}
