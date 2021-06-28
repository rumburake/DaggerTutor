package com.threecats.config;

import dagger.Module;
import dagger.Provides;

import java.math.BigDecimal;

@Module
public interface AmountsModule {

    @Provides
    @MaxTransaction
    static BigDecimal maxTransaction() {
        return new BigDecimal(10);
    }

    @Provides
    @MinBalance
    static BigDecimal minBalance() {
        return new BigDecimal(1);
    }
}
