package com.example.joblogger.uimodels

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.joblogger.R
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.local.model.JobStatus
import java.util.UUID

data class JobUiModel @JvmOverloads constructor(
    val id: String = UUID.randomUUID().toString(),
    val companyName: String,
    val contactName: String,
    val description: String,
    val status: JobUiStatus = JobUiStatus.OnGoing
) {
    constructor(jobEntity: JobEntity) : this(
        id = jobEntity.id,
        companyName = jobEntity.companyName,
        contactName = jobEntity.contactName,
        description = jobEntity.description,
        status = JobUiStatus.fromJobStatus(jobEntity.status)
    )
}

enum class JobUiStatus(
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    OnGoing(R.string.status_ongoing, R.drawable.ic_ongoing),
    Old(R.string.status_old, R.drawable.ic_old),
    Yes(R.string.status_yes, R.drawable.ic_checkmark_green),
    No(R.string.status_no, R.drawable.ic_red_x);

    companion object {
        fun fromJobStatus(status: JobStatus) = when (status) {
            JobStatus.OnGoing -> OnGoing
            JobStatus.Old -> Old
            JobStatus.Yes -> Yes
            JobStatus.No -> No
        }
    }
}
