package com.example.videotrimmer.ui.models

import android.net.Uri

data class VideoItem(
    val videoPath: String,
    val duration: String,
    val size: String,
    val videoUri: Uri
)
