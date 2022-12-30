package com.xndrive.gamesdb.views.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.FragmentPickStarBinding
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.views.activities.RateGameActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PickStarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickStarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var game_object: GameModel? = null
    private lateinit var fragmentActivity : RateGameActivity

    private lateinit var fragmentPickStarBinding: FragmentPickStarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        fragmentActivity = activity as RateGameActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPickStarBinding = FragmentPickStarBinding.inflate(layoutInflater)
        return fragmentPickStarBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        determine()

//        val game_object_args : PickStarFragmentArgs by navArgs()
//        Toast.makeText(context, "${game_object_args.gameObject.title}", Toast.LENGTH_SHORT).show()

    }

    private fun determine() {
        fragmentPickStarBinding.fragmentPickStarNextBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_pickStarFragment_to_writeReviewFragment)
            val rating = fragmentPickStarBinding.fragmentPickStarRatingbar.rating.toString()
            findNavController().navigate(PickStarFragmentDirections.actionPickStarFragmentToWriteReviewFragment(
                        fragmentActivity.game_object,
                        rating,
                )
            )
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PickStarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickStarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}