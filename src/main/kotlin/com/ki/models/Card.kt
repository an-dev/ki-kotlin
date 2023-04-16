package com.ki.models

class Card: TransactionType {
    constructor(cardId: Int, status: String?) {
        this.cardId = cardId
        this.status = status
    }

    override fun isSuccessful(): Boolean {
        return if (this.status != null) this.status == "processed" else false
    }
}
