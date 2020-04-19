package main;
import org.tinylog.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.error("I'm a TEST error, IGNORE me!");
        Logger.warn("I'm a TEST warn, IGNORE me!");
        Logger.info("I'm a TEST info, IGNORE me!");
        Logger.debug("I'm a TEST debug, IGNORE me!");
        Logger.trace("I'm a TEST trace, IGNORE me!");
    }
}
