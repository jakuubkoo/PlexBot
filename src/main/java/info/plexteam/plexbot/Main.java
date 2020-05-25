package info.plexteam.plexbot;

import com.google.gson.Gson;
import info.plexteam.plexbot.utils.Config;
import lombok.Getter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jakub
 * @since 24/05/2020
 */
public class Main {

    public Config config = new Config();

    private static String token;

    public static Main getInstance(){
        return new Main();
    }

    private static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {

        getInstance().loadCfg();

        jda = new JDABuilder(AccountType.BOT)
                .setGame(Game.listening("Invite me to your server."))
                .setStatus(OnlineStatus.ONLINE)
                .setToken(token)
                .buildBlocking();
    }

    private void loadCfg(){
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader("config.json"))){
            this.config = gson.fromJson(reader, Config.class);
            token = config.token;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
