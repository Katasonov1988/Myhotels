package com.example.myhotels.ui.hotelsScreen

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelListBinding
import com.example.myhotels.domain.model.HotelEntity
import com.example.myhotels.ui.hotelsScreen.list.HotelEntityAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HotelListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelListFragment : Fragment() {

    private lateinit var viewModel: HotelViewModel
    private var _binding: FragmentHotelListBinding? = null
    private val binding: FragmentHotelListBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelListBinding is null")


//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        val adapter = context?.let { HotelEntityAdapter(it) }
        adapter?.onHotelClickListener = object : HotelEntityAdapter.OnHotelClickListener {
            override fun onHotelClick(hotelInfo: HotelEntity) {
                TODO()
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.hotels.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

        binding.etSearchHotels.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                binding.updateHotelListFromInput()
                true
            } else {
                false
            }
        }

        binding.etSearchHotels.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                binding.updateHotelListFromInput()
                inputMethodManager(view)
                true
            } else {
                false
            }
        }

    }

    private fun inputMethodManager(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun FragmentHotelListBinding.updateHotelListFromInput(){
        binding.etSearchHotels.text.trim().let {
            if (it.isNotEmpty()) {
                recyclerView.scrollToPosition(0)
                viewModel.getHotelsDataFromNetwork(it.toString())
                Toast.makeText(requireContext(),"$it",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, Injection.provideHotelViewModelFactory()
        ).get(HotelViewModel::class.java)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HotelListFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HotelListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}