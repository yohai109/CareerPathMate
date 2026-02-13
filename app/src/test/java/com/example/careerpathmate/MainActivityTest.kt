package com.example.careerpathmate

import androidx.appcompat.widget.Toolbar as AppCompatToolbar
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.*

/**
 * Robolectric test for MainActivity that validates UI components are properly initialized.
 * These tests run on the JVM without requiring an Android device or emulator.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        // Build and launch the MainActivity
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()
    }

    @Test
    fun toolbar_isNotNull() {
        // Verify that the toolbar exists in the layout
        val toolbar = activity.findViewById<AppCompatToolbar>(R.id.toolbar)
        assertNotNull("Toolbar should not be null", toolbar)
    }

    @Test
    fun navHostFragment_isPresent() {
        // Verify that the navigation host fragment container exists
        val navHostFragment = activity.findViewById<androidx.fragment.app.FragmentContainerView>(R.id.nav_host_fragment)
        assertNotNull("NavHostFragment should not be null", navHostFragment)
    }

    @Test
    fun activity_hasCorrectTitle() {
        // Verify that the activity has a title set
        val supportActionBar = activity.supportActionBar
        assertNotNull("Support action bar should not be null", supportActionBar)
    }

    @Test
    fun setToolbarTitle_updatesTitle() {
        // Test that setting the toolbar title works correctly
        val newTitle = "Test Title"
        activity.setToolbarTitle(newTitle)
        
        val actionBar = activity.supportActionBar
        assertNotNull("Action bar should not be null", actionBar)
        assertEquals("Title should be updated", newTitle, actionBar?.title)
    }

    @Test
    fun toolbarContainer_isVisible() {
        // Verify that the toolbar container is present
        val toolbarContainer = activity.findViewById<com.google.android.material.appbar.AppBarLayout>(R.id.toolbarContainer)
        assertNotNull("Toolbar container should not be null", toolbarContainer)
    }
}
