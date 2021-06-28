package com.threecats.command.deposit;

import com.threecats.command.BigDecimalCommand;
import com.threecats.db.Database;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class DepositCommand implements BigDecimalCommand {

    private final Database.Account account;
    private Outputter outputter;

    @Inject
    DepositCommand(Database.Account account, Outputter outputter) {
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
        this.account = account;
    }

    @Override
    public Result handleInput(List<String> input) {
        if (input.size() != 1) {
            return new Result(Status.INVALID);
        }
        handleAmount(new BigDecimal(input.get(0)));
        return new Result(Status.HANDLED);
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output(account.username() + " has now: " + account.balance());
    }
}
