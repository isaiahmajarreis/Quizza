package com.mobdeve.majarreisroncal.quizza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/* Modal Fragment that pulls and displays user info pulled from
   the firebase realtime database such as total games played,
   high score and average score */

class StatsFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }
}