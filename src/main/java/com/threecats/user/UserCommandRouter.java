package com.threecats.user;


import com.threecats.command.CommandRouter;
import com.threecats.db.Database;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

@Subcomponent(modules = UserCommandModule.class)
public interface UserCommandRouter {
    CommandRouter router();

    @Subcomponent.Factory
    interface Factory {
        UserCommandRouter create(@BindsInstance Database.Account account);
    }

    @Module(subcomponents = UserCommandRouter.class)
    interface InstallationModule {}
}
