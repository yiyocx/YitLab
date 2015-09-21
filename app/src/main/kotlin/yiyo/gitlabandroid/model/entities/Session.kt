package yiyo.gitlabandroid.model.entities

import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 * Created by yiyo on 22/07/15.
 */
data class Session(
    val id: Int,
    val username: String,
    val email: String,
    val name: String,
    @SerializedName("private_token") val privateToken: String,
    val blocked: Boolean,
    @SerializedName("created_at") val createdAt: Date,
    val bio: String,
    val skype: String,
    val linkedin: String,
    val twitter: String,
    @SerializedName("website_url") val websiteUrl: String,
    @SerializedName("dark_scheme") val darkScheme: Boolean,
    @SerializedName("theme_id") val themeId: Int,
    @SerializedName("is_admin") val isAdmin: Boolean,
    @SerializedName("can_create_group") val canCreateGroup: Boolean,
    @SerializedName("can_create_team") val canCreateTeam: Boolean,
    @SerializedName("can_create_project") val canCreateProject: Boolean
)
