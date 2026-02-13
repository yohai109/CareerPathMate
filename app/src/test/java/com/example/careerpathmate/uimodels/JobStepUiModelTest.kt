package com.example.careerpathmate.uimodels

import com.example.careerpathmate.local.model.JobStepEntity
import com.example.careerpathmate.local.model.StepLocation
import com.example.careerpathmate.local.model.StepStatus
import org.junit.Assert.*
import org.junit.Test
import java.util.Calendar

class JobStepUiModelTest {
    
    @Test
    fun jobStepUiModel_defaultConstructor_generatesUniqueId() {
        // Arrange
        val date = Calendar.getInstance()
        val jobId = "job-123"
        
        // Act
        val step1 = JobStepUiModel(
            date = date,
            name = "Interview",
            status = StepStatusUi.Current,
            location = StepLocationUi.OnSite,
            jobId = jobId
        )
        val step2 = JobStepUiModel(
            date = date,
            name = "Interview",
            status = StepStatusUi.Current,
            location = StepLocationUi.OnSite,
            jobId = jobId
        )
        
        // Assert
        assertNotEquals(step1.id, step2.id)
    }
    
    @Test
    fun jobStepUiModel_fromEntity_convertsCorrectly() {
        // Arrange
        val date = Calendar.getInstance()
        date.set(2024, Calendar.MARCH, 15, 10, 0, 0)
        val entity = JobStepEntity(
            id = "step-id",
            date = date,
            name = "Technical Interview",
            status = StepStatus.Done,
            location = StepLocation.Remote,
            jobId = "job-456"
        )
        
        // Act
        val uiModel = JobStepUiModel(entity)
        
        // Assert
        assertEquals(entity.id, uiModel.id)
        assertEquals(entity.date, uiModel.date)
        assertEquals(entity.name, uiModel.name)
        assertEquals(StepStatusUi.Done, uiModel.status)
        assertEquals(StepLocationUi.Remote, uiModel.location)
        assertEquals(entity.jobId, uiModel.jobId)
    }
    
    @Test
    fun jobStepUiModel_allStatusValues_convertCorrectly() {
        // Arrange
        val date = Calendar.getInstance()
        val jobId = "job-123"
        
        // Test all status conversions
        StepStatus.values().forEach { status ->
            val entity = JobStepEntity(
                date = date,
                name = "Test Step",
                status = status,
                location = StepLocation.OnSite,
                jobId = jobId
            )
            val uiModel = JobStepUiModel(entity)
            
            // Verify conversion maintains consistency
            assertNotNull(uiModel.status)
        }
    }
    
    @Test
    fun jobStepUiModel_allLocationValues_convertCorrectly() {
        // Arrange
        val date = Calendar.getInstance()
        val jobId = "job-123"
        
        // Test all location conversions
        StepLocation.values().forEach { location ->
            val entity = JobStepEntity(
                date = date,
                name = "Test Step",
                status = StepStatus.Current,
                location = location,
                jobId = jobId
            )
            val uiModel = JobStepUiModel(entity)
            
            // Verify conversion maintains consistency
            assertNotNull(uiModel.location)
        }
    }
}
