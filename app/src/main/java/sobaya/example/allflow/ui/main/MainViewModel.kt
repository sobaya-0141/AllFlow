package sobaya.example.allflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    val _user = MutableStateFlow("")
    private val user: Flow<String> = _user
    private val resFlow = user.flatMapLatest {
        repository.getRepo(it)
    }

    init {
        viewModelScope.launch {
            resFlow.collect { res ->
                if (res.isSuccessful) {
                    res.body()?.forEach {
                        println(it.name)
                    }
                }
            }
        }
    }
}
