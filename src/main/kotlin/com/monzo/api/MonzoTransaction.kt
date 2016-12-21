package com.monzo.api

import javax.print.attribute.IntegerSyntax

class MonzoTransaction {
    lateinit var id: String
    lateinit var created: String
    lateinit var description: String
    var amount: Int = 0
    lateinit var currency: String
    lateinit var merchant: Map<String, Any>//MonzoMerchant?
    lateinit var notes: String
    lateinit var accountBalance: IntegerSyntax
    lateinit var metadata: Map<String, Any>
    lateinit var attachments: List<Any>
    lateinit var category: String
    var isLoad: Boolean = false
    lateinit var settled: String
    var localAmount: Int = 0
    lateinit var localCurrency: String
    lateinit var updated: String
    lateinit var accountId: String
    lateinit var counterparty: Map<String, Any>
    lateinit var scheme: String
    lateinit var dedupe_id: String
    var originator: Boolean = false
    var includeInSpending: Boolean = false
}
