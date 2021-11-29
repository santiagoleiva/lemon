package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.out.ExistsUserPortOut
import me.lemon.challenge.application.port.out.QueryCurrencyPortOut
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.config.exception.UserAliasUnavailableException
import me.lemon.challenge.config.exception.UserEmailUnavailableException
import me.lemon.challenge.domain.Balance
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CreateUserUseCase(
    private val upsertUserAdapter: UpsertUserPortOut,
    private val queryCurrencyAdapter: QueryCurrencyPortOut,
    private val existsUserAdapter: ExistsUserPortOut
) : CreateUserPortIn {

    override fun execute(command: CreateUserPortIn.Command): User {
        doValidations(command)
        val user = command.toDomain()
        user.addToWallet(getInitialBalances())
        return upsertUserAdapter.create(user)
    }

    private fun doValidations(command: CreateUserPortIn.Command) {
        if (existsUserAdapter.byAlias(command.alias)) throw UserAliasUnavailableException()
        if (existsUserAdapter.byEmail(command.email)) throw UserEmailUnavailableException()
    }

    private fun getInitialBalances(): List<Balance> = queryCurrencyAdapter
        .query()
        .map { it toBalanceWithAmount BigDecimal.ZERO }

    private fun CreateUserPortIn.Command.toDomain(): User = User(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email
    )

    private infix fun Currency.toBalanceWithAmount(amount: BigDecimal): Balance = Balance(
        currency = this,
        amount = amount
    )

    private infix fun User.addToWallet(balances: List<Balance>) = this.wallet.addAll(balances)

}
