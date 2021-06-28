package com.threecats.cli;

import com.threecats.command.CommandProcessor;
import com.threecats.command.CommandRouter;

import java.util.Scanner;

public class CommandLineAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CommandProcessor commandProcessor = DaggerCommandProcessorFactory.create().processor();

        while (scanner.hasNextLine()) {
            commandProcessor.process(scanner.nextLine());
        }
    }
}
