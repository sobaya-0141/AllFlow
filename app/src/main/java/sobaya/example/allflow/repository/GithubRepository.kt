package sobaya.example.allflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GithubRepository(private val service: GithubService) {
     fun getRepo(user: LiveData<String>) =
        flow {
            user.asFlow().collect { it ->
                emit(service.listRepos(it))
            }
        }
}
