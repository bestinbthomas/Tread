package com.treadhill.app.utilities

import android.content.Context
import android.util.Log
import com.treadhill.app.R
import com.treadhill.app.dataTypes.Result
import com.vimeo.networking.Configuration
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.callbacks.ModelCallback
import com.vimeo.networking.model.User
import com.vimeo.networking.model.Video
import com.vimeo.networking.model.VideoList
import com.vimeo.networking.model.error.VimeoError
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Utility class to get videos from vimeo
 *
 * this Utility class uses API in https://github.com/vimeo/vimeo-networking-java library
 * refer that for more info
 *
 * @constructor
 *
 * @param context
 */
class VimeoUtils private constructor(context: Context) {
    companion object {
        @Volatile
        var instance: VimeoUtils? = null

        /**
         * get the util instance
         *
         * @param context
         * @return
         */
        fun getInstance(context: Context): VimeoUtils = instance ?: synchronized(this) {
            instance ?: VimeoUtils(context).also { instance = it }
        }

        /**
         * get MP4 url from the video file
         *
         * @param video Vimeo Video
         */
        fun getProgressiveUrlsFromVideo(video: Video) =
            video.download
                ?.map {
                    it.getLink()
                }

        /**
         * get dash url from the video file
         *
         * @param video Vimeo Video
         */
        fun getDashUrlFromVideo(video: Video) =
            video.play?.dashVideoFile?.getLink()

        /**
         * get hls url from the video file
         *
         * @param video Vimeo Video
         */
        fun getHlsUrlFromVideo(video: Video) =
            video.play?.hlsVideoFile?.getLink()

    }

    /**
     * Vimeo API token it is secret do not publish
     */
    val TOKEN = context.getString(R.string.acess_token2)
    val config = Configuration.Builder(TOKEN)
        .setCacheDirectory(context.cacheDir)
        .build()
    val cachedVideos = HashMap<String, Video>()
    private val vimoClient: VimeoClient

    /**
     * the an instance of VimeoClient
     */
    init {
        VimeoClient.initialize(config)
        vimoClient = VimeoClient.getInstance()
    }

    /**
     * get the Vimeo User
     *
     *
     * @return
     */
    private suspend fun getUser(): Result<User> = suspendCancellableCoroutine { continuation ->
        vimoClient.fetchCurrentUser(object : ModelCallback<User>(User::class.java) {
            override fun success(t: User?) {
                Log.i("VIMEO", "user signed in name : ${t?.name}")
                continuation.resume(Result(t))
            }

            override fun failure(error: VimeoError?) {
                continuation.resume(Result(error))
            }

        })

    }

    /**
     * fetch video info from vimeo
     *
     * @param uri in the form "/videos/394219538"
     * @return
     */
    suspend fun getVideo(uri: String): Result<Video> = suspendCancellableCoroutine { cont ->
        Log.i("VIMEO", "get video called with id $uri")
        vimoClient.fetchNetworkContent(uri,
            object : ModelCallback<Video>(Video::class.java) {
                override fun success(t: Video?) {
                    cont.resume(Result(t))
                }

                override fun failure(error: VimeoError?) {
                    Log.e("VIMEO", "user not signed in", error?.cause)
                    cont.resume(Result(error))
                }

            })
    }

    /**
     * fetch Multiple videos from vimeo
     *
     * @param uri of the from "/<user.name>/videos"
     * @return
     */
    private suspend fun getMultipleVids(uri: String): Result<VideoList> =
        suspendCancellableCoroutine { cont ->
            vimoClient.fetchNetworkContent(uri,
                object : ModelCallback<VideoList>(VideoList::class.java) {
                    override fun success(t: VideoList?) {
                        Log.i("Vimeo", "videos recieved no ${t?.data?.size}")
                        cont.resume(Result(t))
                    }

                    override fun failure(error: VimeoError?) {
                        Log.e("VIMEO", "user not signed in", error?.cause)
                        cont.resume(Result(error))
                    }

                })
        }

    /**
     * get all videos uploaded bu the user that is all the videos uploaded to vimeo account
     *
     * @return
     */
    suspend fun getUserVids(): List<Video> =
        if (cachedVideos.isNullOrEmpty()) {
            Log.i("VIMEO", "get vids called")
            val result = getUser()
            Log.i("VIMEO", "get vids called user got ${result.value} or ${result.exception}")
            result.value?.let { user ->
                val uri = user.videosConnection?.uri
                val multipleVidsresutl = getMultipleVids(uri ?: "${user.name}/videos")
                multipleVidsresutl.value?.let { videoList ->
                    videoList.data.forEach { video ->
                        cachedVideos[video.uri] = video
                    }
                }
            }
            cachedVideos.map { it.value }
        } else {
            cachedVideos.map { it.value }
        }
}
