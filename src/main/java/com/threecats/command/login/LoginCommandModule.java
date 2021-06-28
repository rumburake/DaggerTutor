package com.threecats.command.login;

import com.threecats.command.Command;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface LoginCommandModule {
    @Binds
    @IntoMap
    @StringKey("login")
    Command loginCommand(LoginCommand command);
}
