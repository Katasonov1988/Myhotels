package com.example.myhotels.ui.hotelsScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.util.Preconditions
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelListBinding
import com.example.myhotels.domain.model.HotelEntity
import com.example.myhotels.ui.hotelDetailScreen.HotelDetailFragment
import com.example.myhotels.ui.hotelsScreen.list.HotelEntityAdapter
import com.example.myhotels.ui.sort_button_sheet_fragment.ButtonSheetSortFragment
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.addHeaderLenient


private const val QUERY_FOR_HOTELS = "0777"
private const val SCROLL_POSITION = 0
const val REQUEST_KEY = "requestKey"
const val KEY_NUMBER = "key-number"

class HotelListFragment : Fragment() {

    private lateinit var viewModel: HotelViewModel
    private var _binding: FragmentHotelListBinding? = null
    private val binding: FragmentHotelListBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelListBinding is null")

    private var chosenSort: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("radioB", "onCreate_mainFragment")
        setUpResultListener()
    }

    private fun setUpResultListener() {
        childFragmentManager.setFragmentResultListener(
            REQUEST_KEY,
            this
        ) { requestKey, bundle ->
            chosenSort = bundle.getString(KEY_NUMBER)
            binding.testView.text = chosenSort.toString().trim()
        }
    }

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
        Log.d("radioB", "onViewCreated_mainFragment")

        childFragmentManager.setFragmentResultListener(
            REQUEST_KEY,
            this
        ) { requestKey, bundle ->
            chosenSort = bundle.getString(KEY_NUMBER)
            binding.testView.text = chosenSort.toString().trim()
        }

        initialToolbar()
        initViewModel()

        val adapter = context?.let { HotelEntityAdapter(it) }
        adapter?.onHotelClickListener = object : HotelEntityAdapter.OnHotelClickListener {
            override fun onHotelClick(hotelInfo: HotelEntity) {
                launchHotelDetailActivity(hotelInfo.id)
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null
        binding.etSearchHotels.setText(QUERY_FOR_HOTELS)

        viewModel.hotels.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
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


    private fun launchHotelDetailActivity(hotelId: Int) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, HotelDetailFragment.newInstance(hotelId))
            .addToBackStack(null)
            .commit()
    }

    private fun initialToolbar() {
        binding.mainToolbar.inflateMenu(R.menu.first_screen_filter_menu)
        binding.mainToolbar.setTitle(R.string.find_you_hotel)
        binding.mainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter_hotels -> {
                    Log.d("radioB", chosenSort.toString())
                    Snackbar.make(binding.root, R.string.pressed_button, Snackbar.LENGTH_SHORT)
                        .show()
                    ButtonSheetSortFragment().show(
                        requireActivity().supportFragmentManager,
                        "sortHotels"
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun inputMethodManager(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun FragmentHotelListBinding.updateHotelListFromInput() {
        binding.etSearchHotels.text.trim().let {
            if (it.isNotEmpty()) {
                if (it.toString() == QUERY_FOR_HOTELS) {
                    recyclerView.scrollToPosition(SCROLL_POSITION)
                    viewModel.getHotelsDataFromNetwork(it.toString())
                } else {
                    viewModel.resetOrder()
                    Snackbar.make(binding.root, R.string.required_order, Snackbar.LENGTH_SHORT)
                        .show()
                }
            } else {
                Snackbar.make(binding.root, R.string.enter_request, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, Injection.provideHotelViewModelFactory()
        )[HotelViewModel::class.java]
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            chosenSort = result.toString()
        }
        super.onStart()
        Log.d("radioB", "onStart_mainFragment")
    }

    override fun onResume() {
        Log.d("radioB", "onResume_mainFragment")
        super.onResume()
    }

    override fun onStop() {
        Log.d("radioB", "onStop_mainFragment")
        super.onStop()
    }

    override fun onPause() {
        Log.d("radioB", "onPause_mainFragment")
        super.onPause()
    }
}