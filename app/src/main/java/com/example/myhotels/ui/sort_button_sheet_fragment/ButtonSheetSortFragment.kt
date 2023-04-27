package com.example.myhotels.ui.sort_button_sheet_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.myhotels.databinding.FragmentButtonSheetSortBinding
import com.example.myhotels.ui.hotelsScreen.KEY_NUMBER
import com.example.myhotels.ui.hotelsScreen.REQUEST_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val DEFAULT_SORT = "default_sort"
const val DISTANCE_SORT = "distance_sort"
const val NUMBER_FREE_ROOMS_SORT = "number_free_rooms"

class ButtonSheetSortFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentButtonSheetSortBinding
    private var chosenSorting: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtonSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroupSortOptions.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    binding.rbDefaultSort.id -> {
                        chosenSorting = DEFAULT_SORT
                        setResult(chosenSorting.toString())
                        Log.d("radioB", chosenSorting.toString())
                    }
                    binding.rbDistanceSort.id -> {
                        chosenSorting = DISTANCE_SORT
                        setResult(chosenSorting.toString())
                        Log.d("radioB", chosenSorting.toString())
                    }
                    binding.rbNumberFreeRooms.id -> {
                        chosenSorting = NUMBER_FREE_ROOMS_SORT
                        setResult(chosenSorting.toString())
                        Log.d("radioB", chosenSorting.toString())
                    }
                }
                dismiss()
            })
    }

    private fun setResult(result: String) {
        setFragmentResult(
            REQUEST_KEY,
            bundleOf(KEY_NUMBER to result)
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d("radioB", "onPause")
    }

    override fun onStop() {
        Log.d("radioB", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("radioB", "onDestroy")
        super.onDestroy()
    }

    override fun onDestroyView() {

        Log.d("radioB", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d("radioB", "onDetach")
        super.onDetach()
    }

}
