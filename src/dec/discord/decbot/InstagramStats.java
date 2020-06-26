package dec.discord.decbot;

import java.io.InputStream;
import java.net.URL;

import com.google.gson.Gson;
import discord4j.core.object.entity.Message;

import dec.discord.decbot.objects.MainObj;
import dec.discord.decbot.objects.UserObj;

public class InstagramStats {
    public InstagramStats(Message msg) {
        String output = format(retrieve(msg.getContent().substring(5)));

        msg.getChannel()
            .block()
            .createMessage(output)
            .block();
    }

    private MainObj retrieve(String username) {
        String url = "https://www.instagram.com/" + username + "/?__a=1";
        StringBuilder sb = new StringBuilder();

        try {
            InputStream in = new URL(url).openStream();
            
            int ch = in.read();
            while (ch != -1) {
                sb.append(String.valueOf((char)ch));
                ch = in.read();
            }
        } catch (Exception e) {
            System.out.println("Exception in IGStats:\n" + e);
            return null;
        }

        String json = sb.toString();
        return new Gson().fromJson(json, MainObj.class);
    }

    private String format(MainObj object) {
        if (object == null)
            return "Invalid username";
        
        UserObj user = object.graphql.user;
        StringBuilder sb = new StringBuilder();

        sb.append("**" + user.full_name + "**");
        sb.append("\nFollowers : " + user.edge_followed_by.count);
        sb.append("\nFollows    : " + user.edge_follow.count);
        sb.append("\nPosts        : " + user.edge_owner_to_timeline_media.count);

        sb.append("\nAccount   : ");
        if (user.is_private)
            sb.append("Private");
        else
            sb.append("Public");
        if (user.is_business_account)
            sb.append(", Business");
        if (user.is_verified)
            sb.append(", **Verified**");
        if (user.is_joined_recently)
            sb.append(", Recently joined");

        sb.append("\n\n" + user.biography);

        return sb.toString(); 
    }
}
