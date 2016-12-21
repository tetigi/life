package com.monzo.api

class MonzoAccount {
    lateinit var id: String
    lateinit var created: String
    lateinit var description: String

    override fun toString(): String {
        return "MonzoAccount(id='$id', created='$created', description='$description')"
    }
}
