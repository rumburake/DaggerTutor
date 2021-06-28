package com.threecats.command.logout;

import com.threecats.command.Command;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.util.List;

public class LogoutCommand implements Command {

    Outputter outputter;

    @Inject
    LogoutCommand(Outputter outputter) {
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        if (input.size() > 0) {
            return new Result(Status.INVALID);
        }
        return new Result(Status.INPUT_COMPLETED);
    }
}
