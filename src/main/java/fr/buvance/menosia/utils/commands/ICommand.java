package fr.buvance.menosia.utils.commands;

import java.io.IOException;

public abstract class ICommand {

	public abstract void onCommand(CommandArgs args) throws IOException;
}

