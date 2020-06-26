package dec.discord.decbot;

public class R {
    /**
     * Loads token from
     * {@code &lt;userHome&gt;/.dec/decbot/token}.
     * Make sure the file is present and contains a valid token.
     * 
     * @return The token
     */
    public static final String TOKEN = PreferenceManager.loadToken();

    public static final String PREFIX = "..";
    public static final String CMD_GOOGLE = PREFIX + "google";
    public static final String CMD_INSTAGRAM = PREFIX + "ig";
}