package com.ki.services

import com.ki.Fixture
import com.ki.models.Card
import com.ki.models.Payment
import org.junit.Assert
import org.junit.Test

class PaymentProcessorTestCard {
    @Test
    fun testGetPayments() {
        val fixturePath = Fixture.getPath("card_payments_mixed.csv")
        val processor = PaymentProcessor()
        val payments = processor.getPayments(fixturePath, "card")
        Assert.assertEquals(3, payments.size.toLong())
        Assert.assertEquals(30, payments[0].type!!.cardId)
        Assert.assertEquals(45, payments[1].type!!.cardId)
        Assert.assertEquals(10, payments[2].type!!.cardId)
    }

    @Test
    fun testGetPaymentsEmpty() {
        val fixturePath = Fixture.getPath("card_payments_empty.csv")
        val processor = PaymentProcessor()
        val payments = processor.getPayments(fixturePath, "card")
        Assert.assertEquals(0, payments.size.toLong())
    }

    @Test
    fun testVerifyPayments() {
        val payment1 = createPayment(1, "processed")
        val payment2 = createPayment(2, "declined")
        val payment3 = createPayment(3, "processed")
        val payments = arrayOf<Payment>(payment1, payment2, payment3)
        val processor = PaymentProcessor()
        val result = processor.verifyPayments(payments)
        val expected = arrayOf(payment1, payment3)
        Assert.assertArrayEquals(expected, result)
    }

    private fun createPayment(cardId: Int, cardStatus: String): Payment {
        val card = Card(cardId, cardStatus)
        val payment = Payment()
        payment.type = card
        return payment
    }
}
