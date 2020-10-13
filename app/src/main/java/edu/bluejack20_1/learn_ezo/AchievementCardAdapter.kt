package edu.bluejack20_1.learn_ezo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AchievementCardAdapter(val achievementList : ArrayList<Achievement>) :
    RecyclerView.Adapter<AchievementCardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val achTitle = itemView.findViewById<TextView>(R.id.ach_title)
        val achDesc = itemView.findViewById<TextView>(R.id.ach_desc)
        val achIcon = itemView.findViewById<ImageView>(R.id.ach_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.achievement_card, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return achievementList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val achievement : Achievement = achievementList.get(position)
        val titleTextView = holder.achTitle
        val iconImageView = holder.achIcon
        val descTextView = holder.achDesc

        iconImageView.setImageResource(achievement.achievement_icon)

        titleTextView.setText(achievement.achievement_title)
        descTextView.setText(achievement.achievement_desc)
    }
}