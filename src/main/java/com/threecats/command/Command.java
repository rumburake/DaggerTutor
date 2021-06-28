package com.threecats.command;

import java.util.List;
import java.util.Optional;

/** Logic to process some user input. */
public interface Command {

    class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        public Result(Status status, Optional<CommandRouter> nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        public Result(Status status) {
            this.status = status;
            this.nestedCommandRouter = Optional.empty();
        }

        public static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            return new Result(Status.HANDLED, Optional.of(nestedCommandRouter));
        }

        public Status getStatus() {
            return status;
        }

        public Optional<CommandRouter> getNestedCommandRouter() {
            return nestedCommandRouter;
        }
    }

    /** Process the rest of the command's words and do something. */
    Result handleInput(List<String> input);

    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}
