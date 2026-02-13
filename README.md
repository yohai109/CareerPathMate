# Career Path Mate

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  [![CI](https://github.com/yohai109/CareerPathMate/actions/workflows/ci.yml/badge.svg)](https://github.com/yohai109/CareerPathMate/actions/workflows/ci.yml)

A personal Android application designed to help job seekers track and manage their job applications throughout the job search process.

## Overview

Career Path Mate is a comprehensive job application tracker that allows users to efficiently organize and monitor their job search activities. Whether you're applying to multiple positions or tracking the progress of individual applications, this app provides an intuitive interface to manage your career journey.

## Features

- 📝 **Job Application Tracking**: Keep track of all your job applications in one place
- 📊 **Application Status Management**: Monitor the progress of each application
- 🔄 **Multi-step Process Tracking**: Track different stages of the application process (applied, interview, offer, etc.)
- 💾 **Local Data Storage**: All your data is stored securely on your device using Room database
- 🎨 **Modern UI**: Built with Material Design components for a clean and intuitive user experience

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Android Jetpack (Navigation, ViewBinding)
- **Database**: Room (SQLite)
- **Dependency Injection**: Dagger Hilt
- **Architecture**: MVVM (Model-View-ViewModel)
- **Minimum SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 16 (API 36)

## Requirements

- Android Studio (Arctic Fox or newer)
- Android SDK 36
- JDK 17
- Gradle 8.0+

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

## Project Structure

```
app/src/main/java/com/example/careerpathmate/
├── local/              # Database layer (Room)
│   ├── dao/           # Data Access Objects
│   ├── model/         # Entity classes
│   └── typeconverters/ # Type converters for Room
├── screens/           # UI screens and ViewModels
├── customviews/       # Custom UI components
└── JobLoggerApplication.kt  # Application class
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
