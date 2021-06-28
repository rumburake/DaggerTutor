package com.threecats.cli;

import com.threecats.command.CommandProcessor;
import com.threecats.command.CommandRouter;
import com.threecats.command.deposit.DepositModule;
import com.threecats.command.hello.HelloWorldModule;
import com.threecats.command.login.LoginCommandModule;
import com.threecats.out.SystemOutModule;
import com.threecats.user.UserCommandModule;
import com.threecats.user.UserCommandRouter;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {
        LoginCommandModule.class,
        HelloWorldModule.class,
        SystemOutModule.class,
        UserCommandRouter.InstallationModule.class
})
@Singleton
interface CommandProcessorFactory {
    CommandProcessor processor();
}
