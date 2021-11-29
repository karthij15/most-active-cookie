package com.quantcast.cookie.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import static com.quantcast.cookie.util.Constants.*;

public class CLIOptions {

    private static CLIOptions instance = null;

    public Options getCliOptions() {
        return cliOptions;
    }

    private final Options cliOptions;

    private CLIOptions() {
        cliOptions = new Options();

        Option logFilePath = Option.builder(LOG_FILE_ARG)
                .longOpt(LONG_NAME_FOR_LOG_FILE_OPTION)
                .desc(DESC_LOG_FILE_OPTION)
                .hasArg(FLAG_IS_INITIALIZED)
                .required(FLAG_IS_MANDATORY)
                .build();

        Option queryDate = Option.builder(QUERY_DATE_ARG)
                .longOpt(LONG_NAME_FOR_LOG_QUERY_DATE_OPTION)
                .desc(DESC_QUERY_DATE_OPTION)
                .hasArg(FLAG_IS_INITIALIZED)
                .required(FLAG_IS_MANDATORY)
                .build();

        cliOptions.addOption(logFilePath)
                .addOption(queryDate);

    }

    public static CLIOptions getInstance() {
        if (instance == null) {
            instance = new CLIOptions();
        }

        return instance;
    }
}
