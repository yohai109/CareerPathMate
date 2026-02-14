package com.yohai.careerpathmate.uimodels

import com.yohai.careerpathmate.local.model.StepStatus
import org.junit.Assert.*
import org.junit.Test

class StepStatusUiTest {
    
    @Test
    fun fromStepStatus_done_returnsDone() {
        // Act
        val result = StepStatusUi.fromStepStatus(StepStatus.Done)
        
        // Assert
        assertEquals(StepStatusUi.Done, result)
    }
    
    @Test
    fun fromStepStatus_current_returnsCurrent() {
        // Act
        val result = StepStatusUi.fromStepStatus(StepStatus.Current)
        
        // Assert
        assertEquals(StepStatusUi.Current, result)
    }
    
    @Test
    fun fromStepStatus_future_returnsFuture() {
        // Act
        val result = StepStatusUi.fromStepStatus(StepStatus.Future)
        
        // Assert
        assertEquals(StepStatusUi.Future, result)
    }
    
    @Test
    fun allStepStatuses_haveMappings() {
        // Assert all enum values can be converted
        StepStatus.values().forEach { status ->
            val converted = StepStatusUi.fromStepStatus(status)
            assertNotNull(converted)
        }
    }
}
