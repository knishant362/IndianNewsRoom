package com.indiannewssroom.app.model

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("_embedded")
    val embedded: Embedded,
    @SerializedName("id")
    val id: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) {
    data class Embedded(
        @SerializedName("self")
        val self: List<Self>
    ) {
        data class Self(
            @SerializedName("author")
            val author: Int,
            @SerializedName("date")
            val date: String,
            @SerializedName("excerpt")
            val excerpt: Excerpt,
            @SerializedName("featured_media")
            val featuredMedia: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("link")
            val link: String,
            @SerializedName("_links")
            val links: Links,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("title")
            val title: Title,
            @SerializedName("type")
            val type: String
        ) {
            data class Excerpt(
                @SerializedName("protected")
                val `protected`: Boolean,
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
    }

    data class Links(
        @SerializedName("about")
        val about: List<About>,
        @SerializedName("collection")
        val collection: List<Collection>,
        @SerializedName("self")
        val self: List<Self>
    ) {
        data class About(
            @SerializedName("href")
            val href: String
        )

        data class Collection(
            @SerializedName("href")
            val href: String
        )

        data class Self(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )
    }
}
