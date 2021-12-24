package by.epamtc.lyskovkirill.taskxml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
//        LoggerContext context = (LoggerContext) LogManager.getContext(false);
//        var cl = Thread.currentThread().getContextClassLoader();
//        File file = new File("src/main/resources/log4j2.xml");
//        context.setConfigLocation(file.toURI());

        //System.out.println(System.getProperties());

        final Logger logger = LogManager.getLogger();
        logger.info("Hello world");
//        try {
//            throw new RuntimeException("Test error");
//        } catch (RuntimeException e) {
//            logger.error(e);
//        }
    }
}

//    public static void main1( String[] args ) throws FileNotFoundException, IOException {
//
//        // Get instance of configuration factory; your options are default ConfigurationFactory, XMLConfigurationFactory,
//        // 	YamlConfigurationFactory & JsonConfigurationFactory
//        ConfigurationFactory factory =  XmlConfigurationFactory.getInstance();
//
//        // Locate the source of this configuration, this located file is dummy file contains just an empty configuration Tag
//        ConfigurationSource configurationSource = new ConfigurationSource(new FileInputStream(new File("C:/dummyConfiguration.xml")));
//
//        // Get a reference from configuration
//        Configuration configuration = factory.getConfiguration(configurationSource);
//
//        // Create default console appender
//        ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
//
//        // Add console appender into configuration
//        configuration.addAppender(appender);
//
//        // Create loggerConfig
//        LoggerConfig loggerConfig = new LoggerConfig("com",Level.FATAL,false);
//
//        // Add appender
//        loggerConfig.addAppender(appender,null,null);
//
//        // Add logger and associate it with loggerConfig instance
//        configuration.addLogger("com", loggerConfig);
//
//        // Get context instance
//        LoggerContext context = new LoggerContext("JournalDevLoggerContext");
//
//        // Start logging system
//        context.start(configuration);
//
//        // Get a reference for logger
//        Logger logger = context.getLogger("com");
//
//        // LogEvent of DEBUG message
//        logger.log(Level.FATAL, "Logger Name :: "+logger.getName()+" :: Passed Message ::");
//
//        // LogEvent of Error message for Logger configured as FATAL
//        logger.log(Level.ERROR, "Logger Name :: "+logger.getName()+" :: Not Passed Message ::");
//
//        // LogEvent of ERROR message that would be handled by Root
//        logger.getParent().log(Level.ERROR, "Root Logger :: Passed Message As Root Is Configured For ERROR Level messages");
//    }
//}