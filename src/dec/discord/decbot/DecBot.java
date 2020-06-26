package dec.discord.decbot;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class DecBot {
    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder
            .create(R.TOKEN)
            .build()
            .login()
            .block();

        client.getEventDispatcher().on(MessageCreateEvent.class)
            .subscribe(event -> filterVulgar(event.getMessage()));

        client.onDisconnect().block();
    }

    private static void filterVulgar(Message msg) {
        if (msg.getContent().trim().startsWith(R.PREFIX))
            delegate(msg);
        else
            new LanguageMod(msg);
    }

    private static void delegate(Message msg) {
        String text = msg.getContent();
    
        if (text.toLowerCase().trim().startsWith(R.CMD_GOOGLE + " "))
            new GoogleSearch(msg);
        else if (text.toLowerCase().trim().startsWith(R.CMD_INSTAGRAM + " "))
            new InstagramStats(msg);
    }
}
