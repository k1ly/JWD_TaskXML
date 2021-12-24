package by.epamtc.lyskovkirill.taskxml.command;

import by.epamtc.lyskovkirill.taskxml.command.impl.GoToUploadPageCommand;
import by.epamtc.lyskovkirill.taskxml.command.impl.ParseXMLCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static volatile CommandProvider instance;
    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.PARSE_XML, new ParseXMLCommand());
        repository.put(CommandName.GO_TO_UPLOAD_PAGE, new GoToUploadPageCommand());
    }

    public static synchronized CommandProvider getInstance() {
        if (instance == null)
            instance = new CommandProvider();
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.GO_TO_UPLOAD_PAGE);
        }
        return command;
    }
}
