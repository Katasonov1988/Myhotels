package com.example.myhotels.ui.sort_button_sheet_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.example.myhotels.databinding.FragmentButtonSheetSortBinding
import com.example.myhotels.databinding.FragmentHotelListBinding
import com.example.myhotels.ui.hotelsScreen.KEY_SORT
import com.example.myhotels.ui.hotelsScreen.KEY_TYPE_SORT
import com.example.myhotels.ui.hotelsScreen.REQUEST_KEY_SORT
import com.example.myhotels.ui.hotelsScreen.REQUEST_KEY_TYPE_SORT
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val DEFAULT_SORT = "default_sort"
const val DISTANCE_SORT = "distance_sort"
const val NUMBER_FREE_ROOMS_SORT = "number_free_rooms"

class ButtonSheetSortFragment : BottomSheetDialogFragment() {

//    private var state: String = DEFAULT_SORT

    private var _binding: FragmentButtonSheetSortBinding? = null
    private val binding: FragmentButtonSheetSortBinding
        get() = _binding ?: throw RuntimeException("FragmentButtonSheetSortBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setFragmentResultListener(
//            REQUEST_KEY_TYPE_SORT
//        ) { requestKey, bundle ->
//            val saveState = bundle.getString(KEY_TYPE_SORT)
//            Log.d("radioB", "резалтЛистенер $saveState")
//            state = saveState.toString()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setChecked(state)

        binding.radioGroupSortOptions.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    binding.rbDefaultSort.id -> {
                        setResult(DEFAULT_SORT)
                    }
                    binding.rbDistanceSort.id -> {
                        setResult(DISTANCE_SORT)
                    }
                    binding.rbNumberFreeRooms.id -> {
                        setResult(NUMBER_FREE_ROOMS_SORT)
                    }
                }
                dismiss()
            })
    }

    private fun setResult(result: String) {
        requireActivity().supportFragmentManager.setFragmentResult(
            REQUEST_KEY_SORT,
            bundleOf(KEY_SORT to result)
        )
    }

//    private fun setChecked(saveState: String?) {
//        Log.d("radioB", "установка состояния $saveState")
//        when (saveState) {
//            DEFAULT_SORT -> binding.rbDefaultSort.setChecked(true)
//            DISTANCE_SORT -> binding.rbDistanceSort.setChecked(true)
//            NUMBER_FREE_ROOMS_SORT -> binding.rbNumberFreeRooms.setChecked(true)
//            null -> binding.rbDefaultSort.setChecked(true)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
