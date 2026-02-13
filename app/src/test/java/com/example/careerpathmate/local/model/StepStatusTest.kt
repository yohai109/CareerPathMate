package com.example.careerpathmate.local.model

import com.example.careerpathmate.uimodels.StepStatusUi
import org.junit.Assert.*
import org.junit.Test

class StepStatusTest {
    
    @Test
    fun fromStepStatusUi_done_returnsDone() {
        // Act
        val result = StepStatus.fromStepStatusUi(StepStatusUi.Done)
        
        // Assert
        assertEquals(StepStatus.Done, result)
    }
    
    @Test
    fun fromStepStatusUi_current_returnsCurrent() {
        // Act
        val result = StepStatus.fromStepStatusUi(StepStatusUi.Current)
        
        // Assert
        assertEquals(StepStatus.Current, result)
    }
    
    @Test
    fun fromStepStatusUi_future_returnsFuture() {
        // Act
        val result = StepStatus.fromStepStatusUi(StepStatusUi.Future)
        
        // Assert
        assertEquals(StepStatus.Future, result)
    }
    
    @Test
    fun allStepStatuses_haveMappings() {
        // Assert all enum values can be converted
        StepStatusUi.values().forEach { uiStatus ->
            val converted = StepStatus.fromStepStatusUi(uiStatus)
            assertNotNull(converted)
        }
    }
}
