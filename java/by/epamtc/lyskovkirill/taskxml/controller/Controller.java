package by.epamtc.lyskovkirill.taskxml.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import by.epamtc.lyskovkirill.taskxml.command.Command;
import by.epamtc.lyskovkirill.taskxml.command.CommandProvider;
import by.epamtc.lyskovkirill.taskxml.command.CommandResult;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// TODO: 23.12.2021
//  <T> void tr(T g){
//        g.getClass();
//    }
// TODO: 23.12.2021 final class role

@WebServlet(name = "Controller", value = "/controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String HTML_CONTENT_TYPE = "text/html";

    public void init() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);

        CommandProvider provider = CommandProvider.getInstance();
        Command executionCommand = provider.getCommand(commandName);
        CommandResult commandResult;

        commandResult = executionCommand.execute(request, response);
        String page = commandResult.getPage();
        response.setContentType(HTML_CONTENT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        if (commandResult.isRedirect())
            response.sendRedirect(page);
        else {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
    }
}