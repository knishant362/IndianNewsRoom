package com.indiannewssroom.app.model

import com.google.gson.annotations.SerializedName

data class MediaData(
    @SerializedName("alt_text")
    val altText: String,
    @SerializedName("author")
    val author: Int,
    @SerializedName("caption")
    val caption: Caption,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    @SerializedName("description")
    val description: Description,
    @SerializedName("guid")
    val guid: Guid,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("media_details")
    val mediaDetails: MediaDetails,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("meta")
    val meta: List<Any>,
    @SerializedName("mime_type")
    val mimeType: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    @SerializedName("post")
    val post: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("template")
    val template: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("type")
    val type: String
) {
    data class Caption(
        @SerializedName("rendered")
        val rendered: String
    )

    data class Description(
        @SerializedName("rendered")
        val rendered: String
    )

    data class Guid(
        @SerializedName("rendered")
        val rendered: String
    )

    data class Links(
        @SerializedName("about")
        val about: List<About>,
        @SerializedName("author")
        val author: List<Author>,
        @SerializedName("collection")
        val collection: List<Collection>,
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("self")
        val self: List<Self>
    ) {
        data class About(
            @SerializedName("href")
            val href: String
        )

        data class Author(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class Collection(
            @SerializedName("href")
            val href: String
        )

        data class Reply(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class Self(
            @SerializedName("href")
            val href: String
        )
    }

    data class MediaDetails(
        @SerializedName("file")
        val `file`: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("image_meta")
        val imageMeta: ImageMeta,
        @SerializedName("sizes")
        val sizes: Sizes,
        @SerializedName("width")
        val width: Int
    ) {
        data class ImageMeta(
            @SerializedName("aperture")
            val aperture: String,
            @SerializedName("camera")
            val camera: String,
            @SerializedName("caption")
            val caption: String,
            @SerializedName("copyright")
            val copyright: String,
            @SerializedName("created_timestamp")
            val createdTimestamp: String,
            @SerializedName("credit")
            val credit: String,
            @SerializedName("focal_length")
            val focalLength: String,
            @SerializedName("iso")
            val iso: String,
            @SerializedName("keywords")
            val keywords: List<Any>,
            @SerializedName("orientation")
            val orientation: String,
            @SerializedName("shutter_speed")
            val shutterSpeed: String,
            @SerializedName("title")
            val title: String
        )

        data class Sizes(
            @SerializedName("full")
            val full: Full,
            @SerializedName("large")
            val large: Large,
            @SerializedName("medium")
            val medium: Medium,
            @SerializedName("medium_large")
            val mediumLarge: MediumLarge,
            @SerializedName("thumbnail")
            val thumbnail: Thumbnail
        ) {
            data class Full(
                @SerializedName("file")
                val `file`: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("width")
                val width: Int
            )

            data class Large(
                @SerializedName("file")
                val `file`: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("width")
                val width: Int
            )

            data class Medium(
                @SerializedName("file")
                val `file`: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("width")
                val width: Int
            )

            data class MediumLarge(
                @SerializedName("file")
                val `file`: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("width")
                val width: Int
            )

            data class Thumbnail(
                @SerializedName("file")
                val `file`: String,
                @SerializedName("height")
                val height: Int,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("width")
                val width: Int
            )
        }
    }

    data class Title(
        @SerializedName("rendered")
        val rendered: String
    )
}
