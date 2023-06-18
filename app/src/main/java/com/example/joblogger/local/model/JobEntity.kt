package com.example.joblogger.local.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val companyName: String
)
object JobDiffUtil : DiffUtil.ItemCallback<JobEntity>() {
    override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem == newItem

}

