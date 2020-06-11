package sobaya.example.allflow.repository

import kotlinx.coroutines.flow.flow

class GithubRepository(private val service: GithubService) {
    fun getRepo(user: String) = flow {
        emit(service.listRepos(user))
    }
}