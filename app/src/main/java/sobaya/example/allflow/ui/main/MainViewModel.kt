package sobaya.example.allflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import sobaya.example.allflow.repository.GithubRepository

class MainViewModel(private val repository: GithubRepository) : ViewModel() {

    private val repo = repository.getRepo("sobaya-0141")

    init {
        repo.onEach {
            it.body()
        }.launchIn(viewModelScope)
    }

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
