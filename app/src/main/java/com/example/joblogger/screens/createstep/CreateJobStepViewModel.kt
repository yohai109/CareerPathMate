package com.example.joblogger.screens.createstep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogger.local.model.JobStepEntity
import com.example.joblogger.uimodels.JobStepUiModel
import com.example.joblogger.uimodels.StepLocationUi
import com.example.joblogger.uimodels.StepStatusUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateJobStepViewModel @Inject constructor(
    private val createStepRepo: CreateStepRepo
) : ViewModel() {
    var newStep = JobStepUiModel(
        date = Calendar.getInstance(),
        name = "",
        status = StepStatusUi.Current,
        location = StepLocationUi.Remote,
        jobId = ""
    )

    fun setStepDate(cal: Calendar) {
        newStep = newStep.copy(
            date = cal
        )
    }

    fun setJobId(id: String) {
        newStep = newStep.copy(
            jobId = id
        )
    }

    fun saveStep() {
        viewModelScope.launch(Dispatchers.IO) {
            createStepRepo.createStep(
                JobStepEntity(newStep)
            )
        }
    }
}