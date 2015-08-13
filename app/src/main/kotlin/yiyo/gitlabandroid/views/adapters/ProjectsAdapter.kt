package yiyo.gitlabandroid.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int = projects.size()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}