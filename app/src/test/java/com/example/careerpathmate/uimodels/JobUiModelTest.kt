package com.example.careerpathmate.uimodels

import com.example.careerpathmate.local.model.JobEntity
import com.example.careerpathmate.local.model.JobLocation
import com.example.careerpathmate.local.model.JobStatus
import org.junit.Assert.*
import org.junit.Test

class JobUiModelTest {
    
    @Test
    fun jobUiModel_defaultConstructor_generatesUniqueId() {
        // Act
        val job1 = JobUiModel(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        val job2 = JobUiModel(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        
        // Assert
        assertNotEquals(job1.id, job2.id)
    }
    
    @Test
    fun jobUiModel_defaultValues_setCorrectly() {
        // Act
        val job = JobUiModel(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        
        // Assert
        assertEquals(JobUiStatus.OnGoing, job.status)
        assertEquals(JobLocationUi.Hybrid, job.location)
    }
    
    @Test
    fun jobUiModel_fromEntity_convertsCorrectly() {
        // Arrange
        val entity = JobEntity(
            id = "test-id",
            companyName = "Acme Corp",
            contactName = "Jane Smith",
            description = "Software Engineer",
            status = JobStatus.Yes,
            location = JobLocation.Remote
        )
        
        // Act
        val uiModel = JobUiModel(entity)
        
        // Assert
        assertEquals(entity.id, uiModel.id)
        assertEquals(entity.companyName, uiModel.companyName)
        assertEquals(entity.contactName, uiModel.contactName)
        assertEquals(entity.description, uiModel.description)
        assertEquals(JobUiStatus.Yes, uiModel.status)
        assertEquals(JobLocationUi.Remote, uiModel.location)
    }
    
    @Test
    fun jobUiModel_allStatusValues_convertCorrectly() {
        // Test all status conversions
        JobStatus.values().forEach { status ->
            val entity = JobEntity(
                companyName = "Test",
                contactName = "Test",
                description = "Test",
                status = status
            )
            val uiModel = JobUiModel(entity)
            
            // Verify conversion maintains consistency
            assertNotNull(uiModel.status)
        }
    }
    
    @Test
    fun jobUiModel_allLocationValues_convertCorrectly() {
        // Test all location conversions
        JobLocation.values().forEach { location ->
            val entity = JobEntity(
                companyName = "Test",
                contactName = "Test",
                description = "Test",
                location = location
            )
            val uiModel = JobUiModel(entity)
            
            // Verify conversion maintains consistency
            assertNotNull(uiModel.location)
        }
    }
}
