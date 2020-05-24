package info.plexteam.plexbot.listeners;

import info.plexteam.plexbot.Main;
import info.plexteam.plexbot.command.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * From Discord BOT Base using JDA (https://github.com/jakuubkoo/JDABotBase/blob/master/src/main/java/me/jakub/botbase/events/MessageReceivedListener.java)
 *
 * @author Jakub
 * @since 24/05/2020
 */
public class MessageReceivedListener extends ListenerAdapter {

    private Map<String, ICommand> loadedCommands = new HashMap<>();

    public MessageReceivedListener(){

        try {
            Reflections reflections = new Reflections("info.plexteam.plexbot.command.commands");
            Set<Class<? extends ICommand>> classes = reflections.getSubTypesOf(ICommand.class);
            loadedCommands = new HashMap<>(classes.size());

            for (Class<? extends ICommand> clazz : classes){
                loadedCommands.put(clazz.getSimpleName(), clazz.getDeclaredConstructor().newInstance());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().isEmpty() && event.getMessage().getContentRaw().startsWith(Main.getInstance().getPrefix())){
            String cmd = event.getMessage().getContentRaw().split(" ")[0].substring(1);
            String[] args = event.getMessage().getContentRaw().substring(0, cmd.length()).split(" ");

            Optional<ICommand> command = loadedCommands.entrySet()
                    .stream()
                    .filter(stringICommandEntry -> cmd.equalsIgnoreCase(stringICommandEntry.getKey()))
                    .map(Map.Entry::getValue)
                    .findAny();

            if (command.isPresent()){
                command.get().onCommand(event.getMessage(), args);
            }else{
                event.getChannel().sendMessage("Unknown command!").queue();
            }
        }
        super.onGuildMessageReceived(event);
    }
}
