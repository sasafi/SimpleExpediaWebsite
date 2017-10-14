package com.expedia.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expedia.controller.commands.CommandFactory;
import com.expedia.controller.commands.CommandModel;
import com.expedia.controller.commands.CommandResult;
import com.expedia.data.DataTransfer;

/**
 * Servlet implementation of FrontController, this Servlet will be responsible for all requests coming from the cient,
 * and then invoke the corresponding command based on COMMAND_NAME parameter, and then get the command result and forward
 * to the correspodning view.
 * @author sasafi
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(CommandModel.COMMAND_NAME);
		CommandModel command = CommandFactory.getInstance().getCommand(commandName);
		
		CommandResult commandResult = command.execute(new DataTransfer(request));
		
		
		addAttributes(request, commandResult);
		
		request.getRequestDispatcher(commandResult.getPath()).forward(request, response);
	}
	
	
	/**
	 * Fill in all parameters returned in the command result into the http request object to be transfered to the 
	 * client.
	 * @param request
	 * @param commandResult
	 */
	private void addAttributes(HttpServletRequest request, CommandResult commandResult) {
		
		Map<String, Object> attributes = commandResult.getAttributes();
		
		for (String key : attributes.keySet()) {
			request.setAttribute(key, attributes.get(key));
		}
	}


}
