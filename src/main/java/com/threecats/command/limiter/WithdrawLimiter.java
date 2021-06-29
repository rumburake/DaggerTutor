package com.threecats.command.limiter;

import com.threecats.command.withdraw.PerSession;
import com.threecats.config.MaxSession;
import com.threecats.out.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

@PerSession
public class WithdrawLimiter {

    BigDecimal available;
    Outputter outputter;

    @Inject
    WithdrawLimiter(@MaxSession BigDecimal available, Outputter outputter) {
        System.out.println("Creating a new " + this);
        this.available = available;
        this.outputter = outputter;
    }

    public boolean canWithdraw(BigDecimal amount) {
        if (available.compareTo(amount) < 0) {
            outputter.output("Session exceeded! Try less than: " + available);
            return false;
        }
        return true;
    }

    public void recDeposit(BigDecimal amount) {
        available = available.add(amount);
    }

    public void recWithdraw(BigDecimal amount) {
        available = available.subtract(amount);
    }
}
