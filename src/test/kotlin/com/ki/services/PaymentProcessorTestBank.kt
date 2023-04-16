package com.ki.services

import com.ki.Fixture
import com.ki.models.Bank
import com.ki.models.Payment
import org.junit.Assert
import org.junit.Test

class PaymentProcessorTestBank {
    @Test
    fun testGetPayments() {
        val fixturePath = Fixture.getPath("bank_payments_mixed.csv")
        val processor = PaymentProcessor()
        val payments = processor.getPayments(fixturePath, "bank")
        Assert.assertEquals(2, payments.size.toLong())
        Assert.assertEquals(20, payments[0].type!!.accountId)
        Assert.assertEquals(60, payments[1].type!!.accountId)
    }

    @Test
    fun testGetPaymentsEmpty() {
        val fixturePath = Fixture.getPath("bank_payments_empty.csv")
        val processor = PaymentProcessor()
        val payments = processor.getPayments(fixturePath, "bank")
        Assert.assertEquals(0, payments.size.toLong())
    }

    @Test
    fun testVerifyPayments() {
        val payment1 = createPayment(1)
        val payment2 = createPayment(2)
        val payments = arrayOf<Payment>(payment1, payment2)
        val processor = PaymentProcessor()
        val result = processor.verifyPayments(payments)
        val expected = arrayOf(payment1, payment2)
        Assert.assertArrayEquals(expected, result)
    }

    private fun createPayment(accountId: Int): Payment {
        val bank = Bank(accountId)
        val payment = Payment()
        payment.type = bank
        return payment
    }
}
