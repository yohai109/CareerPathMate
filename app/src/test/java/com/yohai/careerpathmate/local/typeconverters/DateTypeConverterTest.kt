package com.yohai.careerpathmate.local.typeconverters

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class DateTypeConverterTest {
    
    private lateinit var converter: DateTypeConverter
    
    @Before
    fun setup() {
        converter = DateTypeConverter()
    }
    
    @Test
    fun calendarToString_validCalendar_returnsFormattedString() {
        // Arrange
        val calendar = Calendar.getInstance()
        calendar.set(2024, Calendar.JANUARY, 15, 10, 30, 45)
        calendar.set(Calendar.MILLISECOND, 0)
        
        // Act
        val result = converter.calenderToString(calendar)
        
        // Assert
        assertNotNull(result)
        assertTrue(result!!.contains("2024-01-15"))
    }
    
    @Test
    fun calendarToString_nullCalendar_returnsNull() {
        // Act
        val result = converter.calenderToString(null)
        
        // Assert
        assertNull(result)
    }
    
    @Test
    fun stringToCalendar_validString_returnsCalendar() {
        // Arrange
        val dateString = "2024-01-15T10:30:45Z"
        
        // Act
        val result = converter.stringToCalender(dateString)
        
        // Assert
        assertNotNull(result)
        assertEquals(2024, result!!.get(Calendar.YEAR))
        assertEquals(Calendar.JANUARY, result.get(Calendar.MONTH))
        assertEquals(15, result.get(Calendar.DAY_OF_MONTH))
    }
    
    @Test
    fun stringToCalendar_nullString_returnsNull() {
        // Act
        val result = converter.stringToCalender(null)
        
        // Assert
        assertNull(result)
    }
    
    @Test
    fun stringToCalendar_invalidString_returnsCurrentTime() {
        // Arrange
        val invalidString = "invalid-date"
        
        // Act
        val result = converter.stringToCalender(invalidString)
        
        // Assert
        // When parsing fails, the converter returns current time
        assertNotNull(result)
    }
    
    @Test
    fun roundTrip_calendarToStringAndBack_preservesDate() {
        // Arrange
        val original = Calendar.getInstance()
        original.set(2024, Calendar.MARCH, 20, 14, 15, 30)
        original.set(Calendar.MILLISECOND, 0)
        
        // Act
        val stringRepresentation = converter.calenderToString(original)
        val reconstructed = converter.stringToCalender(stringRepresentation)
        
        // Assert
        assertNotNull(reconstructed)
        assertEquals(original.get(Calendar.YEAR), reconstructed!!.get(Calendar.YEAR))
        assertEquals(original.get(Calendar.MONTH), reconstructed.get(Calendar.MONTH))
        assertEquals(original.get(Calendar.DAY_OF_MONTH), reconstructed.get(Calendar.DAY_OF_MONTH))
        assertEquals(original.get(Calendar.HOUR_OF_DAY), reconstructed.get(Calendar.HOUR_OF_DAY))
        assertEquals(original.get(Calendar.MINUTE), reconstructed.get(Calendar.MINUTE))
        assertEquals(original.get(Calendar.SECOND), reconstructed.get(Calendar.SECOND))
    }
}
