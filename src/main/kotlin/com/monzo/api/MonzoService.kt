package com.monzo.api

import com.palantir.tokens.auth.AuthHeader
import javax.ws.rs.*
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

interface MonzoService {
    @GET
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    fun listAccounts(@HeaderParam(HttpHeaders.AUTHORIZATION) authHeader: AuthHeader): MonzoAccountsResponse

    @GET
    @Path("/balance")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBalance(@HeaderParam(HttpHeaders.AUTHORIZATION) authHeader: AuthHeader, @QueryParam("account_id") accountId: String): MonzoBalance

    @GET
    @Path("transactions")
    @Produces(MediaType.APPLICATION_JSON)
    fun listTransactions(@HeaderParam(HttpHeaders.AUTHORIZATION) authHeader: AuthHeader, @QueryParam("account_id") accountId: String): MonzoTransactionsResponse
}
