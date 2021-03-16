package com.indiannewssroom.app.model

import com.google.gson.annotations.SerializedName

data class PageData(
    @SerializedName("author")
    val author: Int,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("content")
    val content: Content,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    @SerializedName("excerpt")
    val excerpt: Excerpt,
    @SerializedName("featured_media")
    val featuredMedia: Int,
    @SerializedName("guid")
    val guid: Guid,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("menu_order")
    val menuOrder: Int,
    @SerializedName("meta")
    val meta: List<Any>,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    @SerializedName("parent")
    val parent: Int,
    @SerializedName("ping_status")
    val pingStatus: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("template")
    val template: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("type")
    val type: String
) {
    data class Content(
        @SerializedName("protected")
        val `protected`: Boolean,
        @SerializedName("rendered")
        val rendered: String
    )

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
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("self")
        val self: List<Self>,
        @SerializedName("version-history")
        val versionHistory: List<VersionHistory>,
        @SerializedName("wp:attachment")
        val wpAttachment: List<WpAttachment>
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
    }

    data class Title(
        @SerializedName("rendered")
        val rendered: String
    )
}
