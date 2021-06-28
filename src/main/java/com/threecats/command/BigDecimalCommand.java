package com.threecats.command;

import com.threecats.out.Outputter;

import java.math.BigDecimal;

public abstract class BigDecimalCommand extends SingleArgCommand {

    Outputter outputter;

    public BigDecimalCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    protected Result handleArg(String arg) {
        BigDecimal amount = null;
        try {
            amount = new BigDecimal(arg);
        } catch (NumberFormatException e) {
            // nothing
        }
        if (amount == null) {
            outputter.output("Amount invalid: " + arg);
            return new Result(Status.INVALID);
        }
        handleAmount(amount);
        return new Result(Status.HANDLED);
    }

    public abstract void handleAmount(BigDecimal amount);
}
