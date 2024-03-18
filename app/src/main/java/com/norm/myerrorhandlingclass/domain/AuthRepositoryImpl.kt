package com.norm.myerrorhandlingclass.domain

import retrofit2.HttpException

class AuthRepositoryImpl : AuthRepository {
    override suspend fun register(password: String): Result<User, DataError.Network> {
        //API call logic
        return try {
            val user = User(
                "Sergey Bozborodov",
                "123345567789",
                "serega.b301@gmail.com",
            )
            Result.Success(user)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}