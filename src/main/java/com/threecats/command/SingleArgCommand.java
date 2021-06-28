package com.threecats.command;

import com.threecats.command.Command;

import java.util.List;

public abstract class SingleArgCommand implements Command {

    @Override
    public Result handleInput(List<String> input) {
        return input.size() == 1 ? handleArg(input.get(0)) : new Result(Status.INVALID);
    }

    protected abstract Result handleArg(String arg);
}
