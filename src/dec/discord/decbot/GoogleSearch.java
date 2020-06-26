package dec.discord.decbot;

import java.util.ArrayList;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import discord4j.core.object.entity.Message;

public class GoogleSearch {
    private final String DESC_CLASS = "BNeawe vvjwJb AP7Wnd";
    private final String BASE_URL =
        "http://www.google.com/search?q=";
    private final String USER_AGENT =
        "Googlebot/2.1 (+http://www.googlebot.com/bot.html)";

    public GoogleSearch(Message msg) {
        String output = format(search(msg.getContent().substring(9)));

        msg.getChannel()
            .block()
            .createMessage(output)
            .block();
    }

    private ArrayList<String> search(String searchStr) {
        ArrayList<String> results = new ArrayList<>();
        Elements elems = null;

        try {
            String url = BASE_URL + URLEncoder.encode(searchStr, "UTF-8");

            elems = Jsoup
                .connect(url)
                .userAgent(USER_AGENT)
                .get()
                .getElementsByClass(DESC_CLASS);
        } catch(Exception e) {
            System.out.println("Error in search:\n" + e);
        }   
 
        for (Element elem : elems)
            results.add(elem.text());

        return results;
    }

    private String format(ArrayList<String> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("**Top Google search results:**\n");

        boolean firstLine = true;
        for (String str : results) {
            if (!firstLine)
                sb.append("\n");
            else
                firstLine = false;

            sb.append(str);
        }

        return sb.toString();
    }
}
