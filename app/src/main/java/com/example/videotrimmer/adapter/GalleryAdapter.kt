package com.example.videotrimmer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videotrimmer.databinding.ListItemVideosBinding
import com.example.videotrimmer.ui.models.VideoItem

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var videoList: List<VideoItem> = ArrayList()
    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ListItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(videoList[position])
        holder.cdMain.setOnClickListener { listener?.onItemClick(videoList.get(position)) }
    }

    class GalleryViewHolder(private val binding: ListItemVideosBinding) : RecyclerView.ViewHolder(binding.root) {

        val cdMain = binding.cdMain

        fun bind(item: VideoItem) {
            binding.videoDuration.text = item.duration
            binding.videoSize.text = item.size
            Glide.with(binding.root.context).load(item.videoPath).into(binding.videoThumb)

        }

    }

    fun submitListToAdapter(videoList: List<VideoItem>)
    {
        this.videoList = videoList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: VideoItem)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}