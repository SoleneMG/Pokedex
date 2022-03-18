package com.invadercorps.poketest.data.repository.model


sealed class StatefulData<T> {
    data class Success<T>(val result : T) : StatefulData<T>()
    class Empty<T> : StatefulData<T>()
    class Loading<T> : StatefulData<T>()
    class Error<T> : StatefulData<T>()
}
