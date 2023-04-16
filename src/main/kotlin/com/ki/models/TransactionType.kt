package com.ki.models

abstract class TransactionType {
    var cardId = 0
    var accountId = 0
    var status: String? = null

    constructor() {}
    abstract fun isSuccessful(): Boolean
}
