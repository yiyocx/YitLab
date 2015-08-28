package yiyo.gitlabandroid.model.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by sumset on 5/08/15.
 */
data class Project(
    val id: Int,
    val description: String,
    SerializedName("default_branch") val defaultBranch: String,
    val public: Boolean,
    val archived: Boolean,
    SerializedName("visibility_level") val visibilityLevel: Int,
    SerializedName("ssh_url_to_repo") val sshUrlToRepo: String,
    SerializedName("http_url_to_repo") val httpUrlToRepo: String,
    SerializedName("web_url") val webUrl: String,

    val name: String,
    SerializedName("name_with_namespace") val nameWithNamespace: String,
    val path: String,
    SerializedName("path_with_namespace") val pathWithNamespace: String,
    SerializedName("issues_enabled") val issuesEnable: Boolean,
    SerializedName("merge_requests_enabled") val mergeRequestsEnabled: Boolean,
    SerializedName("wiki_enabled") val wikiEnabled: Boolean,
    SerializedName("snippets_enabled") val snippetsEnabled: Boolean,
    SerializedName("created_at") val createdAt: Date,
    SerializedName("last_activity_at") val lastActivityAt: Date,
    SerializedName("creator_id") val creatorId: Int,

    SerializedName("avatar_url") val avatarUrl: String,
    SerializedName("star_count") val starCount: String,
    SerializedName("forks_count") val forksCount: String
)