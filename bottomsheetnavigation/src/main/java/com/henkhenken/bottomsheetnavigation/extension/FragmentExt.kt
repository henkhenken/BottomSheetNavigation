package com.henkhenken.bottomsheetnavigation.extension

import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.henkhenken.bottomsheetnavigation.BottomSheetNavHostProvider
import com.henkhenken.bottomsheetnavigation.BottomSheetNavigation

// This extension function checks the parent fragments for a class that implements the BottomSheetNavigation
fun Fragment.findBottomSheetNavController(): BottomSheetNavigation? {
    var findFragment: Fragment? = this
    while (findFragment != null) {
        if (findFragment is BottomSheetNavHostProvider) {
            return findFragment.getNavHost()
        }
        findFragment = findFragment.parentFragment
    }
    return null
}

fun Fragment.dismissBottomSheet(allowStateLoss: Boolean = false) {
    var findFragment: Fragment? = this
    while (findFragment != null) {
        if (findFragment is BottomSheetDialogFragment) {
            if (allowStateLoss) findFragment.dismissAllowingStateLoss()
            else findFragment.dismiss()
            findFragment = null
        } else {
            findFragment = findFragment.parentFragment
        }
    }
}