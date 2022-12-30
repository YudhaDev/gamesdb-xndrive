package com.xndrive.gamesdb.views.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.FragmentLandingBinding
import com.xndrive.gamesdb.views.activities.HomeActivity
import com.xndrive.gamesdb.views.activities.SplashScreenActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LandingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fragmentLandingBinding : FragmentLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        (requireActivity() as SplashScreenActivity).supportActionBar!!.setTitle("kkk")
        (requireActivity() as SplashScreenActivity).supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentLandingBinding = FragmentLandingBinding.inflate(layoutInflater)
        return fragmentLandingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentLandingBinding.fragmentLandingLewatiBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }

        val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
            var isHidden = false
            override fun onPageSelected(position: Int) {
                when(position){
                    2-> {
                        val animation_slide_out = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down_exit_for_views)
                        animation_slide_out.fillAfter = true
                        fragmentLandingBinding.fragmentLandingLewatiBtn.startAnimation(animation_slide_out)
                        fragmentLandingBinding.fragmentLandingIndicator.startAnimation(animation_slide_out)
                        isHidden=true
                    }
                    else -> {
                        if (isHidden) {
                            val animation_slide_in = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up_enter_for_views)
                            animation_slide_in.fillAfter = true
                            fragmentLandingBinding.fragmentLandingLewatiBtn.startAnimation(animation_slide_in)
                            fragmentLandingBinding.fragmentLandingIndicator.startAnimation(animation_slide_in)
                            isHidden=false
                        }
                    }
                }
                super.onPageSelected(position)
            }
        }
        fragmentLandingBinding.fragmentLandingViewpager.adapter = MyViewPagerAdapter(requireActivity() as AppCompatActivity)
        fragmentLandingBinding.fragmentLandingIndicator.setViewPager2(fragmentLandingBinding.fragmentLandingViewpager)
        fragmentLandingBinding.fragmentLandingViewpager.registerOnPageChangeCallback(onPageChangeCallback)

        super.onViewCreated(view, savedInstanceState)
    }

    inner class MyViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            if (position<2){
//                Log.d("cnuy", "$position")
                return LandingItemFragment(position)
            } else {
                return LandingLastFragment()
            }
        }


    }


}