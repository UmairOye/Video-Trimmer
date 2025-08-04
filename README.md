# Video-Trimmer
An open-source Kotlin Android Studio project that offers WhatsApp-like video trimming, allowing precise edits with a sleek user interface for seamless video customization on your Android device.

# ScreenShots

<img width="150" height="350" src="screenshots/Screenshot_20231009-124711.png" alt="Image" > <img width="150" height="350" src="screenshots/Screenshot_20231009-124720.png" alt="Image" > <img width="150" height="350" src="screenshots/Screenshot_20231009-124730.png" alt="Image" >


<img width="150" height="350" src="screenshots/Screenshot_20231009-124738.png" alt="Image" > <img width="150" height="350" src="screenshots/Screenshot_20231009-124744.png" alt="Image" > <img width="150" height="350" src="screenshots/Screenshot_20231009-124755.png" alt="Image" >


# Features
Trim videos to a specified length.
Simple and intuitive user interface.
Allows users to grant read external storage permission for video selection.
Supports various video formats.

# Usage

## Setup

### Kotlin-dsl
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}
```

### Gradle groovy
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```groovy
implementation 'com.github.redevrx:android_video_trimmer:1.0.0'
```

## Example
```xml
 <com.redevrx.video_trimmer.view.VideoEditor
        android:id="@+id/video_trimmer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

# Allow Permission

Upon launching the app, you'll be prompted to grant "Read External Storage" permission. Click the "Allow Permission" button to proceed.
# Select Video

After granting permission, the app will display a list of videos available on your device.
# Trim Video

Select the video you want to trim from the list. The selected video will be loaded into the video player.

Use the sliders to set the start and end points of the desired trim.

Preview the trimmed video to ensure it meets your requirements.

# Save Trimmed Video

Once satisfied with the trim, click the "Save Trimmed Video" button. The trimmed video will be saved to your device.

## Credits

[Android Video Trimmer](https://github.com/redevrx/android_video_trimmer) by [Kasem Saikhuedong](https://github.com/redevrx)

## 💼 Hire Me
Need help with Android development?  
I specialize in:
- Custom UI components (e.g., animated bottom navigation)
- API integration (AI tools, social media, ads)
- Performance optimization and clean architecture

👉 [Hire me on Fiverr](https://www.fiverr.com/users/theandroiddev/)


## Don't Forget to Star ⭐

If you found this project useful or had fun exploring it, please consider giving it a star. It's a great way to show your appreciation and it puts a smile on my face! 😊🌟

