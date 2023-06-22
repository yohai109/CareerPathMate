package com.example.joblogger.uimodels

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.joblogger.R
import com.example.joblogger.local.model.JobEntity
import com.example.joblogger.local.model.JobLocation
import com.example.joblogger.local.model.JobStatus
import java.util.UUID

data class JobUiModel @JvmOverloads constructor(
    val id: String = UUID.randomUUID().toString(),
    val companyName: String,
    val contactName: String,
    val description: String,
    val status: JobUiStatus = JobUiStatus.OnGoing,
    val location: JobLocationUi = JobLocationUi.Hybrid
) {
    constructor(jobEntity: JobEntity) : this(
        id = jobEntity.id,
        companyName = jobEntity.companyName,
        contactName = jobEntity.contactName,
        description = jobEntity.description,
        status = JobUiStatus.fromJobStatus(jobEntity.status),
        location = JobLocationUi.fromJobLocation(jobEntity.location)
    )
}

enum class JobLocationUi(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    OnSite(R.string.job_location_onsite, R.drawable.ic_onsite),
    Remote(R.string.job_location_remote, R.drawable.ic_remote),
    Hybrid(R.string.job_location_hybrid, R.drawable.ic_hybrid);

    companion object {
        fun fromJobLocation(location: JobLocation) = when (location) {
            JobLocation.OnSite -> OnSite
            JobLocation.Remote -> Remote
            JobLocation.Hybrid -> Hybrid
        }
    }
}

enum class JobUiStatus(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @ColorRes val indicatorColor: Int
) {
    OnGoing(R.string.status_ongoing, R.drawable.ic_ongoing, R.color.lightBlue500),
    Old(R.string.job_status_old, R.drawable.ic_old, R.color.amber600),
    Yes(R.string.job_status_yes, R.drawable.ic_checkmark_green, R.color.green600),
    No(R.string.job_status_no, R.drawable.ic_red_x, R.color.red700);

    companion object {
        fun fromJobStatus(status: JobStatus) = when (status) {
            JobStatus.OnGoing -> OnGoing
            JobStatus.Old -> Old
            JobStatus.Yes -> Yes
            JobStatus.No -> No
        }
    }
}
