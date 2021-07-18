# simple-shop
A simple shopping app with google and facebook auth, also implementing MVVM design pattern.

### Important note:
In order to run this app you need to include facebook app id and facebook login protocol scheme and put it into string resource file (`SimpleShop\app\src\main\res\values\strings.xml`) in `facebook_app_id` and `fb_login_protocol_scheme` fields.

### Feature:
- Implementing simple facebook and Google authentication to login
- Load products and categories from API callbacks
- Search products (local data)
- Share product from product detail page
- Save purchased products from product detail to purchase history page
- Purchase history page can be accessed through profile bottom bar menu

This project include repository pattern and Retrofit to get data from API, Dagger 2 for the dependency injection, LiveData to maintain UI data in life cycles, and Hawk to store data values locally.
