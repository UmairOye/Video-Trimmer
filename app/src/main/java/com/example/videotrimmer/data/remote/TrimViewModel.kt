package com.example.videotrimmer.ui.data.remote

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videotrimmer.ui.models.VideoItem

class TrimViewModel: ViewModel() {
    fun loadVideos(context: Context): LiveData<List<VideoItem>> {
        val videoLiveData = MutableLiveData<List<VideoItem>>()
        val videosList = mutableListOf<VideoItem>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID, // Add the _ID field to get video IDs
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val cursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val pathColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn) // Get the video ID
                val path = it.getString(pathColumn)
                val durationMs = it.getLong(durationColumn)
                val size = it.getLong(sizeColumn)

                val durationFormatted = formatDuration(durationMs)
                val sizeFormatted = formatSize(size)

                val videoUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                ) // Get the video URI

                val video = VideoItem(path, durationFormatted, sizeFormatted, videoUri)
                videosList.add(video)
            }
        }
        videoLiveData.postValue(videosList)
        return videoLiveData
    }

    private fun formatDuration(duration: Long): String
    {
        val seconds = (duration / 1000).toInt()
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
    }

    private fun formatSize(size: Long): String
    {
        val kilobyte: Long = 1024
        val megabyte = kilobyte * 1024
        val gigabyte = megabyte * 1024
        val terabyte = gigabyte * 1024

        return if (size in 0..<kilobyte) {
            "$size B"
        } else if (size in kilobyte..<megabyte) {
            (size / kilobyte).toString() + " KB"
        } else if (size in megabyte..<gigabyte) {
            (size / megabyte).toString() + " MB"
        } else if (size in gigabyte..<terabyte) {
            (size / gigabyte).toString() + " GB"
        } else if (size >= terabyte) {
            (size / terabyte).toString() + " TB"
        } else {
            "$size size"
        }
    }
}