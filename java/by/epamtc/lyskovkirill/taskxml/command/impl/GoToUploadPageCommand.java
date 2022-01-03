package by.epamtc.lyskovkirill.taskxml.command.impl;

import by.epamtc.lyskovkirill.taskxml.command.Command;
import by.epamtc.lyskovkirill.taskxml.command.CommandResult;
import by.epamtc.lyskovkirill.taskxml.controller.page.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUploadPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(Page.UPLOAD_PAGE.getValue());
    }
}
