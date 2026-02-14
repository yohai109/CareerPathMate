# Career Path Mate

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  [![CI](https://github.com/yohai109/CareerPathMate/actions/workflows/ci.yml/badge.svg)](https://github.com/yohai109/CareerPathMate/actions/workflows/ci.yml)

A personal Android application designed to help job seekers track and manage their job applications throughout the job search process.

## Overview

Career Path Mate is a comprehensive job application tracker that allows users to efficiently organize and monitor their job search activities. Whether you're applying to multiple positions or tracking the progress of individual applications, this app provides an intuitive interface to manage your career journey.

## Features

- 📝 **Job Application Tracking**: Keep track of all your job applications in one place
- 📊 **Application Status Management**: Monitor the progress of each application with status indicators (Ongoing, Old, Accepted, Rejected)
- 🔄 **Multi-step Process Tracking**: Break down each job application into actionable steps with progress tracking
- 🔍 **Search & Filter**: Real-time search functionality and filter jobs by location and status
- 📋 **Job Details View**: Detailed view for each job with its associated steps and timeline
- ⚡ **Long-Press Actions**: Quick actions via long-press dialogs for managing jobs and steps
- 💾 **Local Data Storage**: All your data is stored securely on your device using Room database
- 🎨 **Modern UI**: Built with Material Design components for a clean and intuitive user experience

## Technology Stack

- **Language**: Kotlin 2.0
- **UI Framework**: Android Jetpack (Navigation with Safe Args, ViewBinding)
- **Database**: Room 2.8.4 (SQLite)
- **Dependency Injection**: Dagger Hilt 2.59.1
- **Architecture**: MVVM (Model-View-ViewModel) with Repository pattern
- **Logging**: Timber 5.0.1
- **Build System**: Gradle 9.3.1 with KSP (Kotlin Symbol Processing)
- **Minimum SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 16 (API 36)

## Requirements

- Android Studio (Hedgehog or newer)
- Android SDK 36
- JDK 17
- Gradle 9.3+

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yohai109/CareerPathMate.git
   cd CareerPathMate
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository folder

3. **Build the project**
   - Let Android Studio download and sync all dependencies
   - Build the project: `Build > Make Project` or press `Ctrl+F9` (Windows/Linux) / `Cmd+F9` (Mac)

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift+F10` (Windows/Linux) / `Ctrl+R` (Mac)

## Testing

The project includes comprehensive unit tests for data models, type converters, and business logic.

### Running Tests Locally

```bash
./gradlew test
```

### CI/CD Pipeline

The project uses GitHub Actions for continuous integration. On every push and pull request:
- ✅ Automated tests are executed
- 📊 **Test results are visualized** with a beautiful UI in the GitHub Actions checks tab
- 📝 Test summaries are posted as comments on pull requests
- 📦 Detailed test reports are uploaded as artifacts

Test results include:
- Pass/fail status for each test
- Test execution time
- Detailed failure messages and stack traces
- Overall test statistics and trends

You can view test results directly in:
1. The **Checks** tab of any pull request
2. The **Actions** tab for workflow runs
3. Downloaded test report artifacts for detailed HTML reports

## Project Structure

```
app/src/main/java/com/yohai/careerpathmate/
├── baseclasses/        # Base classes for fragments & bottom sheet dialogs
├── customviews/        # Custom UI components (FormInputText, FormInputSpinner, etc.)
├── hilt/               # Dagger Hilt dependency injection modules
├── local/              # Database layer (Room)
│   ├── dao/           # Data Access Objects (BaseDao, JobsDao, StepsDao)
│   ├── model/         # Entity classes (JobEntity, JobStepEntity)
│   └── typeconverters/ # Type converters for Room (DateTypeConverter)
├── screens/           # UI screens and ViewModels
│   ├── createjob/     # Job creation screen
│   ├── createstep/    # Job step creation screen
│   ├── jobdetails/    # Job details with steps list
│   │   └── steplongclickdialog/  # Step management dialog
│   └── jobslist/      # Jobs list with search & filter
│       └── joblongclickdialog/   # Job management dialog
├── uimodels/          # UI data models and adapters
├── JobLoggerApplication.kt  # Application class with Hilt
└── MainActivity.kt    # Main activity with Navigation Component
```
## Theme and Colors

The app supports both light and dark themes that automatically switch based on system settings.

### Color Palette

#### Light Theme
- **Primary**: Dark Green (#1B5E20) with lighter variant (#388E3C)
- **Secondary**: Purple (#6200EA) with lighter variant (#7C4DFF)
- **Background**: Light Gray (#F5F5F5)
- **Surface**: White (#FFFFFF)

#### Dark Theme
- **Primary**: Light Green (#66BB6A) with lighter variant (#81C784)
- **Secondary**: Light Purple (#9575CD) with lighter variant (#B39DDB)
- **Background**: Dark Gray (#121212)
- **Surface**: Charcoal (#1E1E1E)

### Status Colors
The app uses consistent status colors across both themes:
- **Ongoing**: Blue (#2196F3 light, #42A5F5 dark)
- **Old**: Orange (#FF9800 light, #FFA726 dark)
- **Accepted**: Green (#4CAF50 light, #66BB6A dark)
- **Rejected**: Red (#F44336 light, #EF5350 dark)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Yohai Knaani

---

Made with ❤️ to help job seekers stay organized during their career journey
