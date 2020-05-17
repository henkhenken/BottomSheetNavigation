package com.henkhenken.bottomsheetnavigationexample.bottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.henkhenken.bottomsheetnavigation.extension.dismissBottomSheet
import com.henkhenken.bottomsheetnavigation.extension.findBottomSheetNavController
import com.henkhenken.bottomsheetnavigationexample.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondBottomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            findBottomSheetNavController()?.navigateUp()
        }

        view.findViewById<ImageButton>(R.id.close).setOnClickListener {
            dismissBottomSheet()
        }
    }
}
