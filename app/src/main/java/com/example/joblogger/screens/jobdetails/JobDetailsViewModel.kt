package com.example.joblogger.screens.jobdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.model.JobStepEntity
import com.example.joblogger.local.model.StepLocation
import com.example.joblogger.local.model.StepStatus
import com.example.joblogger.screens.createstep.CreateStepRepo
import com.example.joblogger.uimodels.JobStepUiModel
import com.example.joblogger.uimodels.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repo: JobDetailsRepo,
    private val createStepRepo: CreateStepRepo
) : ViewModel() {

    var jobId: String = ""

    fun currJob() = repo.currJob(jobId).map {
        JobUiModel(it)
    }

    fun steps() = repo.steps(jobId).map {
        it.map {
            JobStepUiModel(it)
        }
    }

    fun createStep() {
        viewModelScope.launch(Dispatchers.IO) {
            createStepRepo.createStep(
                JobStepEntity(
                    date = Calendar.getInstance(),
                    jobId = jobId,
                    status = StepStatus.Current,
                    name = "auto created",
                    location = StepLocation.Remote
                )
            )
        }
    }
}