package com.yohai.careerpathmate.local.model

import com.yohai.careerpathmate.uimodels.JobLocationUi
import org.junit.Assert.*
import org.junit.Test

class JobLocationTest {
    
    @Test
    fun fromJobLocationUi_onSite_returnsOnSite() {
        // Act
        val result = JobLocation.fromJobLocationUi(JobLocationUi.OnSite)
        
        // Assert
        assertEquals(JobLocation.OnSite, result)
    }
    
    @Test
    fun fromJobLocationUi_remote_returnsRemote() {
        // Act
        val result = JobLocation.fromJobLocationUi(JobLocationUi.Remote)
        
        // Assert
        assertEquals(JobLocation.Remote, result)
    }
    
    @Test
    fun fromJobLocationUi_hybrid_returnsHybrid() {
        // Act
        val result = JobLocation.fromJobLocationUi(JobLocationUi.Hybrid)
        
        // Assert
        assertEquals(JobLocation.Hybrid, result)
    }
    
    @Test
    fun allJobLocations_haveMappings() {
        // Assert all enum values can be converted
        JobLocationUi.values().forEach { uiLocation ->
            val converted = JobLocation.fromJobLocationUi(uiLocation)
            assertNotNull(converted)
        }
    }
}
