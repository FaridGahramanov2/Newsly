# Newsly

## Project Overview
Newsly is an advanced news aggregation app that fetches top headlines using a third-party REST API and displays them in a user-friendly interface. The app is built to showcase concepts such as RecyclerView with Material Design, efficient data handling using DiffUtil and ListAdapter, Firebase integration for user data management, and MVVM architecture with the Repository pattern.

---

## Features
1. **Advanced RecyclerView Implementation**:
   - Uses `ListAdapter` for better performance and efficient updates.
   - Integrates `DiffUtil.ItemCallback` for seamless list data changes.
   - Implements custom `BindingAdapter` for cleaner and reusable data binding logic.

2. **Material Design**:
   - Uses `MaterialCardView` to style news items in the RecyclerView.
   - Cards have rounded corners, shadows, and proper text/image alignment.

3. **API Integration**:
   - Fetches top news headlines using the News API.
   - Data includes article title, description, image, and other metadata.

4. **Firebase Integration**:
   - Firebase Firestore for storing saved articles per user.
   - Firebase Authentication for login, registration, and logout functionality.

5. **MVVM Architecture**:
   - Adopts the Repository Pattern for clean separation of concerns.
   - ViewModel interacts with the Repository to fetch and manage data from API and Firestore.

6. **Additional Features**:
   - Allows users to save or unsave articles with a bookmark feature.
   - Animations for RecyclerView items and smooth fragment transitions.

---

## Project Structure

### 1. **Model**
   - **`Article`**: Represents a news article with fields for title, description, image, etc.
   - **`Source`**: Represents the source of a news article.

### 2. **View**
   - **Fragments**:
     - `NewsListFragment`: Displays a list of news articles.
     - `ArticleDetailFragment`: Shows detailed information about a selected article.
     - `UserFragment`: Manages user-saved articles and logout.
   - **Custom UI Components**:
     - `MaterialCardView` for RecyclerView items.
     - `BindingAdapters` for handling image loading and icons.

### 3. **ViewModel**
   - **NewsListViewModel**: Fetches news articles from the API and handles user interactions like saving articles.
   - **MainViewModel**: Manages shared data across multiple fragments.

### 4. **Repository**
   - Centralized class for handling data operations.
   - Abstracts Firebase Firestore and REST API interactions.

---

## Installation

### Prerequisites
- Android Studio (latest version recommended)
- Firebase project setup with API keys configured
- News API key


