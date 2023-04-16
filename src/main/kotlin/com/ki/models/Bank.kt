package com.ki.models

class Bank: TransactionType {
    constructor(accountId: Int) {
        this.accountId = accountId
    }

    override fun isSuccessful(): Boolean {
        return true
    }
}
