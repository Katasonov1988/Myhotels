package com.example.myhotels.ui.hotelsScreen

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelsBinding
import com.example.myhotels.domain.model.HotelEntity
import com.example.myhotels.ui.hotelDetailScreen.HotelDetailFragment
import com.example.myhotels.ui.hotelsScreen.list.HotelEntityAdapter
import com.example.myhotels.ui.sortButtonSheetFragment.BottomSheetSortFragment
import com.example.myhotels.ui.sortButtonSheetFragment.DEFAULT_SORT
import com.google.android.material.snackbar.Snackbar


private const val QUERY_FOR_HOTELS = "0777"
private const val SCROLL_POSITION = 0
private const val FLAG = 0
const val REQUEST_KEY_SORT = "request key-sort"
const val KEY_SORT = "key-sort"
private const val SORT_HOTELS = "sortHotels"
private const val NOTHING_FOUND = "Ничего не найдено"
private const val EMPTY_REQUEST = "Пустой запрос"

class HotelListFragment : Fragment() {

    private lateinit var viewModel: HotelViewModel
    private var _binding: FragmentHotelsBinding? = null
    private val binding: FragmentHotelsBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelListBinding is null")

    private var selectedSort = DEFAULT_SORT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener()
        initialToolbar()
        setOnMenuItemClickListenerForToolbar()
        initViewModel()
        setRecViewItemDecoration()

        val adapter = context?.let { HotelEntityAdapter() }
        adapter?.onHotelClickListener = object : HotelEntityAdapter.OnHotelClickListener {
            override fun onHotelClick(hotelInfo: HotelEntity) {
                launchHotelDetailFragment(hotelInfo.id)
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.hotels.observe(viewLifecycleOwner) {
            binding.progress.isVisible = false
            adapter?.submitList(it)
            changeStateOfSortIcon(it)
        }

        binding.etSearchHotels.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                checkRequestAndGetResponseFromNetwork()
                hideSoftKey(view)
                true
            } else {
                false
            }
        }

    }

    private fun setRecViewItemDecoration() {
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
    }

    private fun changeStateOfSortIcon(hotels: List<HotelEntity>?) {
        if (hotels != null) {
            binding.recyclerView.scrollToPosition(SCROLL_POSITION)
            binding.tvNothingFound.visibility = View.GONE
            binding.mainToolbar.menu.findItem(R.id.action_filter_hotels)
                .isVisible = !hotels.isEmpty()

        } else {
            binding.tvNothingFound.visibility = View.VISIBLE
            binding.mainToolbar.menu.findItem(R.id.action_filter_hotels)
                .isVisible = false
        }
    }

    private fun setFragmentResultListener() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            REQUEST_KEY_SORT,
            viewLifecycleOwner
        ) { _, bundle ->
            selectedSort = bundle.getString(KEY_SORT).toString()
            viewModel.sortHotels(selectedSort)
        }
    }

    private fun launchHotelDetailFragment(hotelId: Int) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, HotelDetailFragment.newInstance(hotelId))
            .addToBackStack(null)
            .commit()
    }

    private fun initialToolbar() {
        with(binding) {
            mainToolbar.inflateMenu(R.menu.first_screen_filter_menu)
            mainToolbar.setTitle(R.string.find_you_hotel)
        }
    }

    private fun setOnMenuItemClickListenerForToolbar() {
        binding.mainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter_hotels -> {
                    BottomSheetSortFragment.newInstance(selectedSort).show(
                        requireActivity().supportFragmentManager,
                        SORT_HOTELS
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun hideSoftKey(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, FLAG)
    }

    private fun checkRequestAndGetResponseFromNetwork() {
        binding.etSearchHotels.text.toString().trim().let {
            if (it.isNotEmpty()) {
                binding.progress.isVisible = true
                binding.tvNothingFound.isVisible = false
                if (it == QUERY_FOR_HOTELS) {
                    getHotelsFromNetwork(it)
                } else {
                    clearAdapter()
                    showTipNotFound()
                }
            } else {
                showTipEmptyRequest()
            }
        }
    }

    private fun getHotelsFromNetwork(request: String) {
        viewModel.getHotelsDataFromNetwork(request, selectedSort)
    }

    private fun clearAdapter() {
        viewModel.resetOrder()

    }

    private fun showTipNotFound() {
        binding.tvNothingFound.text = NOTHING_FOUND
        Snackbar.make(binding.root, R.string.required_order, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun showTipEmptyRequest() {
        binding.tvNothingFound.text = EMPTY_REQUEST
        Snackbar.make(binding.root, R.string.enter_request, Snackbar.LENGTH_SHORT).show()
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
}