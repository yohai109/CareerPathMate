package com.yohai.careerpathmate.local.model

import com.yohai.careerpathmate.uimodels.JobUiStatus
import org.junit.Assert.*
import org.junit.Test

class JobStatusTest {
    
    @Test
    fun fromJobUiStatus_onGoing_returnsOnGoing() {
        // Act
        val result = JobStatus.fromJobUiStatus(JobUiStatus.OnGoing)
        
        // Assert
        assertEquals(JobStatus.OnGoing, result)
    }
    
    @Test
    fun fromJobUiStatus_old_returnsOld() {
        // Act
        val result = JobStatus.fromJobUiStatus(JobUiStatus.Old)
        
        // Assert
        assertEquals(JobStatus.Old, result)
    }
    
    @Test
    fun fromJobUiStatus_yes_returnsYes() {
        // Act
        val result = JobStatus.fromJobUiStatus(JobUiStatus.Yes)
        
        // Assert
        assertEquals(JobStatus.Yes, result)
    }
    
    @Test
    fun fromJobUiStatus_no_returnsNo() {
        // Act
        val result = JobStatus.fromJobUiStatus(JobUiStatus.No)
        
        // Assert
        assertEquals(JobStatus.No, result)
    }
    
    @Test
    fun allJobStatuses_haveMappings() {
        // Assert all enum values can be converted
        JobUiStatus.values().forEach { uiStatus ->
            val converted = JobStatus.fromJobUiStatus(uiStatus)
            assertNotNull(converted)
        }
    }
}
