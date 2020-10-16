package edu.bluejack20_1.learn_ezo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProblemPageAdapter(private var problemList: ArrayList<Problem>, var act : Context) : RecyclerView.Adapter<ProblemPageAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val view = itemView

        val rgChoices = itemView.findViewById<RadioGroup>(R.id.rg_choices)
        val nextBtn = itemView.findViewById<Button>(R.id.check_btn)

        var arChoicesBtn = ArrayList<CustomRadio>()

        val problem = itemView.findViewById<TextView>(R.id.tv_problem)

        fun populateArChoices(){
            arChoicesBtn.add(itemView.findViewById<CustomRadio>(R.id.option1))
            arChoicesBtn.add(itemView.findViewById<CustomRadio>(R.id.option2))
            arChoicesBtn.add(itemView.findViewById<CustomRadio>(R.id.option3))
            arChoicesBtn.add(itemView.findViewById<CustomRadio>(R.id.option4))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,  viewType: Int): ProblemPageAdapter.Pager2ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.problem_layout, parent, false)
        val pager : Pager2ViewHolder = Pager2ViewHolder((contactView))
        pager.populateArChoices()
        return pager
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        val problem = problemList.get(position)
        holder.problem.setText(problem.problem)

        holder.rgChoices.clearCheck()
        val options = holder.arChoicesBtn

        for(i in options){
            i.setBackgroundResource(R.drawable.selectable_choices_background)
            i.isChecked = false
        }

        holder.nextBtn.setOnClickListener {
            val selectedBtn = holder.view.findViewById<CustomRadio>(holder.rgChoices.checkedRadioButtonId)

            if( selectedBtn.text == problem.ans){
                selectedBtn.setBackgroundResource(R.drawable.correct_choice)
            }
            else if( selectedBtn.text != problem.ans) {
                selectedBtn.setBackgroundResource(R.drawable.wrong_choice)

                for(btn in holder.arChoicesBtn) {
                    if ( btn.text == problem.ans) {
                        btn.setBackgroundResource(R.drawable.correct_choice)
                        break
                    }
                }
            }

            Log.d("last", position.toString())

            if(position == itemCount - 1){
                holder.nextBtn.text = "Finish"
                holder.nextBtn.setOnClickListener {
                    (act as ProblemActivity).finish()
                }
            }
            else {
                holder.nextBtn.text = "Next"
                holder.nextBtn.setOnClickListener {
                    (act as ProblemActivity).nextPage()
                }
            }
        }

        (holder.rgChoices.getChildAt(1) as AppCompatRadioButton).setText(problem.choices.get(3))
        (holder.rgChoices.getChildAt(2) as AppCompatRadioButton).setText(problem.choices.get(0))
        (holder.rgChoices.getChildAt(3) as AppCompatRadioButton).setText(problem.choices.get(1))
        (holder.rgChoices.getChildAt(0) as AppCompatRadioButton).setText(problem.choices.get(2))

    }

    override fun getItemCount(): Int {
        return problemList.size
    }
}