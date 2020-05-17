package com.henkhenken.bottomsheetnavigationexample

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.henkhenken.bottomsheetnavigation.BottomSheetNavHost
import com.henkhenken.bottomsheetnavigation.BottomSheetNavHostProvider

class NavigationBottomSheetFragment : BottomSheetDialogFragment(), BottomSheetNavHostProvider {

    private lateinit var bottomSheetNavHost: BottomSheetNavHost

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return bottomSheetController(dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetNavHost = view.findViewById(R.id.bottom_sheet_nav_host)
        bottomSheetNavHost.setupNavHost(R.navigation.bottom_sheet_nav_graph, childFragmentManager).apply {
            animationDuration = 300
            animationInterpolator = FastOutSlowInInterpolator()
        }
    }

    private fun bottomSheetController(dialog: BottomSheetDialog): Dialog {
        dialog.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                bottomSheetNavHost.navigateUp()
            } else {
                false
            }
        }
        return dialog
    }

    override fun getNavHost(): BottomSheetNavHost {
        return bottomSheetNavHost
    }
}