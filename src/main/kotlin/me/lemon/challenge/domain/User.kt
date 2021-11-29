package me.lemon.challenge.domain

data class User(
    val id: Int? = null,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String,
    val wallet: Wallet = Wallet.empty()
) {

    fun addToWallet(balances: List<Balance>) = this.wallet.add(balances)

}
