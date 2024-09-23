package soft.divan.test_factory_hothouse.domain.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
// implementation 'ru.gildor.coroutines:kotlin-coroutines-retrofit:1.1.0'
//https://github.com/gildor/kotlin-coroutines-retrofit
//https://habr.com/ru/articles/427475/
sealed class Rezult<out T : Any?> {

    data class Success<out T : Any?>(val data: T) : Rezult<T>()

    data class Error(val exception: Exzeption) : Rezult<Nothing>() {
        constructor(reason: Reazon) : this(Exzeption(reason = reason))
        constructor(wrapped: Throwable) : this(Exzeption(wrapped = wrapped))
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
