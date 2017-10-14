package com.expedia.controller.commands;

import com.expedia.data.DataTransfer;

/**
 * Commands interface
 * @author sasafi
 *
 */
public interface CommandModel {
	
	public static final String COMMAND_NAME = "COMMAND_NAME";
	
	public static final String SEARCH_COMMAND = "SEARCH_COMMAND";
	
	/**
	 * Command main method
	 * @param dataTransfer 
	 */
	public CommandResult execute(DataTransfer dataTransfer);

}
