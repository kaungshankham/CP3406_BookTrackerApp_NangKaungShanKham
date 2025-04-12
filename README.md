# Book Tracker App

Hi! I'm Nang Kaung Shan Kham, and this is my **Book Tracker App**, a mobile app I built using Kotlin and Jetpack Compose. It helps users search for books, save them to their personal library, track reading progress, and write reviews.

---

## What the App Can Do

### Search for Books
- You can search any book by title or author.
- The app uses **Google Books API** to fetch results.
- Found something you like? Just tap **Add** to save it to your library.

### Your Personal Library
- All added books go into your library.
- Each book shows its **status** — like "To Read", "Reading", or "Completed".

### Track Progress & Reviews
- Tap on a book to open the **detail screen**.
- You can update:
  - How many pages you’ve read
  - Give a rating (1 to 5 stars)
  - Write your thoughts as a review
  - Change the status of the book

### Easy Navigation
- The app lets you go from **Search → Library → Book Details** smoothly.
- It remembers what you updated, thanks to local storage.

---

##  How It Works (Tech Overview)

- Built with **Kotlin** and **Jetpack Compose**
- Uses **Room Database** to save your books locally
- Uses **Retrofit** to get data from Google Books API
- Image loading with **Coil**
- Architecture: **MVVM pattern** with ViewModel & Repository

---

## How to Run This App

1. Clone the repo or download the project
2. Open it in Android Studio
3. Sync Gradle, run it on an emulator or device — and you’re good to go!

---

##  About Me

My name is **Nang Kaung Shan Kham**.  
I'm passionate about mobile development, and this app is one of my proudest projects so far.

---

Thanks for checking it out!
