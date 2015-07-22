package yiyo.gitlabandroid.model.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by yiyo on 22/07/15.
 */
public class Session {

    private Integer id;
    private String username;
    private String email;
    private String name;
    @SerializedName("private_token")
    private String privateToken;
    private boolean blocked;
    @SerializedName("created_at")
    private Date createdAt;
    private String bio;
    private String skype;
    private String linkedin;
    private String twitter;
    @SerializedName("website_url")
    private String websiteUrl;
    @SerializedName("dark_scheme")
    private boolean darkScheme;
    @SerializedName("theme_id")
    private Integer themeId;
    @SerializedName("is_admin")
    private boolean isAdmin;
    @SerializedName("can_create_group")
    private boolean canCreateGroup;
    @SerializedName("can_create_team")
    private boolean canCreateTeam;
    @SerializedName("can_create_project")
    private boolean canCreateProject;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getBio() {
        return bio;
    }

    public String getSkype() {
        return skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public boolean isDarkScheme() {
        return darkScheme;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isCanCreateGroup() {
        return canCreateGroup;
    }

    public boolean isCanCreateTeam() {
        return canCreateTeam;
    }

    public boolean isCanCreateProject() {
        return canCreateProject;
    }
}
