package com.example.myhotels.ui.sortButtonSheetFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.myhotels.databinding.FragmentBottomSheetSortBinding
import com.example.myhotels.ui.hotelsScreen.KEY_SORT
import com.example.myhotels.ui.hotelsScreen.REQUEST_KEY_SORT
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val DEFAULT_SORT = "default_sort"
const val DISTANCE_SORT = "distance_sort"
const val NUMBER_FREE_ROOMS_SORT = "number_free_rooms"

class BottomSheetSortFragment : BottomSheetDialogFragment() {
    companion object {
        const val HOTELS_SORT_BY = "extra_hotels_sort_by"
        const val KEY_SAVED_TYPE_SORT = "key_saved_type_sort"

        @JvmStatic
        fun newInstance(typeSort: String) =
            BottomSheetSortFragment().apply {
                arguments = Bundle().apply {
                    putString(HOTELS_SORT_BY, typeSort)
                }
            }
    }

    private var typeSort: String? = DEFAULT_SORT

    private var _binding: FragmentBottomSheetSortBinding? = null
    private val binding: FragmentBottomSheetSortBinding
        get() = _binding ?: throw RuntimeException("FragmentBottomSheetSortBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            typeSort = savedInstanceState.getString(KEY_SAVED_TYPE_SORT)
        } else {
            arguments?.let {
                typeSort = it.getString(HOTELS_SORT_BY)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCheckedBottom(typeSort)

        binding.radioGroupSortOptions.setOnCheckedChangeListener { _, i ->
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
        }

    }

    private fun setCheckedBottom(typeSort: String?) {
        when (typeSort) {
            DEFAULT_SORT -> binding.rbDefaultSort.isChecked = true
            DISTANCE_SORT -> binding.rbDistanceSort.isChecked = true
            NUMBER_FREE_ROOMS_SORT -> binding.rbNumberFreeRooms.isChecked = true
        }
    }

    private fun setResult(result: String) {
        requireActivity().supportFragmentManager.setFragmentResult(
            REQUEST_KEY_SORT,
            bundleOf(KEY_SORT to result)
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        typeSort?.let { outState.putString(KEY_SAVED_TYPE_SORT, it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
