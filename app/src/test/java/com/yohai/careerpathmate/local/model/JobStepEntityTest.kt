package com.yohai.careerpathmate.local.model

import org.junit.Assert.*
import org.junit.Test
import java.util.Calendar

class JobStepEntityTest {
    
    @Test
    fun jobStepEntity_defaultConstructor_generatesUniqueId() {
        // Arrange
        val date = Calendar.getInstance()
        val jobId = "job-123"
        
        // Act
        val step1 = JobStepEntity(
            date = date,
            name = "Interview",
            status = StepStatus.Current,
            location = StepLocation.OnSite,
            jobId = jobId
        )
        val step2 = JobStepEntity(
            date = date,
            name = "Interview",
            status = StepStatus.Current,
            location = StepLocation.OnSite,
            jobId = jobId
        )
        
        // Assert
        assertNotEquals(step1.id, step2.id)
    }
    
    @Test
    fun jobStepEntity_allFields_setCorrectly() {
        // Arrange
        val id = "step-id-123"
        val date = Calendar.getInstance()
        date.set(2024, Calendar.MARCH, 15, 10, 0, 0)
        val name = "Technical Interview"
        val status = StepStatus.Done
        val location = StepLocation.Remote
        val jobId = "job-456"
        
        // Act
        val step = JobStepEntity(
            id = id,
            date = date,
            name = name,
            status = status,
            location = location,
            jobId = jobId
        )
        
        // Assert
        assertEquals(id, step.id)
        assertEquals(date, step.date)
        assertEquals(name, step.name)
        assertEquals(status, step.status)
        assertEquals(location, step.location)
        assertEquals(jobId, step.jobId)
    }
}
