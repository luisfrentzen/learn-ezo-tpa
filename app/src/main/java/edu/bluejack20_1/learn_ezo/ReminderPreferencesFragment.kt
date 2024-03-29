package edu.bluejack20_1.learn_ezo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReminderPreferencesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReminderPreferencesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reminder_preferences, container, false)
        val finishBtn = root.findViewById<Button>(R.id.finish_button)

        var setup_activity = (activity as SetupActivity)

        val reminderTimePicker = root.findViewById<TimePicker>(R.id.time_picker)

        finishBtn.setOnClickListener {

            var hour = reminderTimePicker.hour
            val minute = reminderTimePicker.minute
            val form = if ( hour > 11 ) {
                hour = hour - 11
                "PM"
            }
            else {
                "AM"
            }

            val reminderTime = String.format("%02d", hour) + ":" + String.format("%02d", minute) + " " + form

            setup_activity.u?.dailyReminder = reminderTime

            setup_activity!!.finishSetup()
        }

        val backBtn = root.findViewById<Button>(R.id.back_button)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReminderPreferencesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReminderPreferencesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}