


import com.monzo.api.MonzoService
import com.monzo.api.auth.MonzoAuthService
import com.palantir.remoting1.jaxrs.JaxRsClient
import com.tetigi.resources.AuthResource
import com.tetigi.resources.LifeResource
import io.dropwizard.Application
import io.dropwizard.setup.Environment
import org.apache.commons.lang3.RandomStringUtils


class App : Application<LifeConfig>() {
    val USER_AGENT: String = "monzo"
    val MONZO_URL: String = "https://api.monzo.com"
    override fun getName(): String = "life"

    override fun run(configuration: LifeConfig?, environment: Environment?) {

        val client = JaxRsClient.builder().build(MonzoService::class.java, USER_AGENT, listOf(MONZO_URL))
        val auth = MonzoAuthService()

        /*
        val token = File("var/token.txt").readText().trim()
        val authHeader = AuthHeader.valueOf(token)
        */

        if (configuration != null) {
            val clientId = configuration.clientId
            val clientSecret = configuration.clientSecret
            val redirect = "http://li1522-73.members.linode.com:8080/oauth/callback"
            //val redirect = "http://localhost:8080/oauth/callback"
            val state = RandomStringUtils.randomAlphanumeric(18)

            println("https://auth.getmondo.co.uk/?client_id=$clientId&redirect_uri=$redirect&response_type=code&state=$state")

            val authResource = AuthResource(clientId, clientSecret, state, redirect, auth)
            val lifeResource = LifeResource({authResource.authHeader}, client, configuration.incoming.sum(), configuration.outgoing.sum())
            environment?.jersey()?.register(authResource)
            environment?.jersey()?.register(lifeResource)
        } else {
            error("No configuration provided. Cannot proceed.")
        }
    }
}

fun main(args: Array<String>) {
    println("Hello, World!")

    App().run(*args)
}
