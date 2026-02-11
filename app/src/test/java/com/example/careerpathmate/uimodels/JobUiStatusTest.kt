package com.example.careerpathmate.uimodels

import com.example.careerpathmate.local.model.JobStatus
import org.junit.Assert.*
import org.junit.Test

class JobUiStatusTest {
    
    @Test
    fun fromJobStatus_onGoing_returnsOnGoing() {
        // Act
        val result = JobUiStatus.fromJobStatus(JobStatus.OnGoing)
        
        // Assert
        assertEquals(JobUiStatus.OnGoing, result)
    }
    
    @Test
    fun fromJobStatus_old_returnsOld() {
        // Act
        val result = JobUiStatus.fromJobStatus(JobStatus.Old)
        
        // Assert
        assertEquals(JobUiStatus.Old, result)
    }
    
    @Test
    fun fromJobStatus_yes_returnsYes() {
        // Act
        val result = JobUiStatus.fromJobStatus(JobStatus.Yes)
        
        // Assert
        assertEquals(JobUiStatus.Yes, result)
    }
    
    @Test
    fun fromJobStatus_no_returnsNo() {
        // Act
        val result = JobUiStatus.fromJobStatus(JobStatus.No)
        
        // Assert
        assertEquals(JobUiStatus.No, result)
    }
    
    @Test
    fun allJobStatuses_haveMappings() {
        // Assert all enum values can be converted
        JobStatus.values().forEach { status ->
            val converted = JobUiStatus.fromJobStatus(status)
            assertNotNull(converted)
        }
    }
}
