package com.treadhill.app.dataTypes

import android.os.Parcelable
import com.treadhill.app.R
import kotlinx.android.parcel.Parcelize


data class Category(val name: String, val items: ArrayList<VideoItem>)

@Parcelize
data class VideoItem(
    var name: String = "sample video",
    var CreatorID: String? = null,
    var WorkoutID: String? = null,
    var vimeoUri: String? = null,
    var VideoDescription: String? = null,
    var VideoCategory: String? = null,
    var rating: Float? = null,
    var LevelOfDifficulty: String? = null,
    var UploadDate: Long? = null,
    var ThumbnailUrl: String? = null,
    var NoOfUniqueViewers: Int? = null,
    var Calories: Int? = null,
    var FocusArea: String? = null,
    var Equipment: String? = null,
    var CreatorName: String? = null,
    var VideoLength: Int? = null,
    var MusicType: String? = null,
    var imgId: Int = R.mipmap.img1,
    var taretZones: List<Int>? = null
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VideoItem

        if (FocusArea != other.FocusArea) return false
        if (Equipment != other.Equipment) return false
        if (CreatorName != other.CreatorName) return false
        if (VideoLength != other.VideoLength) return false
        if (MusicType != other.MusicType) return false
        if (imgId != other.imgId) return false
        return true
    }

    override fun hashCode(): Int {
        var result = FocusArea?.hashCode() ?: 0
        result = 31 * result + (CreatorName?.hashCode() ?: 0)
        result = 31 * result + (VideoLength ?: 0)
        result = 31 * result + (MusicType?.hashCode() ?: 0)
        result = 31 * result + imgId
        return result
    }
}
