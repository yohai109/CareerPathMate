package com.example.careerpathmate.local.model

import org.junit.Assert.*
import org.junit.Test

class JobEntityTest {
    
    @Test
    fun jobEntity_defaultConstructor_generatesUniqueId() {
        // Act
        val job1 = JobEntity(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        val job2 = JobEntity(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        
        // Assert
        assertNotEquals(job1.id, job2.id)
    }
    
    @Test
    fun jobEntity_defaultValues_setCorrectly() {
        // Act
        val job = JobEntity(
            companyName = "Test Company",
            contactName = "John Doe",
            description = "Test Description"
        )
        
        // Assert
        assertEquals(JobStatus.OnGoing, job.status)
        assertEquals(JobLocation.Hybrid, job.location)
    }
    
    @Test
    fun jobEntity_allFields_setCorrectly() {
        // Arrange
        val id = "test-id-123"
        val companyName = "Acme Corp"
        val contactName = "Jane Smith"
        val description = "Software Engineer Position"
        val status = JobStatus.Yes
        val location = JobLocation.Remote
        
        // Act
        val job = JobEntity(
            id = id,
            companyName = companyName,
            contactName = contactName,
            description = description,
            status = status,
            location = location
        )
        
        // Assert
        assertEquals(id, job.id)
        assertEquals(companyName, job.companyName)
        assertEquals(contactName, job.contactName)
        assertEquals(description, job.description)
        assertEquals(status, job.status)
        assertEquals(location, job.location)
    }
}
