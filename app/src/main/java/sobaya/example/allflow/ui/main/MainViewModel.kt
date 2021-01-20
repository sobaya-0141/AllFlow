package sobaya.example.allflow.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    val userName = MutableLiveData("")
    private val isCompleteUserName = userName.asFlow().debounce(1000).map {
        check(it.length > 3)
        true
    }.catch {
        emit(false)
    }.onEach {
        it
    }

    val subText = MutableLiveData("")
    private val isCompleteSubText = subText.asFlow().map {
        check(it.length > 3)
        true
    }.catch {
        emit(false)
    }.onEach {
        it
    }

    val checkComplete = combine(isCompleteUserName, isCompleteSubText) { userName, subTitle ->
        userName && subTitle
    }

    val request = checkComplete.flatMapLatest {
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