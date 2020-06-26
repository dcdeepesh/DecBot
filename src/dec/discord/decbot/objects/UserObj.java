package dec.discord.decbot.objects;

public class UserObj {
    public String biography;
    public FollowersObj edge_followed_by;
    public FollowsObj edge_follow;
    public String full_name;

    public boolean is_business_account;
    public boolean is_private;
    public boolean is_verified;

    public boolean is_joined_recently;

    public PostsObj edge_owner_to_timeline_media;
}