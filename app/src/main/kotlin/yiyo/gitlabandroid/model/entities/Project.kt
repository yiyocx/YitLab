package yiyo.gitlabandroid.model.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by sumset on 5/08/15.
 */
data class Project(
    val id: Int,
    val description: String,
    SerializedName("default_branch") val defaultBranch: String,
    val public: Boolean,
    SerializedName("visibility_level") val visibilityLevel: Int,
    SerializedName("ssh_url_to_repo") val sshUrlToRepo: String,
    SerializedName("http_url_to_repo") val httpUrlToRepo: String,
    SerializedName("web_url") val webUrl: String
)