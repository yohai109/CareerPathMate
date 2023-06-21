package com.example.joblogger.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joblogger.uimodels.JobUiModel
import com.example.joblogger.uimodels.JobUiStatus
import java.util.UUID

@Entity(tableName = "jobs")
data class JobEntity @JvmOverloads constructor(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val companyName: String,
    val contactName: String,
    val description:String,
    val status: JobStatus = JobStatus.OnGoing
) {
    constructor(jobUiModel: JobUiModel) : this(
        id = jobUiModel.id,
        companyName = jobUiModel.companyName,
        contactName = jobUiModel.contactName,
        description = jobUiModel.description,
        status = JobStatus.fromJobUiStatus(jobUiModel.status)
    )
}

enum class JobStatus {
    OnGoing,
    Old,
    Yes,
    No;

    companion object {
        fun fromJobUiStatus(status: JobUiStatus) = when (status) {
            JobUiStatus.OnGoing -> OnGoing
            JobUiStatus.Old -> Old
            JobUiStatus.Yes -> Yes
            JobUiStatus.No -> No
        }
    }
}