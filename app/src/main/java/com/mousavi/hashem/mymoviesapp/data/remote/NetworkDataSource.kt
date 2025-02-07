package com.mousavi.hashem.mymoviesapp.data.remote

import com.mousavi.hashem.common.Either
import com.mousavi.hashem.mymoviesapp.data.remote.dto.GenresDto
import com.mousavi.hashem.mymoviesapp.data.remote.dto.PageDataDto
import com.mousavi.hashem.mymoviesapp.data.remote.dto.ReviewsDto

interface NetworkDataSource {

    suspend fun getPopularMovies(
        language: String,
        page: Int,
    ): Either<PageDataDto, String>

    suspend fun getGenres(): Either<GenresDto, String>

    suspend fun getReviews(
        movieId: Int,
        language: String,
        page: Int,
    ): Either<ReviewsDto, String>
}