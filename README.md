# Newsly

## Project Overview
Newsly is an application that aggregates news from different websites via a third-party rest-api, displaying the most relevant news to the user. It is created for the demonstration of functionalities such as RecyclerView with Material design, efficient alteration of UI elements with DiffUtil and ListAdapter, Firebase as a management system for users' data, and a clean separation of concerns Architecture for the Repository Pattern.


---

## Features
1. **Advanced RecyclerView Implementation**:
   - Employs `ListAdapter` for better handling of updates.
   - Integrates `DiffUtil.ItemCallback` in order to recognize changes in list data and updates accordingly.
   - Implements `BindingAdapter` so that the data binding logic is cleaner and more reusable.

2. **Material Design**:
   - Implementation of `MaterialCardView` for news items styling in the RecyclerView.
   - Cards attributes: rounded corners, shadows, and  text/image alignment.

3. **API Integration**:
   - Gets top news headlines using API.
   - Data fetched from API is: article title, description, image, etc...

4. **Firebase Integration**:
   - Firebase `Firestore` for saving saved articles.
   

5. **Clean Repository Architecture**:
   - Implemented for clean separation of concerns.

---

## Project Structure

1. **Data Layer**:
   - Contains the logic for objects, and handles API configuration.
3. **UI Layer**
   - Responsible for handling and displaying data.
5. **Utilities**
   - Provides utility classes for our custom implementations.

---

## Installation

### Prerequisites
- Android Studio (latest version recommended)
- Firebase project setup with API keys configured
- News API key


