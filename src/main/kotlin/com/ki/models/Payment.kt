package com.ki.models

import com.ki.Config
import java.math.BigDecimal
import java.time.LocalDate

class Payment {
    var customerId = 0
    var date: LocalDate? = null
    var amount = 0
    var fee = 0

    var source = ""

    var type: TransactionType? = null

    constructor() {}
    constructor(data: Array<String>, source: String = "card") {
        customerId = data[0].toInt()
        val paymentFeeRate = Config.paymentFeeRate
        val totalAmount = data[2].toInt()
        fee = paymentFeeRate.multiply(BigDecimal(totalAmount)).toInt()
        amount = totalAmount - fee
        date = LocalDate.parse(data[1])

        this.source = source

        // Pseudo factory, this can be moved
        // to a class responsible for creating the right
        // transaction class based on 'source'
        fun processCardTx(): Unit {
            val cardId = data[3].toInt()
            val status = data[4]
            val card = Card(cardId, status)
            this.type = card
        }

        fun processBankTx(): Unit {
            val accountId = data[3].toInt()
            val bank = Bank(accountId)
            this.type = bank
        }

        when (source) {
            "card" -> processCardTx()
            "bank" -> processBankTx()
            else ->
                    throw Exception(
                            "Unknown payment source"
                    ) // Depends on how we want to handle unknown sources
        }
    }

    val isSuccessful: Boolean
        get() = type?.isSuccessful() ?: false
}
