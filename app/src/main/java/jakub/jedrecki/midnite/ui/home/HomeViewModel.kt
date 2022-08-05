package jakub.jedrecki.midnite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakub.jedrecki.midnite.R
import jakub.jedrecki.midnite.domain.model.Matches
import jakub.jedrecki.midnite.network.errors.NetworkErrors
import jakub.jedrecki.midnite.repository.MatchesRepository
import jakub.jedrecki.midnite.util.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MatchesRepository
): ViewModel() {

    private val _upcomingMatches: MutableLiveData<Matches> = MutableLiveData()
    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()

    val upcomingMatches: LiveData<Matches> = _upcomingMatches
    val errorMessage: LiveData<Int> = _errorMessage

    fun getUpcomingMatches() {
        viewModelScope.launch(IO) {
            val result = repository.getUpcomingMatches()

            when(result.status) {
                Status.SUCCESS -> {
                    _upcomingMatches.postValue(result.data!!)
                }
                Status.ERROR -> {
                    when(result.error) {
                        is NetworkErrors.UNKNOWN_HOST_ERROR -> {
                            _errorMessage.postValue(R.string.error_host_network)
                        }
                        else -> {
                            _errorMessage.postValue(R.string.error_smth_went_wrong) // different message
                        }
                    }
                    //todo handle error / display message/action to user ?
                }
            }
        }
    }
}