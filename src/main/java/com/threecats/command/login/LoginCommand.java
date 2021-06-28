package com.threecats.command.login;

import com.threecats.command.SingleArgCommand;
import com.threecats.db.Database;
import com.threecats.out.Outputter;
import com.threecats.user.UserCommandRouter;

import javax.inject.Inject;

public class LoginCommand extends SingleArgCommand {
    private final Outputter outputter;
    private final Database database;
    private final UserCommandRouter.Factory userCommandRouterFactory;

    @Inject
    LoginCommand(Database database, Outputter outputter, UserCommandRouter.Factory userCommandRouterFactory) {
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
        this.database = database;
        this.userCommandRouterFactory = userCommandRouterFactory;
    }

    @Override
    protected Result handleArg(String username) {
        outputter.output(username + " is logged in.");
        Database.Account account = database.getAccount(username);
        outputter.output("Balance is: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandRouterFactory.create(account).router()
        );
    }
}
