package com.tetigi.api

import com.monzo.api.MonzoTokenResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/life")
interface LifeService {
    @GET
    @Path("/oauth/callback")
    @Produces(MediaType.APPLICATION_JSON)
    fun authCallback(@QueryParam("code") authorizationCode: String, @QueryParam("state") stateToken: String): MonzoTokenResponse

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping(): String = "pong"
}
