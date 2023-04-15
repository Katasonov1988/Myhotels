package com.example.myhotels.ui.hotelDetailScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myhotels.Injection
import com.example.myhotels.R
import com.example.myhotels.databinding.FragmentHotelDetailBinding
import com.example.myhotels.databinding.FragmentHotelListBinding
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.ui.hotelsScreen.HotelViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val HOTEL_ITEM_ID = "extra_hotel_item_id"

/**
 * A simple [Fragment] subclass.
 * Use the [HotelDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelDetailFragment : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel

    private var _binding: FragmentHotelDetailBinding? = null
    private val binding: FragmentHotelDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelDetailBinding is null")

    //    // TODO: Rename and change types of parameters
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
        setDataToView()
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
                Picasso.get().load(it.image).into(ivHotelCover)
            }
        }
    }

    private fun initialToolbar() {
        binding.secondScreenToolbar.inflateMenu(R.menu.first_screen_filter_menu)
        binding.secondScreenToolbar.setNavigationIcon(R.drawable.ic_back_24)
        binding.secondScreenToolbar.setTitle("Установить Заголовок")
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, Injection.provideHotelDetailViewModelFactory()
        ).get(HotelDetailViewModel::class.java)
    }

    private fun setDataToView() {
//    viewModel.getBookDetaiItem(bookItemId)
//    viewModel.bookItem.observe(this) {
//        with(binding) {
//            tvBookDetailTitle.text = it.title
//            tvBookDetailAuthor.text = it.authors
//            tvBookDetailPublishedDate.text =
//                resources?.getString(R.string.published_date, it.publishedDate)
//            tvBookDetailPageCount.text =
//                resources?.getString(R.string.page_count, it.pageCount.toString())
//            tvBookDetailLanguage.text = resources?.getString(R.string.language, it.language)
//            tvBookDetailDescription.text = it.description
//            if (it.imageLinks == null) {
//                ivBookDetailCover.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
//            } else {
//                Picasso.get().load(it.imageLinks).into(ivBookDetailCover)
//            }
//        }
//    }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HotelDetailFragment.
         */
        // TODO: Rename and change types and number of parameters

        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOTEL_ITEM_ID, hotelId)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}