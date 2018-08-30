package sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LogTest {
    private static final Logger log = LogManager.getLogger(sample.Main.class);

     public static void main(String[] args) {
        log.info("Test Message");
        log.error("Error Test");
        log.debug("Debug Test");
    }
}