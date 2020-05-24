package info.plexteam.plexbot;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

/**
 * @author Jakub
 * @since 24/05/2020
 */
public class Main {

    @Getter
    @Setter
    private String prefix;

    public static Main getInstance(){
        return new Main();
    }

    private static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        jda = new JDABuilder(AccountType.BOT)
                .setGame(Game.listening("Invite me to your server."))
                .setStatus(OnlineStatus.ONLINE)
                .setToken("")
                .buildBlocking();
    }

}
