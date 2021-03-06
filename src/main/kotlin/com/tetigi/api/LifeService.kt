package com.tetigi.api

import com.tetigi.api.lametric.LifeMetricResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/life")
interface LifeService {
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping(): String = "pong"

    @GET
    @Path("/utility")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUtility(): LifeUtilityResponse

    @GET
    @Path("/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMetrics(): LifeMetricResponse
}
