package com.example.careerpathmate.screens.jobdetails.steplongclickdialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerpathmate.screens.jobdetails.JobDetailsRepo
import com.example.careerpathmate.uimodels.StepStatusUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepLongClickViewModel @Inject constructor(private val repo: JobDetailsRepo) : ViewModel() {
    var stepId: String = ""

    fun updateStatus(newStatus: StepStatusUi) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setStepStatus(stepId, newStatus)
        }
    }

    fun deleteStep() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteStep(stepId)
        }
    }
}