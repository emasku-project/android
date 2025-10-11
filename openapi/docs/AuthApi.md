# AuthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**login**](AuthApi.md#login) | **POST** /api/v1/auth/login |  |
| [**logout**](AuthApi.md#logout) | **POST** /api/v1/auth/logout |  |
| [**register**](AuthApi.md#register) | **POST** /api/v1/auth/register |  |


<a id="login"></a>
# **login**
> LoginRes login(body)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = AuthApi()
val body : LoginReq =  // LoginReq | body
try {
    val result : LoginRes = apiInstance.login(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#login")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#login")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**LoginReq**](LoginReq.md)| body | |

### Return type

[**LoginRes**](LoginRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="logout"></a>
# **logout**
> LogoutRes logout()



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = AuthApi()
try {
    val result : LogoutRes = apiInstance.logout()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#logout")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#logout")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**LogoutRes**](LogoutRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="register"></a>
# **register**
> RegisterRes register(body)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = AuthApi()
val body : RegisterReq =  // RegisterReq | body
try {
    val result : RegisterRes = apiInstance.register(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#register")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#register")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RegisterReq**](RegisterReq.md)| body | |

### Return type

[**RegisterRes**](RegisterRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

