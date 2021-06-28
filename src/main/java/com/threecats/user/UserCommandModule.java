package com.threecats.user;

import com.threecats.command.Command;
import com.threecats.command.deposit.DepositCommand;
import com.threecats.command.logout.LogoutCommand;
import com.threecats.command.withdraw.WithdrawCommand;
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

    @Binds
    @IntoMap
    @StringKey("withdraw")
    Command withdrawCommand(WithdrawCommand command);

    @Binds
    @IntoMap
    @StringKey("logout")
    Command logoutCommand(LogoutCommand command);

}
