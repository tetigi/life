import com.monzo.api.MonzoService
import com.palantir.remoting1.jaxrs.JaxRsClient
import com.palantir.tokens.auth.AuthHeader
import com.tetigi.resources.LifeResource
import io.dropwizard.Application
import io.dropwizard.setup.Environment
import java.io.File


class App : Application<LifeConfig>() {
    override fun getName(): String = "life"

    override fun run(configuration: LifeConfig?, environment: Environment?) {
        val lifeResource = LifeResource()

        environment?.jersey()?.register(lifeResource)
    }
}

fun main(args: Array<String>) {
    println("Hello, World!")

    /*
    val client = JaxRsClient.builder().build(MonzoService::class.java, "monzo", listOf("https://api.monzo.com"))
    val token = File("var/token.txt").readText().trim()
    val authHeader = AuthHeader.valueOf(token)

    val incoming = File("var/incoming.txt").readText().lines().filter { !it.isEmpty() }.map(String::toInt).sum()
    val outgoing = File("var/outgoing.txt").readText().lines().filter{ !it.isEmpty() }.map(String::toInt).sum()

    val transactions = client.listTransactions(authHeader, "acc_00009Co1o7WqTLfqSJoytV").transactions.filter { !it.is_load }
    val totalSpend = transactions.filter { !it.is_load }.map { it.amount }.sum()

    println("So far I have spent ${totalSpend/100.0}GBP")

    val moneyPerMonth = transactions
            .groupBy { 12*it.created.year + it.created.monthOfYear }
            .mapValues { incoming + (it.value.map { it.amount }.sum() - outgoing) }

    val avgMoney = moneyPerMonth.values.sum() / moneyPerMonth.size
    println("My money per month is ${(avgMoney)/100}GBP")

    // show surplus | diff vs. 3 month moving average
    val transactionMonths = moneyPerMonth.keys.sortedDescending().take(3)
    val threeMonthAverage = transactionMonths.map { moneyPerMonth[it]!! }.sum() / 3
    val currentDiff = moneyPerMonth[transactionMonths[0]]!!
    println("${currentDiff/100}GBP | ${(currentDiff - threeMonthAverage)/100}GBP")
    */

    App().run(*args)
}
