package com.yohai.careerpathmate.uimodels

import com.yohai.careerpathmate.local.model.StepLocation
import org.junit.Assert.*
import org.junit.Test

class StepLocationUiTest {
    
    @Test
    fun fromStepLocation_onSite_returnsOnSite() {
        // Act
        val result = StepLocationUi.fromStepLocation(StepLocation.OnSite)
        
        // Assert
        assertEquals(StepLocationUi.OnSite, result)
    }
    
    @Test
    fun fromStepLocation_remote_returnsRemote() {
        // Act
        val result = StepLocationUi.fromStepLocation(StepLocation.Remote)
        
        // Assert
        assertEquals(StepLocationUi.Remote, result)
    }
    
    @Test
    fun allStepLocations_haveMappings() {
        // Assert all enum values can be converted
        StepLocation.values().forEach { location ->
            val converted = StepLocationUi.fromStepLocation(location)
            assertNotNull(converted)
        }
    }
}
