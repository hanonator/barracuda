package org.barracuda.core.game.event;

public class UserCommand {

	/**
	 * The command text
	 */
	private String command;

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command.split(" ")[0];
	}

	/**
	 * The command's arguments
	 * @return
	 */
	public String[] getArguments() {
		String[] all = command.split(" ");
		String[] arguments = new String[all.length - 1];
		System.arraycopy(all, 1, arguments, 0, arguments.length);
		return arguments;
	}

	/**
	 * @param index 
	 * @return the argument at the given index
	 */
	public String getArgument(int index) {
		return getArguments()[index];
	}

	/**
	 * @return Amount of arguments
	 */
	public int getArgumentCount() {
		return getArguments().length;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

}
