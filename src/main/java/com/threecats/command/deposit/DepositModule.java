package com.threecats.command.deposit;

import com.threecats.command.Command;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface DepositModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    Command depositCommand(DepositCommand command);
}
