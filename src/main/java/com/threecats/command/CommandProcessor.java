package com.threecats.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayDeque;
import java.util.Deque;

@Singleton
public class CommandProcessor {
    private final Deque<CommandRouter> commandRouterStack = new ArrayDeque<>();

    @Inject
    CommandProcessor(CommandRouter firstCommandRouter) {
        commandRouterStack.push(firstCommandRouter);
    }

    public Command.Status process(String input) {
        Command.Result result = commandRouterStack.peek().route(input);
        if (result.getStatus().equals(Command.Status.INPUT_COMPLETED)) {
            commandRouterStack.pop();
            return commandRouterStack.isEmpty()
                    ? Command.Status.INPUT_COMPLETED
                    : Command.Status.HANDLED;
        }
        result.getNestedCommandRouter().ifPresent(commandRouterStack::push);
        return result.getStatus();
    }
}
