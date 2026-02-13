package com.yohai.careerpathmate.local.model

import com.yohai.careerpathmate.uimodels.StepLocationUi
import org.junit.Assert.*
import org.junit.Test

class StepLocationTest {
    
    @Test
    fun fromStepLocationUi_onSite_returnsOnSite() {
        // Act
        val result = StepLocation.fromStepLocationUi(StepLocationUi.OnSite)
        
        // Assert
        assertEquals(StepLocation.OnSite, result)
    }
    
    @Test
    fun fromStepLocationUi_remote_returnsRemote() {
        // Act
        val result = StepLocation.fromStepLocationUi(StepLocationUi.Remote)
        
        // Assert
        assertEquals(StepLocation.Remote, result)
    }
    
    @Test
    fun allStepLocations_haveMappings() {
        // Assert all enum values can be converted
        StepLocationUi.values().forEach { uiLocation ->
            val converted = StepLocation.fromStepLocationUi(uiLocation)
            assertNotNull(converted)
        }
    }
}
