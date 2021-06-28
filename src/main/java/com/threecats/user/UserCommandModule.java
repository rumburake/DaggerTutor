package com.threecats.user;

import com.threecats.command.Command;
import com.threecats.command.deposit.DepositCommand;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface UserCommandModule {

    @Binds
    @IntoMap
    @StringKey("deposit")
    Command depositCommand(DepositCommand command);

}
