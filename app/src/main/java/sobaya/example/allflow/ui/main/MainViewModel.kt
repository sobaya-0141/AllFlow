package sobaya.example.allflow.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    private val request = checkComplete.flatMapLatest {
        repository.getRepo(userName.value!!)
    }

    init {
        request.onEach {
            it.body()?.forEach {
                print(it)
            }
        }.launchIn(viewModelScope)
    }
}