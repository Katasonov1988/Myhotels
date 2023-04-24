package com.example.myhotels.ui.sort_button_sheet_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myhotels.databinding.FragmentButtonSheetSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ButtonSheetSortFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentButtonSheetSortBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtonSheetSortBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}