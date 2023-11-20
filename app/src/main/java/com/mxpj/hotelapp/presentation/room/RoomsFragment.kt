package com.mxpj.hotelapp.presentation.room

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.FragmentRoomsBinding
import com.mxpj.hotelapp.presentation.BaseFragment
import com.mxpj.hotelapp.presentation.ViewModelFactory
import javax.inject.Inject

class RoomsFragment: BaseFragment<FragmentRoomsBinding>(FragmentRoomsBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RoomsViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[RoomsViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRoomAdapter()
        setupOnBackButtonClick()
    }

    private fun setupRoomAdapter() {
        val adapter = RoomAdapter(requireActivity())
        viewModel.roomList.observe(requireActivity()){
            adapter.roomList = it
        }
        adapter.onSelectRoomCallback = {
            findNavController().navigate(R.id.action_roomsFragment_to_bookingFragment)
        }
        binding.rvRooms.adapter = adapter
    }

    private fun setupOnBackButtonClick() {
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}