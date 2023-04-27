package com.example.myhotels.ui.hotelDetailScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelDetailBinding
import com.example.myhotels.ui.hotelDetailScreen.utils.CropBitmapTransformation
import com.squareup.picasso.Picasso


class HotelDetailFragment : Fragment() {

    companion object {
        const val HOTEL_ITEM_ID = "extra_hotel_item_id"
        const val KEY_SAVED_ID = "key_saved_id"

        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOTEL_ITEM_ID, hotelId)
                }
            }
    }

    private lateinit var hotelCoordinates: String
    private lateinit var viewModel: HotelDetailViewModel

    private var _binding: FragmentHotelDetailBinding? = null
    private val binding: FragmentHotelDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelDetailBinding is null")

    private var hotelId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            hotelId = savedInstanceState.getInt(KEY_SAVED_ID)
        } else {
            arguments?.let {
                hotelId = it.getInt(HOTEL_ITEM_ID)
            }
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
        initialToolbar()

        hotelId?.let {
            viewModel.getHotelDetailItem(it)
        }

        viewModel.hotelItem.observe(viewLifecycleOwner) {
            with(binding) {
                hotelName.text = it.name
                hotelAddress.text = it.address
                distanceFromCenter.text =
                    resources.getString(R.string.distance_from_center, it.distance)
                hotelSuitesAvailability.text =
                    resources.getString(R.string.count_of_free_rooms, it.suitesAvailability)
                setNumberOfStars(it.stars)
                getAndSetImage(it.image)
                hotelCoordinates = it.coordinates
            }
        }

        binding.showLocation.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(hotelCoordinates))
            startActivity(intent)
        }
    }

    private fun getAndSetImage(image: String) {
        Picasso.get()
            .load(image)
            .error(R.drawable.ic_image_not_supported_24)
            .transform(CropBitmapTransformation())
            .into(binding.ivHotelCover)
    }

    private fun initialToolbar() {
        binding.secondScreenToolbar.setNavigationIcon(R.drawable.ic_back_24)
        binding.secondScreenToolbar.setTitle(R.string.about_hotel)
        binding.secondScreenToolbar.setNavigationOnClickListener {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        hotelId?.let { outState.putInt(KEY_SAVED_ID, it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}