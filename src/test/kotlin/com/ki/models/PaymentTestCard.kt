package com.ki.models

import java.time.LocalDate
import java.util.stream.Stream
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PaymentTestCard {
    @Test
    fun testPaymentFromCsvRow() {
        val CUSTOMER_ID = 123
        val AMOUNT = "2000"
        val CARD_STATUS = "processed"
        val CARD_ID = 45
        val DATE = "2019-02-01"
        val data = arrayOf(CUSTOMER_ID.toString(), DATE, AMOUNT, CARD_ID.toString(), CARD_STATUS)
        val payment = Payment(data)
        Assert.assertEquals(CUSTOMER_ID, payment.customerId)
        Assert.assertEquals(1960, payment.amount)
        Assert.assertEquals(40, payment.fee)
        Assert.assertEquals(LocalDate.of(2019, 2, 1), payment.date)
        Assert.assertTrue(payment.type is Card)
        val card = payment.type
        Assert.assertEquals(CARD_ID, card!!.cardId)
        Assert.assertEquals(CARD_STATUS, card.status)
    }

    fun getStatuses(): Stream<Arguments> {
        return Stream.of(
                Arguments.of("successful, true"),
                Arguments.of("declined, false"),
                Arguments.of("error, false"),
        )
    }

    @ParameterizedTest
    @MethodSource("getStatuses")
    fun testIsSuccessful(status: String, result: Boolean) {
        val cardId = 123
        val payment = Payment()
        payment.type = Card(cardId, status)
        payment.type!!.cardId = cardId
        payment.type!!.status = status
        Assert.assertEquals(payment.isSuccessful, result)
    }
}
