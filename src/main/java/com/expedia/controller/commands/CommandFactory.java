package com.expedia.controller.commands;

/**
 * Command Factory 
 * @author sasafi
 *
 */
public class CommandFactory {
	
	private static CommandFactory instance;
	
	
	/**
	 * Private Constructor to prevent initialization
	 */
	private CommandFactory() {
	}
	
	/**
	 * Get instance method
	 * @return commandFactory singlton instance
	 */
	public static synchronized CommandFactory getInstance() {
		
		if (instance == null) {
			instance = new CommandFactory();
		}
		
		return instance;
		
	}

	/**
	 * This method is responsible for creating corresponding commands objects,
	 * represents a simple factory pattern to initialize command objects
	 * @param commandName - String name of the command @see {@link CommandModel}
	 * @return Command Object
	 */
	public CommandModel getCommand(String commandName) {
		
		if (commandName == null || commandName.equals("")) {
			return new SearchCommand();
		}
		
		if (commandName.equals(CommandModel.SEARCH_COMMAND)) {
			return new SearchCommand();
		}
		
		throw new UnsupportedOperationException("Command " + commandName + " is not found");
		
		
	}
	
 
}
