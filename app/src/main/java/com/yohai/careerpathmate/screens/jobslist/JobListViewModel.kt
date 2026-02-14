package com.yohai.careerpathmate.screens.jobslist

import androidx.lifecycle.ViewModel
import com.yohai.careerpathmate.uimodels.JobLocationUi
import com.yohai.careerpathmate.uimodels.JobUiModel
import com.yohai.careerpathmate.uimodels.JobUiStatus
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
    private val filtersFlow: MutableStateFlow<HashMap<KProperty<*>, (JobUiModel) -> Boolean>> =
        MutableStateFlow(hashMapOf())

    private val statusFilter: ArrayList<JobUiStatus> = arrayListOf()
    private val locationFilter: ArrayList<JobLocationUi> = arrayListOf()

    fun isFiltered(status: JobUiStatus) = status in statusFilter
    fun isFiltered(location: JobLocationUi) = location in locationFilter

    @OptIn(ExperimentalCoroutinesApi::class)
    val jobs = filtersFlow.flatMapLatest { filters ->
        repo.allJobs.map {
            it.filter { currJob ->
                filters.isEmpty() || filters.all {
                    it.value.invoke(currJob)
                }
            }
        }
    }

    fun toggleStatusFilter(status: JobUiStatus) {
        if (isFiltered(status)) {
            statusFilter.remove(status)
        } else {
            statusFilter.add(status)
        }
        filtersFlow.update {
            HashMap(it).also { hashMap ->
                hashMap[JobUiModel::status] = { currJob ->
                    statusFilter.isEmpty() || statusFilter.contains(currJob.status)
                }
            }
        }
    }

    fun toggleLocationFilter(location: JobLocationUi) {
        if (isFiltered(location)) {
            locationFilter.remove(location)
        } else {
            locationFilter.add(location)
        }
        filtersFlow.update {
            HashMap(it).also { hashMap ->
                hashMap[JobUiModel::location] = { currJob ->
                    locationFilter.isEmpty() || locationFilter.contains(currJob.location)
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