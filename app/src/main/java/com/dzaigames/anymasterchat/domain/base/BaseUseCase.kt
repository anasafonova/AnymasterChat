package com.dzaigames.anymasterchat.domain.base

abstract class BaseUseCase<T, in Params> {

    suspend operator fun invoke(params: Params): Result<T> {
        return try {
            val result = doUseCase(params)
            Result.success(result)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    protected abstract suspend fun doUseCase(params: Params): T
}