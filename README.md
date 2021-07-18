# simple-shop
A simple shopping app with google and facebook auth, also implementing MVVM design pattern.

This project include repository pattern and Retrofit to get data from API, Dagger 2 for the dependency injection, LiveData to maintain UI data in life cycles, and Hawk to store data values locally.

## Important note:
In order to run this app you need to include facebook app id and facebook login protocol scheme and put it into string resource file (`SimpleShop\app\src\main\res\values\strings.xml`) in `facebook_app_id` and `fb_login_protocol_scheme` fields.

## Feature:
- Implementing simple facebook and Google authentication to login
- Load products and categories from API callbacks
- Search products (local data)
- Share product from product detail page
- Save purchased products from product detail to purchase history page
- Purchase history page can be accessed through profile bottom bar menu

## Instrumental Test case:
### Load home data:
- Perform click on sign in button
- Check if categories list displayed
- Perform swipe left on categories list
- Perform swipe up on home page
### Load product detail:
- Perform click on sign in button
- Perform click on search bar
- Check if view with "search" hint displayed
- Perform type on search bar
- Check if product list displayed
- Perform click on first item of product list
- Check if product name, description, and price match the dummy data
### Load purchase history page
- Perform click on sign in button
- Check if product list displayed
- Perform click on first item of product list
- Check if view with "buy" text displayed
- Perform click on buy button
- Check if profile menu displayed
- Perform click on profile menu
- Check if product list displayed
