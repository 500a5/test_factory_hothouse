package soft.divan.test_factory_hothouse.domain.utils


class Exzeption(
    val reason: Reazon? = Reazon.UNKNOWN_ERROR,
    val wrapped: Throwable? = null,
    val data: Any? = null
) : Exception(wrapped)
