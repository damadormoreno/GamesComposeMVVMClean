package com.example.rawgcompose.features.games.models


import com.google.gson.annotations.SerializedName

data class GameSearchDto(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("previous")
    val previous: Any? = Any(),
    @SerializedName("results")
    val results: List<SearchGame?>? = listOf(),
    @SerializedName("user_platforms")
    val userPlatforms: Boolean? = false
) {
    data class SearchGame(
        @SerializedName("slug")
        val slug: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("playtime")
        val playtime: Int? = 0,
        @SerializedName("platforms")
        val platforms: List<Platform?>? = listOf(),
        @SerializedName("stores")
        val stores: Any? = Any(),
        @SerializedName("released")
        val released: String? = "",
        @SerializedName("tba")
        val tba: Boolean? = false,
        @SerializedName("background_image")
        val backgroundImage: String? = "",
        @SerializedName("rating")
        val rating: Double? = 0.0,
        @SerializedName("rating_top")
        val ratingTop: Int? = 0,
        @SerializedName("ratings")
        val ratings: List<Rating?>? = listOf(),
        @SerializedName("ratings_count")
        val ratingsCount: Int? = 0,
        @SerializedName("reviews_text_count")
        val reviewsTextCount: Int? = 0,
        @SerializedName("added")
        val added: Int? = 0,
        @SerializedName("added_by_status")
        val addedByStatus: AddedByStatus? = AddedByStatus(),
        @SerializedName("metacritic")
        val metacritic: Any? = Any(),
        @SerializedName("suggestions_count")
        val suggestionsCount: Int? = 0,
        @SerializedName("updated")
        val updated: String? = "",
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("score")
        val score: String? = "",
        @SerializedName("clip")
        val clip: Any? = Any(),
        @SerializedName("tags")
        val tags: List<Tag?>? = listOf(),
        @SerializedName("esrb_rating")
        val esrbRating: Any? = Any(),
        @SerializedName("user_game")
        val userGame: Any? = Any(),
        @SerializedName("reviews_count")
        val reviewsCount: Int? = 0,
        @SerializedName("saturated_color")
        val saturatedColor: String? = "",
        @SerializedName("dominant_color")
        val dominantColor: String? = "",
        @SerializedName("short_screenshots")
        val shortScreenshots: List<ShortScreenshot?>? = listOf(),
        @SerializedName("parent_platforms")
        val parentPlatforms: List<ParentPlatform?>? = listOf(),
        @SerializedName("genres")
        val genres: List<Genre?>? = listOf(),
        @SerializedName("community_rating")
        val communityRating: Int? = 0
    ) {
        data class Platform(
            @SerializedName("platform")
            val platform: Platform? = Platform()
        ) {
            data class Platform(
                @SerializedName("id")
                val id: Int? = 0,
                @SerializedName("name")
                val name: String? = "",
                @SerializedName("slug")
                val slug: String? = ""
            )
        }

        data class Rating(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("title")
            val title: String? = "",
            @SerializedName("count")
            val count: Int? = 0,
            @SerializedName("percent")
            val percent: Double? = 0.0
        )

        data class AddedByStatus(
            @SerializedName("yet")
            val yet: Int? = 0,
            @SerializedName("owned")
            val owned: Int? = 0,
            @SerializedName("beaten")
            val beaten: Int? = 0,
            @SerializedName("toplay")
            val toplay: Int? = 0,
            @SerializedName("dropped")
            val dropped: Int? = 0,
            @SerializedName("playing")
            val playing: Int? = 0
        )

        data class Tag(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("slug")
            val slug: String? = "",
            @SerializedName("language")
            val language: String? = "",
            @SerializedName("games_count")
            val gamesCount: Int? = 0,
            @SerializedName("image_background")
            val imageBackground: String? = ""
        )

        data class ShortScreenshot(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("image")
            val image: String? = ""
        )

        data class ParentPlatform(
            @SerializedName("platform")
            val platform: Platform? = Platform()
        ) {
            data class Platform(
                @SerializedName("id")
                val id: Int? = 0,
                @SerializedName("name")
                val name: String? = "",
                @SerializedName("slug")
                val slug: String? = ""
            )
        }

        data class Genre(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("slug")
            val slug: String? = ""
        )
    }
}

fun GameSearchDto.toGameSearchList(): List<GameSearch> {
    return results?.map {
        GameSearch(it?.id ?: -1, it?.name ?: "", it?.backgroundImage ?: "")
    } ?: emptyList()
}