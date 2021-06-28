package com.threecats.command.deposit;

import com.threecats.command.BigDecimalCommand;
import com.threecats.db.Database;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

public class DepositCommand extends BigDecimalCommand {

    private final Database.Account account;
    private Outputter outputter;

    @Inject
    DepositCommand(Database.Account account, Outputter outputter) {
        super(outputter);
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
        this.account = account;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output(account.username() + " has now: " + account.balance());
    }
}
