package com.yohai.careerpathmate.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.yohai.careerpathmate.uimodels.JobStepUiModel
import com.yohai.careerpathmate.uimodels.StepLocationUi
import com.yohai.careerpathmate.uimodels.StepStatusUi
import java.util.Calendar
import java.util.UUID

@Entity(
    tableName = "JobSteps",
    foreignKeys = [
        ForeignKey(
            entity = JobEntity::class,
            parentColumns = ["id"],
            childColumns = ["jobId"],
            onDelete = CASCADE
        )
    ]
)
data class JobStepEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val date: Calendar,
    val name: String,
    val status: StepStatus,
    val location: StepLocation,
    val jobId: String
) {
    constructor(uiModel: JobStepUiModel) : this(
        id = uiModel.id,
        date = uiModel.date,
        name = uiModel.name,
        status = StepStatus.fromStepStatusUi(uiModel.status),
        location = StepLocation.fromStepLocationUi(uiModel.location),
        jobId = uiModel.jobId
    )
}

enum class StepLocation {
    OnSite,
    Remote;

    companion object {
        fun fromStepLocationUi(location: StepLocationUi) = when (location) {
            StepLocationUi.OnSite -> OnSite
            StepLocationUi.Remote -> Remote
        }
    }
}

enum class StepStatus {
    Done,
    Current,
    Future;

    companion object {
        fun fromStepStatusUi(status: StepStatusUi) = when (status) {
            StepStatusUi.Done -> Done
            StepStatusUi.Current -> Current
            StepStatusUi.Future -> Future
        }
    }
}
