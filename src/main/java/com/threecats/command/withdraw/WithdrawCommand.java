package com.threecats.command.withdraw;

import com.threecats.command.BigDecimalCommand;
import com.threecats.command.limiter.WithdrawLimiter;
import com.threecats.config.MaxTransaction;
import com.threecats.config.MinBalance;
import com.threecats.db.Database;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

public class WithdrawCommand extends BigDecimalCommand {

    Database.Account account;
    Outputter outputter;
    WithdrawLimiter withdrawLimiter;
    BigDecimal minBalance;
    BigDecimal maxTransaction;

    @Inject
    public WithdrawCommand(
            Database.Account account,
            Outputter outputter,
            WithdrawLimiter withdrawLimiter,
            @MinBalance BigDecimal minBalance,
            @MaxTransaction BigDecimal maxTransaction
    ) {
        super(outputter);
        System.out.println("Creating a new " + this);
        this.account = account;
        this.outputter = outputter;
        this.withdrawLimiter = withdrawLimiter;
        this.minBalance = minBalance;
        this.maxTransaction = maxTransaction;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        if (maxTransaction.compareTo(amount) < 0) {
            outputter.output("Transaction too large!");
        } else if (account.balance().subtract(amount).compareTo(minBalance) < 0) {
            outputter.output("Not enough cash!");
        } else if (withdrawLimiter.canWithdraw(amount)) {
            account.withdraw(amount);
            withdrawLimiter.recWithdraw(amount);
            outputter.output("New Balance: " + account.balance());
        }
    }
}
