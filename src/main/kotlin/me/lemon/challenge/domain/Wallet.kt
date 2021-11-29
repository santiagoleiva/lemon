package me.lemon.challenge.domain

data class Wallet(
    val balances: MutableList<Balance> = mutableListOf()
) {

    fun add(balances: List<Balance>) = this.balances.addAll(balances)

    companion object {
        fun empty(): Wallet = Wallet()
    }

}
