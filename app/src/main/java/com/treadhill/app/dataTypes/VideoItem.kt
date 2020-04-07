package com.treadhill.app.dataTypes

import android.os.Parcelable
import com.treadhill.app.R
import kotlinx.android.parcel.Parcelize

/**
 * Category Class
 *
 * @property name Category Name
 * @property items List of Videos in the Category
 */
data class Category(val name: String, val items: ArrayList<VideoItem>)

/**
 * Video Class as in Firebase and app
 *
 * @property name Title of Video
 * @property CreatorID Id of creator
 * @property WorkoutID Workout Id
 * @property vimeoUri Vimeo Uri in the form "/videos/394219538"
 * @property VideoDescription Description of the video
 * @property VideoCategory Category of Video
 * @property rating Rating of Video
 * @property LevelOfDifficulty Diffivulty Level
 * @property UploadDate UploadDate
 * @property ThumbnailUrl Url to get the thumbnail from
 * @property NoOfUniqueViewers No of Viewres
 * @property Calories Expected amount of calories that will be burnt
 * @property FocusArea Area of Focus in the workout
 * @property Equipment Equipments required to follow the video
 * @property CreatorName Creator Name
 * @property VideoLength Length of Video in MINUTES
 * @property MusicType Music Type
 * @property imgId ResId of Thumbnail (will be shown in case the Thumbnail Url is null or invalid)
 * @property taretZones Array on target zones for duration of 10 seconds each. allowed values in range 0 to 4
 */
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
