package com.example.careerpathmate.uimodels

import com.example.careerpathmate.local.model.JobLocation
import com.example.careerpathmate.local.model.JobStatus
import org.junit.Assert.*
import org.junit.Test

class JobLocationUiTest {
    
    @Test
    fun fromJobLocation_onSite_returnsOnSite() {
        // Act
        val result = JobLocationUi.fromJobLocation(JobLocation.OnSite)
        
        // Assert
        assertEquals(JobLocationUi.OnSite, result)
    }
    
    @Test
    fun fromJobLocation_remote_returnsRemote() {
        // Act
        val result = JobLocationUi.fromJobLocation(JobLocation.Remote)
        
        // Assert
        assertEquals(JobLocationUi.Remote, result)
    }
    
    @Test
    fun fromJobLocation_hybrid_returnsHybrid() {
        // Act
        val result = JobLocationUi.fromJobLocation(JobLocation.Hybrid)
        
        // Assert
        assertEquals(JobLocationUi.Hybrid, result)
    }
    
    @Test
    fun allJobLocations_haveMappings() {
        // Assert all enum values can be converted
        JobLocation.values().forEach { location ->
            val converted = JobLocationUi.fromJobLocation(location)
            assertNotNull(converted)
        }
    }
}
