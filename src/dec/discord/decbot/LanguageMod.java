package dec.discord.decbot;

import discord4j.core.object.entity.Message;

public class LanguageMod {
    private String[] badWords = new String[] {
        "fucker", "fuck", "shit", "dick", "cunt", "ho", "bitch"
    };

    public LanguageMod(Message msg) {
        String text = msg.getContent().toLowerCase();
        
        for (String badWord : badWords) {
            if (text.contains(badWord)) {
                modify(msg);
                break;
            }
        }    
    }

    private void modify(Message msg) {
        String author = "Modified msg";
        if (msg.getAuthor().isPresent())
            author = msg.getAuthor().get().getUsername();
            
        String newText = "**" + author + ":** " + bleepOut(msg.getContent());
        
	    msg.getChannel().block()
           .createMessage(newText).block();
            
        msg.delete().block();
    }

    private String bleepOut(String badText) {
        //if (badText.toLowerCase().contains("krove"))
        //    return "Hey you better be polite with Krove, you filthy little piece of shit";
            
        String newText = badText;
        
        for (String badWord : badWords)
            if (newText.contains(badWord))
                newText = newText.replaceAll(badWord, "\\\\*\\\\*\\\\*");
    
        return newText;
    }
}

