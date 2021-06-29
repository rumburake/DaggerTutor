package com.threecats.command.login;

import com.threecats.command.SingleArgCommand;
import com.threecats.db.Database;
import com.threecats.out.Outputter;
import com.threecats.user.UserCommandRouter;

import javax.inject.Inject;
import java.util.Optional;

public class LoginCommand extends SingleArgCommand {
    private final Outputter outputter;
    private final Database database;
    private final UserCommandRouter.Factory userCommandRouterFactory;
    private final Optional<Database.Account> account;

    @Inject
    LoginCommand(
            Database database,
            Outputter outputter,
            UserCommandRouter.Factory userCommandRouterFactory,
            Optional<Database.Account> account
    ) {
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
        this.database = database;
        this.userCommandRouterFactory = userCommandRouterFactory;
        this.account = account;
    }

    @Override
    protected Result handleArg(String username) {
        if (account.isPresent()) {
            outputter.output("Already logged in, please logout first!");
            return new Result(Status.HANDLED);
        }
        outputter.output(username + " is logged in.");
        Database.Account account = database.getAccount(username);
        outputter.output("Balance is: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandRouterFactory.create(account).router()
        );
    }
}
