package com.henkhenken.bottomsheetnavigation

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

class BottomSheetNavHost(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs), BottomSheetNavigation {

    private val container: FrameLayout
    private val navHost = NavHostFragment()
    private var navController: NavController? = null

    var animationDuration: Long = 350L
    var animationInterpolator: Interpolator? = null
    var animationStartDelay: Long = 0L

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_bottom_sheet_nav_host, null)
        this.addView(view)
        container = findViewById(R.id.nav_host_container)
    }

    fun setupNavHost(navGraph: Int, fragmentManager: FragmentManager): BottomSheetNavHost {
        fragmentManager.beginTransaction().replace(R.id.nav_host_container, navHost).commitNow()
        navController = navHost.navController.also {
            it.setGraph(navGraph)
        }
        return this
    }

    override fun navigate(resId: Int, bundle: Bundle?, navOptions: NavOptions?) {
        startAnimation()
        navController?.navigate(resId, bundle, navOptions)
    }

    override fun navigateUp(): Boolean {
        val x = navHost.childFragmentManager.backStackEntryCount
        if (x > 0) {
            startAnimation()
            return navController?.navigateUp() ?: false
        }
        return false
    }

    private fun startAnimation() {
        TransitionManager.endTransitions(container.rootView as ViewGroup)
        val transition = AutoTransition().apply {
            duration = animationDuration
            interpolator = animationInterpolator
            startDelay = animationStartDelay
        }
        TransitionManager.beginDelayedTransition(container.rootView as ViewGroup, transition)
    }
}

// The 'container' fragment must implement this interface so that other fragments can get the navigation object
interface BottomSheetNavHostProvider {
    fun getNavHost(): BottomSheetNavigation
}

// Other fragments will receive an instance of this interface on which they can navigate
interface BottomSheetNavigation {
    fun navigate(resId: Int, bundle: Bundle? = null, navOptions: NavOptions? = null)
    fun navigateUp(): Boolean
}