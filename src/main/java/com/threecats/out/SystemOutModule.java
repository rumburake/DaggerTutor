package com.threecats.out;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SystemOutModule {
    @Provides
    static Outputter textOutputter() {
        System.out.println("Outputter is System.out.");
        return System.out::println;
    }
}
