package jakub.jedrecki.midnite.ui.matchDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakub.jedrecki.midnite.R
import jakub.jedrecki.midnite.domain.model.MatchDetail
import jakub.jedrecki.midnite.network.errors.NetworkErrors
import jakub.jedrecki.midnite.network.errors.NetworkErrors.UNKNOWN_HOST_ERROR
import jakub.jedrecki.midnite.repository.MatchesRepository
import jakub.jedrecki.midnite.util.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val repository: MatchesRepository
) : ViewModel() {

    private val _matchDetails: MutableLiveData<MatchDetail> = MutableLiveData()
    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()

    val matchDetails: LiveData<MatchDetail> = _matchDetails
    val errorMessage: LiveData<Int> = _errorMessage

    fun getMatchDetail(matchId: Int) {
        viewModelScope.launch(IO) {
            val result = repository.getMatchDetails(matchId)

            when(result.status) {
                Status.SUCCESS -> {
                    _matchDetails.postValue(result.data!!)
                }
                Status.ERROR -> {
                    when(result.error) {
                        is UNKNOWN_HOST_ERROR -> {
                            _errorMessage.postValue(R.string.error_host_network)
                        }
                        else -> {
                            _errorMessage.postValue(R.string.error_smth_went_wrong)
                        }
                    }
                    //todo handle error / display message/action to user ?
                }
            }
        }
    }
}