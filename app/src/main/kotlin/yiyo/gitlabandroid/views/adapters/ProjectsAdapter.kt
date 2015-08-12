package yiyo.gitlabandroid.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import yiyo.gitlabandroid.model.entities.Project

/**
 * Created by yiyo on 12/08/15.
 */
class ProjectsAdapter(val projects: List<Project>, val mContext: Context) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int = projects.size()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}