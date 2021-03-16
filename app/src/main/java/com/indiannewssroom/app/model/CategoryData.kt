package com.indiannewssroom.app.model

import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("count")
    val count: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("meta")
    val meta: List<Any>,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent")
    val parent: Int,
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
