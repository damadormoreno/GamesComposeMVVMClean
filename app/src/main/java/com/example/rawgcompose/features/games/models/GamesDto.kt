package com.example.rawgcompose.features.games.models


import com.google.gson.annotations.SerializedName

data class GamesDto(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val previous: Any? = Any(),
    @SerializedName("results")
    val results: List<GameDto> = listOf(),
    @SerializedName("seo_title")
    val seoTitle: String = "",
    @SerializedName("seo_description")
    val seoDescription: String = "",
    @SerializedName("seo_keywords")
    val seoKeywords: String = "",
    @SerializedName("seo_h1")
    val seoH1: String = "",
    @SerializedName("noindex")
    val noindex: Boolean = false,
    @SerializedName("nofollow")
    val nofollow: Boolean = false,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("filters")
    val filters: Filters = Filters(),
    @SerializedName("nofollow_collections")
    val nofollowCollections: List<String> = listOf()
) {
    data class GameDto(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("slug")
        val slug: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("released")
        val released: Any? = Any(),
        @SerializedName("tba")
        val tba: Boolean = false,
        @SerializedName("background_image")
        val backgroundImage: String = "",
        @SerializedName("rating")
        val rating: Double = 0.0,
        @SerializedName("rating_top")
        val ratingTop: Int = 0,
        @SerializedName("ratings")
        val ratings: List<Any> = listOf(),
        @SerializedName("ratings_count")
        val ratingsCount: Int = 0,
        @SerializedName("reviews_text_count")
        val reviewsTextCount: Int = 0,
        @SerializedName("added")
        val added: Int = 0,
        @SerializedName("added_by_status")
        val addedByStatus: Any? = Any(),
        @SerializedName("metacritic")
        val metacritic: Any? = Any(),
        @SerializedName("playtime")
        val playtime: Int = 0,
        @SerializedName("suggestions_count")
        val suggestionsCount: Int = 0,
        @SerializedName("updated")
        val updated: String = "",
        @SerializedName("user_game")
        val userGame: Any? = Any(),
        @SerializedName("reviews_count")
        val reviewsCount: Int = 0,
        @SerializedName("community_rating")
        val communityRating: Int = 0,
        @SerializedName("saturated_color")
        val saturatedColor: String = "",
        @SerializedName("dominant_color")
        val dominantColor: String = "",
        @SerializedName("platforms")
        val platforms: List<Platform> = listOf(),
        @SerializedName("parent_platforms")
        val parentPlatforms: List<ParentPlatform> = listOf(),
        @SerializedName("genres")
        val genres: List<Genre> = listOf(),
        @SerializedName("stores")
        val stores: List<Store> = listOf(),
        @SerializedName("clip")
        val clip: Any? = Any(),
        @SerializedName("tags")
        val tags: List<Tag> = listOf(),
        @SerializedName("esrb_rating")
        val esrbRating: Any? = Any(),
        @SerializedName("short_screenshots")
        val shortScreenshots: List<ShortScreenshot> = listOf()
    ) {
        data class Platform(
            @SerializedName("platform")
            val platform: Platform = Platform(),
            @SerializedName("released_at")
            val releasedAt: Any? = Any(),
            @SerializedName("requirements_en")
            val requirementsEn: Any? = Any(),
            @SerializedName("requirements_ru")
            val requirementsRu: Any? = Any()
        ) {
            data class Platform(
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("slug")
                val slug: String = "",
                @SerializedName("image")
                val image: Any? = Any(),
                @SerializedName("year_end")
                val yearEnd: Any? = Any(),
                @SerializedName("year_start")
                val yearStart: Any? = Any(),
                @SerializedName("games_count")
                val gamesCount: Int = 0,
                @SerializedName("image_background")
                val imageBackground: String = ""
            )
        }

        data class ParentPlatform(
            @SerializedName("platform")
            val platform: Platform = Platform()
        ) {
            data class Platform(
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("slug")
                val slug: String = ""
            )
        }

        data class Genre(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("name")
            val name: String = "",
            @SerializedName("slug")
            val slug: String = "",
            @SerializedName("games_count")
            val gamesCount: Int = 0,
            @SerializedName("image_background")
            val imageBackground: String = ""
        )

        data class Store(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("store")
            val store: Store = Store()
        ) {
            data class Store(
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("slug")
                val slug: String = "",
                @SerializedName("domain")
                val domain: String = "",
                @SerializedName("games_count")
                val gamesCount: Int = 0,
                @SerializedName("image_background")
                val imageBackground: String = ""
            )
        }

        data class Tag(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("name")
            val name: String = "",
            @SerializedName("slug")
            val slug: String = "",
            @SerializedName("language")
            val language: String = "",
            @SerializedName("games_count")
            val gamesCount: Int = 0,
            @SerializedName("image_background")
            val imageBackground: String = ""
        )

        data class ShortScreenshot(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("image")
            val image: String = ""
        )
    }

    data class Filters(
        @SerializedName("years")
        val years: List<Year> = listOf()
    ) {
        data class Year(
            @SerializedName("from")
            val from: Int = 0,
            @SerializedName("to")
            val to: Int = 0,
            @SerializedName("filter")
            val filter: String = "",
            @SerializedName("decade")
            val decade: Int = 0,
            @SerializedName("years")
            val years: List<Year> = listOf(),
            @SerializedName("nofollow")
            val nofollow: Boolean = false,
            @SerializedName("count")
            val count: Int = 0
        ) {
            data class Year(
                @SerializedName("year")
                val year: Int = 0,
                @SerializedName("count")
                val count: Int = 0,
                @SerializedName("nofollow")
                val nofollow: Boolean = false
            )
        }
    }
}

fun GamesDto.toGames() : Games{
    return Games(
        next = next,
        previous = previous,
        games = results.map {
            Games.Game(it.id, it.name, it.backgroundImage)
        }
    )
}