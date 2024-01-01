package com.example.videotrimmer.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.videotrimmer.R
import com.example.videotrimmer.databinding.FragmentVideoGalleryBinding
import com.example.videotrimmer.ui.adapter.GalleryAdapter
import com.example.videotrimmer.ui.data.remote.TrimViewModel
import com.example.videotrimmer.ui.models.VideoItem

class VideoGallery : Fragment() {
    private var _binding: FragmentVideoGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrimViewModel by activityViewModels()
    private lateinit var adapter: GalleryAdapter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoGalleryBinding.inflate(inflater, container, false)

        binding.btnAllowPermission.setOnClickListener {
            startReadExternalStoragePermission()
        }
        adapter = GalleryAdapter()
        binding.rvVideo.adapter = adapter

        adapter.setOnClickListener(listener = object : GalleryAdapter.OnClickListener {
            override fun onItemClick(item: VideoItem) {
                try {
                    val bundle = Bundle()
                    bundle.putString("videoPath", item.videoUri.toString())
                    findNavController().navigate(R.id.action_videoGallery_to_videoTrimming, bundle)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun startReadExternalStoragePermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            this.requestReadPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_VIDEO)
        } else {
            this.requestReadPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val requestReadPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startWriteStoragePermission()
        } else {
            Toast.makeText(
                requireContext(),
                "Allow media permission to continue",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showVideoGallery() {
        viewModel.loadVideos(requireContext()).observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.btnAllowPermission.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                adapter.submitListToAdapter(it)
            }
        }
    }


    private fun startWriteStoragePermission() {
        this.requestWritePermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private val requestWritePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            binding.progressBar.visibility = View.VISIBLE
            showVideoGallery()
        }
    }
}