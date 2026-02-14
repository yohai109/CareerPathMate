package com.yohai.careerpathmate.uimodels

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yohai.careerpathmate.R
import com.yohai.careerpathmate.local.model.JobStepEntity
import com.yohai.careerpathmate.local.model.StepLocation
import com.yohai.careerpathmate.local.model.StepStatus
import java.util.Calendar
import java.util.UUID

data class JobStepUiModel(
    val id: String = UUID.randomUUID().toString(),
    val date: Calendar,
    val name: String,
    val status: StepStatusUi,
    val location: StepLocationUi,
    val jobId: String
) {
    constructor(entity: JobStepEntity) : this(
        id = entity.id,
        date = entity.date,
        name = entity.name,
        status = StepStatusUi.fromStepStatus(entity.status),
        location = StepLocationUi.fromStepLocation(entity.location),
        jobId = entity.jobId
    )
}

enum class StepStatusUi(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @ColorRes val indicatorColor:Int
) {
    Current(R.string.step_status_current, R.drawable.ic_ongoing, R.color.lightBlue500),
    Done(R.string.step_status_done, R.drawable.ic_checkmark_green, R.color.green600),
    Future(R.string.step_status_future, R.drawable.ic_future,R.color.amber600);

    companion object {
        fun fromStepStatus(status: StepStatus) = when (status) {
            StepStatus.Done -> Done
            StepStatus.Current -> Current
            StepStatus.Future -> Future
        }
    }
}

enum class StepLocationUi(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    OnSite(R.string.step_location_onsite, R.drawable.ic_onsite),
    Remote(R.string.step_location_remote, R.drawable.ic_remote);

    companion object {
        fun fromStepLocation(location: StepLocation) = when (location) {
            StepLocation.OnSite -> OnSite
            StepLocation.Remote -> Remote
        }
    }
}
