package yiyo.gitlabandroid.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.model.entities.Project

/**
 * Created by yiyo on 12/08/15.
 */
class ProjectsAdapter(val projects: List<Project>, val context: Context) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.card_project_layout, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(projects.get(position).name)
        if (projects.get(position).description.isEmpty()) {
            holder.description.setText(R.string.projects_no_description)
        } else {
            holder.description.setText(projects.get(position).description)
        }
        val (icon, visibility) = when(projects.get(position).visibilityLevel) {
            0 -> Pair(R.drawable.ic_private, "Private")
            10 -> Pair(R.drawable.ic_internal, "Internal")
            else -> Pair(R.drawable.ic_public, "Public")
        }
        holder.visibility.setText(visibility)
        holder.visibility.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        holder.starsCount.setText(projects.get(position).starCount)
        holder.forksCount.setText(projects.get(position).forksCount)
    }

    override fun getItemCount(): Int = projects.size()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.project_name) as TextView
        val description = itemView.findViewById(R.id.project_description) as TextView
        val visibility = itemView.findViewById(R.id.project_visibility) as TextView
        val starsCount = itemView.findViewById(R.id.project_stars_count) as TextView
        val forksCount = itemView.findViewById(R.id.project_forks_count) as TextView
    }
}