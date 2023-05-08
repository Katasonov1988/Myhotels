package com.example.myhotels.ui.sortButtonSheetFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import com.example.myhotels.databinding.FragmentButtonSheetSortBinding
import com.example.myhotels.ui.hotelsScreen.KEY_SORT
import com.example.myhotels.ui.hotelsScreen.REQUEST_KEY_SORT
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val DEFAULT_SORT = "default_sort"
const val DISTANCE_SORT = "distance_sort"
const val NUMBER_FREE_ROOMS_SORT = "number_free_rooms"

class ButtonSheetSortFragment : BottomSheetDialogFragment() {

    companion object {
        const val HOTELS_SORT_BY = "extra_hotels_sort_by"
        const val KEY_SAVED_TYPE_SORT = "key_saved_type_sort"

        @JvmStatic
        fun newInstance(typeSort: String) =
            ButtonSheetSortFragment().apply {
                arguments = Bundle().apply {
                    putString(HOTELS_SORT_BY, typeSort)
                }
            }
    }

    private var typeSort: String? = DEFAULT_SORT

    private var _binding: FragmentButtonSheetSortBinding? = null
    private val binding: FragmentButtonSheetSortBinding
        get() = _binding ?: throw RuntimeException("FragmentButtonSheetSortBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("radioB", "ButtonFragment: onCreate")
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            typeSort = savedInstanceState.getString(KEY_SAVED_TYPE_SORT)
        } else {
            arguments?.let {
                typeSort = it.getString(HOTELS_SORT_BY)

            }
        }
        Log.d("radioB", "typeSort: $typeSort")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("radioB", "ButtonFragment: onCreateView")
        _binding = FragmentButtonSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("radioB", "ButtonFragment: onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        setCheckedButton(typeSort)

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

    private fun setCheckedButton(typeSort: String?){
        when(typeSort){
            DEFAULT_SORT -> binding.rbDefaultSort.setChecked(true)
            DISTANCE_SORT -> binding.rbDistanceSort.setChecked(true)
            NUMBER_FREE_ROOMS_SORT -> binding.rbNumberFreeRooms.setChecked(true)
        }
    }

    private fun setResult(result: String) {
        requireActivity().supportFragmentManager.setFragmentResult(
            REQUEST_KEY_SORT,
            bundleOf(KEY_SORT to result)
        )
    }

    override fun onPause() {
        Log.d("radioB", "ButtonFragment: onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("radioB", "ButtonFragment: onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("radioB", "ButtonFragment: onSaveInstanceSave")
        super.onSaveInstanceState(outState)
        typeSort?.let { outState.putString(KEY_SAVED_TYPE_SORT, it) }
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d("radioB", "ButtonFragment: onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        Log.d("radioB", "ButtonFragment: onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}
