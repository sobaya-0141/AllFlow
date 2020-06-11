package sobaya.example.allflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    fun getRepo(user: String) {
        viewModelScope.launch {
            repository.getRepo(user)
                .catch {}
                .first { res ->
                    if (res.isSuccessful) {
                        res.body()?.forEach {
                            println(it.name)
                        }
                    }
                    true
                }
        }
    }
}