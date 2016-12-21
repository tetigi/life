import com.monzo.api.MonzoService
import com.palantir.remoting1.jaxrs.JaxRsClient
import com.palantir.tokens.auth.AuthHeader
import java.io.File

fun main(args: Array<String>) {
    println("Hello, World!")

    val client = JaxRsClient.builder().build(MonzoService::class.java, "monzo", listOf("https://api.monzo.com"))
    val token = File("var/token.txt").readText().trim()
    val authHeader = AuthHeader.valueOf(token)

    val results = client.listAccounts(authHeader)
    println(results)

    println(client.getBalance(authHeader, "acc_00009Co1o7WqTLfqSJoytV"))
}
