package com.threecats.command.hello;


import com.threecats.command.Command;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.util.List;

public class HelloWorldCommand implements Command {

    Outputter outputter;

    @Inject
    HelloWorldCommand(Outputter outputter) {
        System.out.println("Creating a new " + this);
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        if (!input.isEmpty()) {
            return new Result(Status.INVALID);
        }
        outputter.output("world!");
        return new Result(Status.HANDLED);
    }
}
