package yiyo.gitlabandroid.model.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by yiyo on 28/08/15.
 */
data class Event(
    val title: String,
    @SerializedName("project_id") val projectId: Int,
    @SerializedName("action_name") val actionName: String,
    @SerializedName("target_id") val targetId: Int,
    @SerializedName("author_id") val authorId: Int,

    @SerializedName("target_title") val targetTitle: String,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("author_username") val authorUsername: String
)