package jakub.jedrecki.midnite.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Outcome<out T, out U>(val status: Status, val data: T?, val error: U?) {
    companion object {
        fun <T, Nothing> success(data: T?): Outcome<T, Nothing> {
            return Outcome(Status.SUCCESS, data, null)
        }

        fun <Nothing, U> error(error: U?): Outcome<Nothing, U> {
            return Outcome(Status.ERROR, null, error)
        }
    }
}