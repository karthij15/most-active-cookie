package com.quantcast.cookie;

import com.quantcast.cookie.exception.CLIParseException;
import com.quantcast.cookie.exception.InvalidCookieTimeException;
import com.quantcast.cookie.exception.InvalidCookieValueException;
import com.quantcast.cookie.exception.InvalidLogException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CookieLogAnalyzerApplicationTest {

    final static Logger LOG = Logger.getLogger(CookieLogAnalyzerApplicationTest.class.getName());
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private MostActiveCookieFinderApplication helper;

    @BeforeEach
    public void setUp() {
        helper = new MostActiveCookieFinderApplication();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @Order(1)
    public void testMissingCommandLineArgs() {
        LOG.info("TEST 0 -- empty command line arguments");
        String[] cmdArgs = new String[]{};

        Throwable thrownException = assertThrows(CLIParseException.class, () -> helper.process(cmdArgs));

        assertEquals(CLIParseException.ERR_MESSAGE, thrownException.getMessage());
    }

    @Test
    @Order(2)
    public void testInvalidCommandLineArgs() {
        LOG.info("TEST 1 -- invalid command line arguments");
        String[] cmdArgs = new String[]{"-s", "src/test/resources/cookie_log.csv", "-d", "2018-12-09"};

        Throwable thrownException = assertThrows(CLIParseException.class, () -> helper.process(cmdArgs));

        assertEquals(CLIParseException.ERR_MESSAGE, thrownException.getMessage());
    }

    @Test
    @Order(3)
    public void testInvalidLogFileException() {
        LOG.info("TEST 2 -- invalid log file exception");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/missing_cookie_log.csv", "-d", "2018-12-09"};

        Throwable thrownException = assertThrows(InvalidLogException.class, () -> helper.process(cmdArgs));

        assertEquals(InvalidLogException.ERR_MESSAGE, thrownException.getMessage());
    }

    @Test
    @Order(4)
    public void testInvalidCookieValueException() {
        LOG.info("TEST 3 -- invalid cookie value exception");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/invalid_cookie_value_log.csv", "-d", "2018-12-09"};

        Throwable thrownException = assertThrows(InvalidCookieValueException.class, () -> helper.process(cmdArgs));

        assertEquals((InvalidCookieValueException.ERR_MESSAGE), thrownException.getMessage());
    }

    @Test
    @Order(5)
    public void testInvalidCookieTimestampException() {
        LOG.info("TEST 4 -- invalid cookie timestamp exception");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/invalid_cookie_timestamp_log.csv", "-d", "2018-12-09"};

        Throwable thrownException = assertThrows(InvalidCookieTimeException.class, () -> helper.process(cmdArgs));

        assertEquals((InvalidCookieTimeException.ERR_MESSAGE), thrownException.getMessage());
    }

    @Test
    @Order(6)
    public void testValidMaxOccurrenceCookie() {
        LOG.info("TEST 5 -- Valid max occurrence cookie ");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/cookie_log.csv", "-d", "2018-12-09"};

        helper.process(cmdArgs);

        Assertions.assertTrue((outputStream.toString().trim()).contains("AtY0laUfhglK3lC7"));
    }

    @Test
    @Order(7)
    public void testValidMaxOccurrenceCookie2() {
        LOG.info("TEST 6 -- Valid max occurrence cookie with different date");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/cookie_log.csv", "-d", "2018-12-08"};

        helper.process(cmdArgs);

        Assertions.assertTrue((outputStream.toString().trim()).contains("SAZuXPGUrfbcn5UA"));
        Assertions.assertTrue((outputStream.toString().trim()).contains("4sMM2LxV07bPJzwf"));
        Assertions.assertTrue((outputStream.toString().trim()).contains("fbcn5UAVanZf6UtG"));
    }

    @Test
    @Order(8)
    public void testValidZeroOccurrenceCookie() {
        LOG.info("TEST 7 -- Valid zero occurrence cookie");
        String[] cmdArgs = new String[]{"-f", "src/test/resources/cookie_log.csv", "-d", "2018-12-01"};

        helper.process(cmdArgs);

        Assertions.assertTrue(outputStream.size() == 0);
    }

    @AfterEach
    public void flushStream() {
        System.setOut(stdOut);
    }

}
