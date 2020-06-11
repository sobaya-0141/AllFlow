package sobaya.example.allflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    fun getRepo(user: String) {
        viewModelScope.launch {
            val res = repository.getRepo(user)
            if (res.isSuccessful) {
                res.body()?.forEach {
                    println(it.name)
                }
            }
        }
    }
}