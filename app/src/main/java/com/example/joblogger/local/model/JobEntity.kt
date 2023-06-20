package com.example.joblogger.local.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joblogger.uimodels.JobUiModel
import com.example.joblogger.uimodels.JobUiStatus
import java.util.UUID

@Entity(tableName = "jobs")
data class JobEntity @JvmOverloads constructor(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val companyName: String,
    val status: JobStatus = JobStatus.OnGoing
) {
    constructor(jobUiModel: JobUiModel) : this(
        id = jobUiModel.id,
        companyName = jobUiModel.companyName,
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

object JobDiffUtil : DiffUtil.ItemCallback<JobEntity>() {
    override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem == newItem

}

