package soft.divan.test_factory_hothouse.domain.utils


import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any?> safeResult(
    ctx: CoroutineContext = IO,
    call: suspend () -> T
): Rezult<T> = try {
    val data = withContext(ctx) { call.invoke() }
    Rezult.Success(data)
} catch (e: SocketTimeoutException) {
    // log.e(e = e)
    Rezult.Error(Exzeption(Reazon.NETWORK_ERROR, e))
} catch (e: ConnectException) {
    // log.e(e = e)
    Rezult.Error(Exzeption(Reazon.NETWORK_ERROR, e))
} catch (e: Exception) {
    //log.e(e = e)
    Rezult.Error(Exzeption(Reazon.UNKNOWN_ERROR, e))
} catch (e: Throwable) {
    // log.e(e = e)
    Rezult.Error(Exzeption(Reazon.INTERNAL_ERROR, e))
}

suspend fun <T : Any?> safeHttpResult(
    ctx: CoroutineContext = IO,
    call: suspend () -> Response<T>
): Rezult<T> = try {
    val rsp = withContext(ctx) { call.invoke() }
    if (rsp.isSuccessful) {
        rsp.body()
            ?.let { Rezult.Success(it) }
            ?: Rezult.Error(Reazon.NULL_DATA)
    } else {
        val body = runCatching { rsp.errorBody().toString() }
        if (body.isSuccess) {
            if (rsp.code() == 401)
                Rezult.Error(Exzeption(Reazon.BAD_AUTH, data = body.getOrNull()))
            else
                Rezult.Error(Exzeption(Reazon.WITH_ERROR_MESSAGE, data = body.getOrNull()))
        } else {
            Rezult.Error(Exzeption(Reazon.SERVER_ERROR))
        }
    }
} catch (e: UnknownHostException) {
    //log.e(e = e)
    Rezult.Error(Exzeption(Reazon.NETWORK_ERROR, e))
} catch (e: SocketTimeoutException) {
    //  log.e(e = e)
    Rezult.Error(Exzeption(Reazon.NETWORK_ERROR, e))
} catch (e: ConnectException) {
    //  log.e(e = e)
    Rezult.Error(Exzeption(Reazon.NETWORK_ERROR, e))
} catch (e: Exception) {
    // log.e(e = e)
    Rezult.Error(Exzeption(Reazon.UNKNOWN_ERROR, e))
} catch (e: Throwable) {
    //  log.e(e = e)
    Rezult.Error(Exzeption(Reazon.INTERNAL_ERROR, e))
}
