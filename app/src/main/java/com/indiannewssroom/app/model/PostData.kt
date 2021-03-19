package com.indiannewssroom.app.model
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

@SuppressLint("ParcelCreator")
@Parcelize
data class PostData(
    @SerializedName("content")
    val content: Content?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("_embedded")
    val embedded: Embedded?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("title")
    val title: Title?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Content(
        @SerializedName("protected")
        val `protected`: Boolean?,
        @SerializedName("rendered")
        val rendered: String?
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Embedded(
        @SerializedName("author")
        val author: List<Author?>?,
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<WpFeaturedmedia?>?,
        @SerializedName("wp:term")
        val wpTerm: List<List<WpTerm?>?>?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Author(
            @SerializedName("avatar_urls")
            val avatarUrls: AvatarUrls?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("link")
            val link: String?,
            @SerializedName("_links")
            val links: Links?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?,
            @SerializedName("url")
            val url: String?,
            @SerializedName("yoast_head")
            val yoastHead: String?
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class AvatarUrls(
                @SerializedName("24")
                val x24: String?,
                @SerializedName("48")
                val x48: String?,
                @SerializedName("96")
                val x96: String?
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Links(
                @SerializedName("collection")
                val collection: List<Collection?>?,
                @SerializedName("self")
                val self: List<Self?>?
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Collection(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Self(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable
            }
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WpFeaturedmedia(
            @SerializedName("alt_text")
            val altText: String?,
            @SerializedName("author")
            val author: Int?,
            @SerializedName("caption")
            val caption: Caption?,
            @SerializedName("date")
            val date: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("link")
            val link: String?,
            @SerializedName("_links")
            val links: Links?,
            @SerializedName("media_details")
            val mediaDetails: MediaDetails?,
            @SerializedName("media_type")
            val mediaType: String?,
            @SerializedName("mime_type")
            val mimeType: String?,
            @SerializedName("slug")
            val slug: String?,
            @SerializedName("source_url")
            val sourceUrl: String?,
            @SerializedName("title")
            val title: Title?,
            @SerializedName("type")
            val type: String?,
            @SerializedName("yoast_head")
            val yoastHead: String?
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Caption(
                @SerializedName("rendered")
                val rendered: String?
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Links(
                @SerializedName("about")
                val about: List<About?>?,
                @SerializedName("author")
                val author: List<Author?>?,
                @SerializedName("collection")
                val collection: List<Collection?>?,
                @SerializedName("replies")
                val replies: List<Reply?>?,
                @SerializedName("self")
                val self: List<Self?>?
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class About(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Author(
                    @SerializedName("embeddable")
                    val embeddable: Boolean?,
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Collection(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Reply(
                    @SerializedName("embeddable")
                    val embeddable: Boolean?,
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Self(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class MediaDetails(
                @SerializedName("file")
                val `file`: String?,
                @SerializedName("height")
                val height: Int?,
                @SerializedName("image_meta")
                val imageMeta: ImageMeta?,
                @SerializedName("sizes")
                val sizes: Sizes?,
                @SerializedName("width")
                val width: Int?
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class ImageMeta(
                    @SerializedName("aperture")
                    val aperture: String?,
                    @SerializedName("camera")
                    val camera: String?,
                    @SerializedName("caption")
                    val caption: String?,
                    @SerializedName("copyright")
                    val copyright: String?,
                    @SerializedName("created_timestamp")
                    val createdTimestamp: String?,
                    @SerializedName("credit")
                    val credit: String?,
                    @SerializedName("focal_length")
                    val focalLength: String?,
                    @SerializedName("iso")
                    val iso: String?,
                    @SerializedName("keywords")
                    val keywords: @RawValue List<Any?>?,
                    @SerializedName("orientation")
                    val orientation: String?,
                    @SerializedName("shutter_speed")
                    val shutterSpeed: String?,
                    @SerializedName("title")
                    val title: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Sizes(
                    @SerializedName("full")
                    val full: Full?,
                    @SerializedName("jannah-image-large")
                    val jannahImageLarge: JannahImageLarge?,
                    @SerializedName("jannah-image-post")
                    val jannahImagePost: JannahImagePost?,
                    @SerializedName("jannah-image-small")
                    val jannahImageSmall: JannahImageSmall?,
                    @SerializedName("medium")
                    val medium: Medium?,
                    @SerializedName("medium_large")
                    val mediumLarge: MediumLarge?,
                    @SerializedName("thumbnail")
                    val thumbnail: Thumbnail?
                ) : Parcelable {
                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class Full(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class JannahImageLarge(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class JannahImagePost(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class JannahImageSmall(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class Medium(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class MediumLarge(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable

                    @SuppressLint("ParcelCreator")
                    @Parcelize
                    data class Thumbnail(
                        @SerializedName("file")
                        val `file`: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("mime_type")
                        val mimeType: String?,
                        @SerializedName("source_url")
                        val sourceUrl: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) : Parcelable
                }
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Title(
                @SerializedName("rendered")
                val rendered: String?
            ) : Parcelable
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WpTerm(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("link")
            val link: String?,
            @SerializedName("_links")
            val links: Links?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?,
            @SerializedName("taxonomy")
            val taxonomy: String?,
            @SerializedName("yoast_head")
            val yoastHead: String?
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Links(
                @SerializedName("about")
                val about: List<About?>?,
                @SerializedName("collection")
                val collection: List<Collection?>?,
                @SerializedName("curies")
                val curies: List<Cury?>?,
                @SerializedName("self")
                val self: List<Self?>?,
                @SerializedName("wp:post_type")
                val wpPostType: List<WpPostType?>?
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class About(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Collection(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Cury(
                    @SerializedName("href")
                    val href: String?,
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("templated")
                    val templated: Boolean?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Self(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class WpPostType(
                    @SerializedName("href")
                    val href: String?
                ) : Parcelable
            }
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Links(
        @SerializedName("about")
        val about: List<About?>?,
        @SerializedName("author")
        val author: List<Author?>?,
        @SerializedName("collection")
        val collection: List<Collection?>?,
        @SerializedName("curies")
        val curies: List<Cury?>?,
        @SerializedName("predecessor-version")
        val predecessorVersion: List<PredecessorVersion?>?,
        @SerializedName("replies")
        val replies: List<Reply?>?,
        @SerializedName("self")
        val self: List<Self?>?,
        @SerializedName("version-history")
        val versionHistory: List<VersionHistory?>?,
        @SerializedName("wp:attachment")
        val wpAttachment: List<WpAttachment?>?,
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<WpFeaturedmedia?>?,
        @SerializedName("wp:term")
        val wpTerm: List<WpTerm?>?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class About(
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Author(
            @SerializedName("embeddable")
            val embeddable: Boolean?,
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Collection(
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Cury(
            @SerializedName("href")
            val href: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("templated")
            val templated: Boolean?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class PredecessorVersion(
            @SerializedName("href")
            val href: String?,
            @SerializedName("id")
            val id: Int?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Reply(
            @SerializedName("embeddable")
            val embeddable: Boolean?,
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Self(
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class VersionHistory(
            @SerializedName("count")
            val count: Int?,
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WpAttachment(
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WpFeaturedmedia(
            @SerializedName("embeddable")
            val embeddable: Boolean?,
            @SerializedName("href")
            val href: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WpTerm(
            @SerializedName("embeddable")
            val embeddable: Boolean?,
            @SerializedName("href")
            val href: String?,
            @SerializedName("taxonomy")
            val taxonomy: String?
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Title(
        @SerializedName("rendered")
        val rendered: String?
    ) : Parcelable
}
