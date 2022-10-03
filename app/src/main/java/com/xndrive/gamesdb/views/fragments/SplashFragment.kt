package com.xndrive.gamesdb.views.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.FragmentSplashBinding
import com.xndrive.gamesdb.views.activities.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    lateinit var fragmentSplashBinding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_splash, container, false)
        fragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)
        return fragmentSplashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        determine()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun determine() {
        fragmentSplashBinding.splashScreenLoadingTextview.setText("loading...")
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_landingFragment)
        }, 3000)

//        GlobalScope.launch {
//            delay(3000)
//        }
//        Handler(Looper.getMainLooper()).postDelayed({
//        }, 3000)
    }

}