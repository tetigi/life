package com.monzo.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.tetigi.utils.DateTimeDeserializer
import org.joda.time.DateTime

class MonzoTransaction {
    lateinit var id: String
    @JsonDeserialize(using = DateTimeDeserializer::class) lateinit var created: DateTime
    lateinit var description: String
    var amount: Int = 0
    lateinit var currency: String
    var merchant: Any? = null
    lateinit var notes: String
    var account_balance: Int = 0
    lateinit var metadata: Map<String, Any>
    lateinit var attachments: List<Any>
    lateinit var category: String
    @JsonProperty("is_load") var is_load: Boolean = false
    lateinit var settled: String
    var local_amount: Int = 0
    lateinit var local_currency: String
    lateinit var updated: String
    lateinit var account_id: String
    lateinit var counterparty: Map<String, Any>
    lateinit var scheme: String
    lateinit var dedupe_id: String
    lateinit var decline_reason: String
    var originator: Boolean = false
    var include_in_spending: Boolean = false

    override fun toString(): String {
        return "MonzoTransaction(id='$id', created='$created', description='$description', amount=$amount, account_balance=$account_balance)"
    }
}
