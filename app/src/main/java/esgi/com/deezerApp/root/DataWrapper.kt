package esgi.com.deezerApp.root

import androidx.lifecycle.MutableLiveData
import esgi.com.deezerApp.data.model.DeezerError

sealed class DataWrapper<T>

data class Loading<T>(val loading: Boolean) : DataWrapper<T>()
data class Success<T>(val data: T) : DataWrapper<T>()
data class Failure<T>(val throwable: Throwable) : DataWrapper<T>()
data class SuccessWithError<T>(val error: DeezerError) : DataWrapper<T>()

fun <T> MutableLiveData<DataWrapper<T>>.setLoadingState(loading: Boolean) {
    this.value = Loading(loading)
}