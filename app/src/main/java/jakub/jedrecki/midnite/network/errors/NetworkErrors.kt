package jakub.jedrecki.midnite.network.errors

sealed class NetworkErrors {
    class UNKNOWN_HOST_ERROR(): NetworkErrors()
}