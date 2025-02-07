package com.mousavi.hashem.mymoviesapp.data.remote

import com.mousavi.hashem.common.Either
import com.mousavi.hashem.mymoviesapp.R
import com.mousavi.hashem.mymoviesapp.data.remote.dto.GenresDto
import com.mousavi.hashem.mymoviesapp.data.remote.dto.PageDataDto
import com.mousavi.hashem.mymoviesapp.data.remote.dto.ReviewsDto
import retrofit2.HttpException
import java.io.IOException

class NetworkDataSourceImpl(
    private val api: Api,
    private var stringProvider: StringProvider
) : NetworkDataSource {

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
    ): Either<PageDataDto, String> {
        return try {
            val popularMovies = api.getPopularMovies(language, page)
            Either.Success(popularMovies)
        } catch (e: HttpException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.error_occurred))
        } catch (e: IOException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.check_internet_connection))
        } catch (e: Exception) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun getGenres(): Either<GenresDto, String> {
        return try {
            val genresDto = api.getGenres()
            Either.Success(genresDto)
        } catch (e: HttpException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.error_occurred))
        } catch (e: IOException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.check_internet_connection))
        } catch (e: Exception) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun getReviews(
        movieId: Int,
        language: String,
        page: Int,
    ): Either<ReviewsDto, String> {
        return try {
            val reviewsDto = api.getReviews(
                movieId,
                language,
                page
            )
            Either.Success(reviewsDto)
        } catch (e: HttpException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.error_occurred))
        } catch (e: IOException) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.check_internet_connection))
        } catch (e: Exception) {
            Either.Error(error = e.message ?: stringProvider.getString(R.string.unknown_error))
        }
    }
}