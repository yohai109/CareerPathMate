package com.yohai.careerpathmate.screens.jobdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yohai.careerpathmate.local.model.JobStepEntity
import com.yohai.careerpathmate.local.model.StepLocation
import com.yohai.careerpathmate.local.model.StepStatus
import com.yohai.careerpathmate.screens.createstep.CreateStepRepo
import com.yohai.careerpathmate.uimodels.JobStepUiModel
import com.yohai.careerpathmate.uimodels.JobUiModel
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

    fun steps() = repo.steps(jobId).map { stepsList ->
        stepsList.map { step ->
            JobStepUiModel(step)
        }
    }

    fun createStep() {
        viewModelScope.launch(Dispatchers.IO) {
            createStepRepo.createStep(
                JobStepEntity(
                    date = Calendar.getInstance(),
                    jobId = jobId,
                    status = StepStatus.Current,
                    name = DEFAULT_STEP_NAME,
                    location = StepLocation.Remote
                )
            )
        }
    }

    companion object {
        private const val DEFAULT_STEP_NAME = "auto created"
    }
}