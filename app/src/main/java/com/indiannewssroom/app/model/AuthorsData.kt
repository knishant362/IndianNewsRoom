package com.indiannewssroom.app.model

import com.google.gson.annotations.SerializedName

data class AuthorsData(
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
    @SerializedName("meta")
    val meta: List<Any>,
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
