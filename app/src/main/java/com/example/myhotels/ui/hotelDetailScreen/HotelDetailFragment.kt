package com.example.myhotels.ui.hotelDetailScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelDetailBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class HotelDetailFragment : Fragment() {

    companion object {
        const val HOTEL_ITEM_ID = "extra_hotel_item_id"

        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOTEL_ITEM_ID, hotelId)
                }
            }
    }

    private lateinit var viewModel: HotelDetailViewModel

    private var _binding: FragmentHotelDetailBinding? = null
    private val binding: FragmentHotelDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelDetailBinding is null")

    private var hotelId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(HOTEL_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        hotelId?.let {
            viewModel.getHotelDetailItem(it)
        }
        initialToolbar()

        viewModel.hotelItem.observe(viewLifecycleOwner) {
            with(binding) {
                hotelName.text = it.name
                hotelAddress.text = it.address
                distanceFromCenter.text =
                    resources.getString(R.string.distance_from_center, it.distance)
                hotelSuitesAvailability.text =
                    resources.getString(R.string.count_of_free_rooms, it.suitesAvailability)
                setNumberOfStars(it.stars)

                Picasso.get()
                    .load(it.image)
                    .error(R.drawable.ic_image_not_supported_24)
                    .resize(740, 410)
                    .centerCrop()
                    .into(ivHotelCover)
            }
        }
    }

    private fun initialToolbar() {
        binding.secondScreenToolbar.inflateMenu(R.menu.first_screen_filter_menu)
        binding.secondScreenToolbar.setNavigationIcon(R.drawable.ic_back_24)
        binding.secondScreenToolbar.setTitle(R.string.about_hotel)
        binding.secondScreenToolbar.setNavigationOnClickListener {
            Snackbar.make(binding.root, "Нажата кнопка", Snackbar.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, Injection.provideHotelDetailViewModelFactory()
        ).get(HotelDetailViewModel::class.java)
    }

    private fun setNumberOfStars(numberOfStars: Int) {
        when (numberOfStars) {
            1 -> binding.firstStar.visibility = View.VISIBLE
            2 -> {
                binding.firstStar.visibility = View.VISIBLE
                binding.secondStar.visibility = View.VISIBLE
            }
            3 -> {
                binding.firstStar.visibility = View.VISIBLE
                binding.secondStar.visibility = View.VISIBLE
                binding.thirdStar.visibility = View.VISIBLE
            }
            4 -> {
                binding.firstStar.visibility = View.VISIBLE
                binding.secondStar.visibility = View.VISIBLE
                binding.thirdStar.visibility = View.VISIBLE
                binding.fourthStar.visibility = View.VISIBLE
            }
            5 -> {
                binding.firstStar.visibility = View.VISIBLE
                binding.secondStar.visibility = View.VISIBLE
                binding.thirdStar.visibility = View.VISIBLE
                binding.fourthStar.visibility = View.VISIBLE
                binding.fifthStar.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}