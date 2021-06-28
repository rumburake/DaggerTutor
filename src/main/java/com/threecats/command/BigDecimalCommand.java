package com.threecats.command;

import java.math.BigDecimal;

public interface BigDecimalCommand extends Command {

    public void handleAmount(BigDecimal amount);
}
