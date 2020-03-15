package com.treadhill.app.utilities

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.treadhill.app.R
import com.vimeo.networking.model.Video

class ExoUtils(val context: Context) {

    companion object {
        val MIN_BUFFER_DURATION = 3000
        val MAX_BUFFER_DURATION = 5000
        val MIN_PLAYBACK_START_BUFFER = 1500
        val MIN_PLAYBACK_RESUME_BUFFER = 5000

        var exoUtils: ExoUtils? = null

        fun getLoadControl() = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                MIN_BUFFER_DURATION * 2, MAX_BUFFER_DURATION * 2, MIN_PLAYBACK_START_BUFFER,
                MIN_PLAYBACK_RESUME_BUFFER
            )
            .createDefaultLoadControl()

        fun getTrackSelector() = DefaultTrackSelector(AdaptiveTrackSelection.Factory())
    }

    val QualityList = ArrayList<String>()

    fun getMediaSource(video: Video): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.getString(R.string.app_name))
        )
        Log.i("EXO", "get media called")
        VimeoUtils.getDashUrlFromVideo(video)?.let { dashUrl ->
            Log.i("EXO", "DASH url")
            val uri = Uri.parse(dashUrl)
            return DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        }
        VimeoUtils.getHlsUrlFromVideo(video)?.let { hlsUrl ->
            Log.i("EXO", "hls url")
            return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(hlsUrl))
        }
        VimeoUtils.getProgressiveUrlsFromVideo(video)?.let { urls ->
            Log.i("EXO", "progressive url $urls")

            urls.forEach { Log.e("VIDEO URL ", it) }
            QualityList.clear()
            QualityList.addAll(urls)
            return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(urls[0]))
        }
        Log.i("EXO", "no url")
        return null
    }


}