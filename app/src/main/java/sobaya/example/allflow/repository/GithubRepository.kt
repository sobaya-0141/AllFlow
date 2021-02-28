package sobaya.example.allflow.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubRepository(private val service: GithubService) {
    fun getRepo(user: String) = flow {
        emit(service.listRepos(user))
    }.catch { print(it.message) }.flowOn(Dispatchers.IO)
}
