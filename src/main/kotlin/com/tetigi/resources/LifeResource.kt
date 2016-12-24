package com.tetigi.resources

import com.monzo.api.MonzoService
import com.palantir.tokens.auth.AuthHeader
import com.tetigi.api.LifeService
import com.tetigi.api.LifeUtilityResponse

class LifeResource(val lazyAuthHeader: () -> AuthHeader, val monzoService: MonzoService,
                   val incoming: Int, val outgoing: Int) : LifeService {

    val authHeader: AuthHeader by lazy { lazyAuthHeader() }

    override fun getUtility(): LifeUtilityResponse {
        val transactions =
                monzoService.listTransactions(authHeader, "acc_00009Co1o7WqTLfqSJoytV").transactions.filter { !it.is_load }

        val moneyPerMonth = transactions
                .groupBy { 12*it.created.year + it.created.monthOfYear }
                .mapValues { incoming + (it.value.map { it.amount }.sum() - outgoing) }

        val transactionMonths = moneyPerMonth.keys.sortedDescending().take(3)
        val threeMonthAverage = transactionMonths.map { moneyPerMonth[it]!! }.sum() / 3
        val currentDiff = moneyPerMonth[transactionMonths[0]]!!

        return LifeUtilityResponse(currentDiff, currentDiff - threeMonthAverage)
    }
}
