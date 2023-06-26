package com.example.joblogger.screens.jobslist

import androidx.lifecycle.ViewModel
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.reflect.KProperty

@HiltViewModel
class JobListViewModel @Inject constructor(private val repo: JobListRepo) : ViewModel() {
    val filtersFlow: MutableStateFlow<HashMap<KProperty<*>, (JobUiModel) -> Boolean>> =
        MutableStateFlow(hashMapOf())

    @OptIn(ExperimentalCoroutinesApi::class)
    val jobs = filtersFlow.flatMapLatest { filters ->
        repo.allJobs.map {
            it.filter { currJob ->
                filters.isEmpty() || filters.any {
                    it.value.invoke(currJob)
                }
            }
        }
    }

    fun setSearchTerm(searchTerm: String) {
        filtersFlow.update {
            HashMap(it).also { map ->
                map[JobUiModel::companyName] = { currJob ->
                    currJob.companyName
                        .lowercase()
                        .contains(searchTerm.lowercase())
                }
            }
        }
    }
}