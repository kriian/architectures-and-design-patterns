package ru.hehnev.logger;

public class LoggerFactory {

    private LoggerFactory() {
    }

    public static Logger createLogger() {
        return new ConsoleLogger();
    }
}
