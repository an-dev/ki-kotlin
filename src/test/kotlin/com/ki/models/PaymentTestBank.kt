package com.ki.models

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class PaymentTestBank {
    @Test
    fun testPaymentFromCsvRow() {
        val CUSTOMER_ID = 123
        val AMOUNT = "2000"
        val ACCOUNT_ID = 45
        val DATE = "2019-02-01"
        val data = arrayOf(
            CUSTOMER_ID.toString(),
            DATE,
            AMOUNT, ACCOUNT_ID.toString(),
        )
        val payment = Payment(data)
        Assert.assertEquals(CUSTOMER_ID, payment.customerId)
        Assert.assertEquals(1960, payment.amount)
        Assert.assertEquals(40, payment.fee)
        Assert.assertEquals(LocalDate.of(2019, 2, 1), payment.date)
        Assert.assertTrue(payment.type is Bank)
        val bank = payment.type
        Assert.assertEquals(ACCOUNT_ID, bank!!.accountId)
    }

    @Test
    fun testIsSuccessful() {
        val payment = Payment()
        payment.type = Bank(123)
        Assert.assertTrue(payment.isSuccessful)
    }
}
