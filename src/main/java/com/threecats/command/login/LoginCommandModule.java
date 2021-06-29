package com.threecats.command.login;

import com.threecats.command.Command;
import com.threecats.db.Database;
import dagger.Binds;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface LoginCommandModule {
    @Binds
    @IntoMap
    @StringKey("login")
    Command loginCommand(LoginCommand command);

    @BindsOptionalOf
    Database.Account optionalAccount();
}
