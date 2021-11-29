package com.quantcast.cookie.cli;

import com.quantcast.cookie.exception.CLIParseException;
import com.quantcast.cookie.request.CookieRequest;
import com.quantcast.cookie.util.Constants;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

public class CLIParser extends DefaultParser {

    final static Logger LOG = Logger.getLogger(CLIParser.class.getName());

    private final Options options;

    public CLIParser() {
        this.options = CLIOptions.getInstance().getCliOptions(); //Default Options
    }

    public CLIParser(Options options) {
        this.options = options;
    }

    public CookieRequest parse(String... args) {
        CookieRequest request = null;
        try {
            CommandLine inputParams = this.parse(this.options, args);

            request = new CookieRequest();
            request.setLogFilePath(inputParams.getOptionValue(Constants.LOG_FILE_ARG));
            request.setQueryDate(inputParams.getOptionValue(Constants.QUERY_DATE_ARG));
        } catch (Exception e) {
            LOG.debug("Exception while parsing command line arguments");
            throw new CLIParseException(e);
        }

        return request;
    }
}
