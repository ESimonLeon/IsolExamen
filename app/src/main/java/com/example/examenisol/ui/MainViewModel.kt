package com.example.examenisol.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenisol.retrofit.helper.ServiceResult
import com.example.examenisol.retrofit.repo.RepositoryList
import com.example.examenisol.utils.loadErrorConnection
import com.example.examenisol.utils.loadErrorTimeoutConnection
import com.example.examenisol.utils.loadErrorUnexpected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repositoryList: RepositoryList) : ViewModel() {

    private val _serviceResult: MutableLiveData<ServiceResult> = MutableLiveData()
    val serviceResult: LiveData<ServiceResult> = _serviceResult

    fun getSearchProducts(page: Int, querySearch: String?, sortPrice: Int?) =
        viewModelScope.launch {

            _serviceResult.value = ServiceResult.ProgressBar

            withContext(IO) {

                try {
                    repositoryList.getProductsListRepo(page, querySearch, sortPrice)
                } catch (e: Exception) {
                    when {
                        (e is java.net.UnknownHostException) -> loadErrorConnection()
                        (e is java.net.SocketTimeoutException || e is javax.net.ssl.SSLException) -> loadErrorTimeoutConnection()
                        else -> loadErrorUnexpected()
                    }
                }

            }.let {
                _serviceResult.value = it
            }

        }

}

