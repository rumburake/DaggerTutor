package com.threecats.command;

import com.threecats.command.Command;

import javax.inject.Inject;
import java.util.*;

public final class CommandRouter {
    private Map<String, Command> commands = new HashMap<>();

    Long startMillis;

    @Inject
    CommandRouter(Map<String, Command> commands) {
        this.commands = commands;
    }

    void startTimer() {
        startMillis = System.currentTimeMillis();
    }

    void readTimer() {
        System.out.format("Time: %d ms\n", System.currentTimeMillis() - startMillis);
    }

    public Command.Result route(String input) {
        startTimer();
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            readTimer();
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            readTimer();
            return invalidCommand(input);
        }

        Command.Result result =
                command.handleInput(splitInput.subList(1, splitInput.size()));
        if (result.getStatus() == Command.Status.INVALID) {
            System.out.println(commandKey + ": invalid arguments");
        }
        readTimer();
        return result;
    }

    private Command.Result invalidCommand(String input) {
        System.out.println(
                String.format("couldn't understand \"%s\". please try again.", input));
        return new Command.Result(Command.Status.INVALID);
    }

    // Split on whitespace
    private static List<String> split(String string) {
        return Arrays.asList(string.split(" "));
    }
}
