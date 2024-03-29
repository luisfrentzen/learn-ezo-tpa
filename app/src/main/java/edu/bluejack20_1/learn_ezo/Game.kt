package edu.bluejack20_1.learn_ezo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.database.*
import edu.bluejack20_1.learn_ezo.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Game.newInstance] factory method to
 * create an instance of this fragment.
 */
class Game : Fragment() {
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
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_game, container, false)

        val memoBtn = root.findViewById<Button>(R.id.play_memorize)
        val guessBtn = root.findViewById<Button>(R.id.play_image)

        if((activity as NavBottom).lessons_mastered_count <= 2){
            memoBtn.isEnabled = false
            memoBtn.alpha = 0.7F

            guessBtn.isEnabled = false
            guessBtn.alpha = 0.7F
        }

        val databaseA : DatabaseReference = FirebaseDatabase.getInstance().getReference("accomplishment").child(
            (activity as NavBottom).u?.id.toString()
        )

        databaseA.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val temp = snapshot.child("lessons").value.toString()

                val temp_completed = temp.split(",")

                if(temp_completed.size <= 2){
                    memoBtn.isEnabled = false
                    memoBtn.alpha = 0.7F

                    guessBtn.isEnabled = false
                    guessBtn.alpha = 0.7F
                }else{
                    memoBtn.isEnabled = true
                    memoBtn.alpha = 1F

                    guessBtn.isEnabled = true
                    guessBtn.alpha = 1F
                }
            }

        })

        memoBtn.setOnClickListener {
            (activity as NavBottom).moveToMemorizePage((activity as NavBottom).u as Player)
        }

        guessBtn.setOnClickListener {
            (activity as NavBottom).moveToGuessPage((activity as NavBottom).u as Player)
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
         * @return A new instance of fragment Game.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Game().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}