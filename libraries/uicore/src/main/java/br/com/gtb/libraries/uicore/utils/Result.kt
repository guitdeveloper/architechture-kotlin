package br.com.gtb.libraries.uicore.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T? = null) : Result<T>()
    data class Error<out T : Any>(val exception: Throwable, val data: T? = null) : Result<T>()
    object InProgress : Result<Nothing>()
    object None : Result<Nothing>()
}

fun responseError(code: Int) =
    Result.Error(
        Exception(
            code.codeResponseToString()
        ), null
    )

private const val NOT_AUTHORIZED: Int = 401
private const val FORBIDDEN: Int = 403
private const val NOT_FOUND: Int = 404

fun Int.codeResponseToString(): String {
    return when (this) {
        NOT_AUTHORIZED -> "Senha incorreta"
        FORBIDDEN -> "Usuário sem permissão para a operação"
        NOT_FOUND -> "Usuário não cadastrado"
        else -> "Ocorreu um erro ao executar a operação"
    }
}