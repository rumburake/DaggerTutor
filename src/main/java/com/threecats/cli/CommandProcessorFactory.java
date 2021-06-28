package com.threecats.cli;

import com.threecats.command.CommandProcessor;
import com.threecats.command.hello.HelloWorldModule;
import com.threecats.command.login.LoginCommandModule;
import com.threecats.config.AmountsModule;
import com.threecats.out.SystemOutModule;
import com.threecats.user.UserCommandRouter;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {
        LoginCommandModule.class,
        HelloWorldModule.class,
        SystemOutModule.class,
        UserCommandRouter.InstallationModule.class,
        AmountsModule.class
})
@Singleton
interface CommandProcessorFactory {
    CommandProcessor processor();
}
