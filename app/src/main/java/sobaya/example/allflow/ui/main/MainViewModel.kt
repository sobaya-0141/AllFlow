package sobaya.example.allflow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    val _user = MutableLiveData<String>()
    private val user: LiveData<String> = _user
    private val resFlow = repository.getRepo(user)

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