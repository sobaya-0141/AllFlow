package sobaya.example.allflow.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    val userName = MutableLiveData("")
    private val isCompleteUserName = userName.asFlow().map {
        it.length > 3
    }

    val subText = MutableLiveData("")
    private val isCompleteSubText = subText.asFlow().map {
        it.length > 3
    }

    private val checkComplete = combine(isCompleteUserName, isCompleteSubText) { userName, subTitle ->
        userName && subTitle
    }

    private val request = repository.getRepo("sobaya-0141")
    private val res = request.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    init {
        res.onEach {
            it?.body()?.forEach {
                print(it)
            }
        }.launchIn(viewModelScope)
    }

    fun getRepos() {
        res.onEach {
            it?.body()?.forEach {
                print(it)
            }
        }.launchIn(viewModelScope)
    }
}
