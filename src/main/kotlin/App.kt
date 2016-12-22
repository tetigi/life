import com.monzo.api.MonzoService
import com.palantir.remoting1.jaxrs.JaxRsClient
import com.palantir.tokens.auth.AuthHeader
import java.io.File

fun main(args: Array<String>) {
    println("Hello, World!")

    val client = JaxRsClient.builder().build(MonzoService::class.java, "monzo", listOf("https://api.monzo.com"))
    val token = File("var/token.txt").readText().trim()
    val authHeader = AuthHeader.valueOf(token)

    val incoming = File("var/incoming.txt").readText().lines().filter { !it.isEmpty() }.map(String::toInt).sum()
    val outgoing = File("var/outgoing.txt").readText().lines().filter{ !it.isEmpty() }.map(String::toInt).sum()

    //println(client.getBalance(authHeader, "acc_00009Co1o7WqTLfqSJoytV"))
    //println(client.listTransactions(authHeader, "acc_00009Co1o7WqTLfqSJoytV"))

    val transactions = client.listTransactions(authHeader, "acc_00009Co1o7WqTLfqSJoytV").transactions.filter { !it.is_load }
    val totalSpend = transactions.filter { !it.is_load }.map { it.amount }.sum()

    println("So far I have spent ${totalSpend/100.0}GBP")

    val moneyPerMonth = transactions
            .groupBy { it.created.monthOfYear }
            .mapValues { incoming + (it.value.map { it.amount }.sum() - outgoing) }

    val avgMoney = moneyPerMonth.values.sum() / moneyPerMonth.size
    println("My money per month is ${(avgMoney)/100}GBP")
}
