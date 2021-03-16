package com.indiannewssroom.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PostData(
    @SerializedName("author")
    val author: Int,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("content")
    val content: @RawValue Content,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    @SerializedName("_embedded")
    val embedded: @RawValue Embedded,
    @SerializedName("excerpt")
    val excerpt: @RawValue Excerpt,
    @SerializedName("featured_media")
    val featuredMedia: Int,
    @SerializedName("format")
    val format: String,
    @SerializedName("guid")
    val guid: @RawValue Guid,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: @RawValue Links,
    @SerializedName("meta")
    val meta: @RawValue List<Any>,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("sticky")
    val sticky: Boolean,
    @SerializedName("tags")
    val tags: @RawValue List<Any>,
    @SerializedName("template")
    val template: String,
    @SerializedName("title")
    val title: @RawValue Title,
    @SerializedName("type")
    val type: String
): Parcelable {
    data class Content(
        @SerializedName("protected")
        val `protected`: Boolean,
        @SerializedName("rendered")
        val rendered: String
    )

    data class Embedded(
        @SerializedName("author")
        val author: List<Author>,
        @SerializedName("replies")
        val replies: List<List<Reply>>,
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<WpFeaturedmedia>,
        @SerializedName("wp:term")
        val wpTerm: List<List<WpTerm>>
    ) {
        data class Author(
            @SerializedName("avatar_urls")
            val avatarUrls: AvatarUrls,
            @SerializedName("description")
            val description: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("_links")
            val links: Links,
            @SerializedName("name")
            val name: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("url")
            val url: String
        ) {
            data class AvatarUrls(
                @SerializedName("24")
                val x24: String,
                @SerializedName("48")
                val x48: String,
                @SerializedName("96")
                val x96: String
            )

            data class Links(
                @SerializedName("collection")
                val collection: List<Collection>,
                @SerializedName("self")
                val self: List<Self>
            ) {
                data class Collection(
                    @SerializedName("href")
                    val href: String
                )

                data class Self(
                    @SerializedName("href")
                    val href: String
                )
            }
        }

        data class Reply(
            @SerializedName("author")
            val author: Int,
            @SerializedName("author_avatar_urls")
            val authorAvatarUrls: AuthorAvatarUrls,
            @SerializedName("author_name")
            val authorName: String,
            @SerializedName("author_url")
            val authorUrl: String,
            @SerializedName("content")
            val content: Content,
            @SerializedName("date")
            val date: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("_links")
            val links: Links,
            @SerializedName("parent")
            val parent: Int,
            @SerializedName("type")
            val type: String
        ) {
            data class AuthorAvatarUrls(
                @SerializedName("24")
                val x24: String,
                @SerializedName("48")
                val x48: String,
                @SerializedName("96")
                val x96: String
            )

            data class Content(
                @SerializedName("rendered")
                val rendered: String
            )

            data class Links(
                @SerializedName("collection")
                val collection: List<Collection>,
                @SerializedName("self")
                val self: List<Self>,
                @SerializedName("up")
                val up: List<Up>
            ) {
                data class Collection(
                    @SerializedName("href")
                    val href: String
                )

                data class Self(
                    @SerializedName("href")
                    val href: String
                )

                data class Up(
                    @SerializedName("embeddable")
                    val embeddable: Boolean,
                    @SerializedName("href")
                    val href: String,
                    @SerializedName("post_type")
                    val postType: String
                )
            }
        }

        data class WpFeaturedmedia(
            @SerializedName("alt_text")
            val altText: String,
            @SerializedName("author")
            val author: Int,
            @SerializedName("caption")
            val caption: Caption,
            @SerializedName("date")
            val date: String,
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
            @SerializedName("mime_type")
            val mimeType: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("source_url")
            val sourceUrl: String,
            @SerializedName("title")
            val title: Title,
            @SerializedName("type")
            val type: String
        ) {
            data class Caption(
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

        data class WpTerm(
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("_links")
            val links: Links,
            @SerializedName("name")
            val name: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("taxonomy")
            val taxonomy: String
        ) {
            data class Links(
                @SerializedName("about")
                val about: List<About>,
                @SerializedName("collection")
                val collection: List<Collection>,
                @SerializedName("curies")
                val curies: List<Cury>,
                @SerializedName("self")
                val self: List<Self>,
                @SerializedName("wp:post_type")
                val wpPostType: List<WpPostType>
            ) {
                data class About(
                    @SerializedName("href")
                    val href: String
                )

                data class Collection(
                    @SerializedName("href")
                    val href: String
                )

                data class Cury(
                    @SerializedName("href")
                    val href: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("templated")
                    val templated: Boolean
                )

                data class Self(
                    @SerializedName("href")
                    val href: String
                )

                data class WpPostType(
                    @SerializedName("href")
                    val href: String
                )
            }
        }
    }

    data class Excerpt(
        @SerializedName("protected")
        val `protected`: Boolean,
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
        @SerializedName("curies")
        val curies: List<Cury>,
        @SerializedName("predecessor-version")
        val predecessorVersion: List<PredecessorVersion>,
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("self")
        val self: List<Self>,
        @SerializedName("version-history")
        val versionHistory: List<VersionHistory>,
        @SerializedName("wp:attachment")
        val wpAttachment: List<WpAttachment>,
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<WpFeaturedmedia>,
        @SerializedName("wp:term")
        val wpTerm: List<WpTerm>
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

        data class Cury(
            @SerializedName("href")
            val href: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("templated")
            val templated: Boolean
        )

        data class PredecessorVersion(
            @SerializedName("href")
            val href: String,
            @SerializedName("id")
            val id: Int
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

        data class VersionHistory(
            @SerializedName("count")
            val count: Int,
            @SerializedName("href")
            val href: String
        )

        data class WpAttachment(
            @SerializedName("href")
            val href: String
        )

        data class WpFeaturedmedia(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class WpTerm(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String,
            @SerializedName("taxonomy")
            val taxonomy: String
        )
    }

    data class Title(
        @SerializedName("rendered")
        val rendered: String
    )
}
