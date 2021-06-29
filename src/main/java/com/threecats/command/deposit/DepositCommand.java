package com.threecats.command.deposit;

import com.threecats.command.BigDecimalCommand;
import com.threecats.command.limiter.WithdrawLimiter;
import com.threecats.db.Database;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

public class DepositCommand extends BigDecimalCommand {

    private final Database.Account account;
    private Outputter outputter;
    private WithdrawLimiter withdrawLimiter;

    @Inject
    DepositCommand(Database.Account account, Outputter outputter, WithdrawLimiter withdrawLimiter) {
        super(outputter);
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
        this.account = account;
        this.withdrawLimiter = withdrawLimiter;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        withdrawLimiter.recDeposit(amount);
        outputter.output(account.username() + " has now: " + account.balance());
    }
}
