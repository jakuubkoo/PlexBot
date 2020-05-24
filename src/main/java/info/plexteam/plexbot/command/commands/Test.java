package info.plexteam.plexbot.command.commands;

import info.plexteam.plexbot.command.ICommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author Jakub
 * @since 24/05/2020
 */
public class Test implements ICommand {

    @Override
    public void onCommand(Message message, String[] args) {
        message.getChannel().sendMessage("ok").queue();
    }

}
