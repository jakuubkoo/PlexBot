package info.plexteam.plexbot.command;

import net.dv8tion.jda.core.entities.Message;

/**
 * From Discord BOT Base using JDA (https://github.com/jakuubkoo/JDABotBase/blob/master/src/main/java/me/jakub/botbase/command/ICommand.java)
 *
 * @author Jakub
 * @since 24/05/2020
 */
public interface ICommand {

    void onCommand(Message message, String[] args);

}
