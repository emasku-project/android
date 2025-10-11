# GoldApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createGold**](GoldApi.md#createGold) | **POST** /api/v1/golds |  |
| [**deleteGoldById**](GoldApi.md#deleteGoldById) | **DELETE** /api/v1/golds/{gold_id} |  |
| [**getAllGolds**](GoldApi.md#getAllGolds) | **GET** /api/v1/golds |  |
| [**getGoldById**](GoldApi.md#getGoldById) | **GET** /api/v1/golds/{gold_id} |  |


<a id="createGold"></a>
# **createGold**
> CreateGoldRes createGold(body)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GoldApi()
val body : CreateGoldReq =  // CreateGoldReq | body
try {
    val result : CreateGoldRes = apiInstance.createGold(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GoldApi#createGold")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GoldApi#createGold")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**CreateGoldReq**](CreateGoldReq.md)| body | |

### Return type

[**CreateGoldRes**](CreateGoldRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteGoldById"></a>
# **deleteGoldById**
> DeleteGoldByIdRes deleteGoldById(goldId)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GoldApi()
val goldId : kotlin.Int = 56 // kotlin.Int | gold_id
try {
    val result : DeleteGoldByIdRes = apiInstance.deleteGoldById(goldId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GoldApi#deleteGoldById")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GoldApi#deleteGoldById")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **goldId** | **kotlin.Int**| gold_id | |

### Return type

[**DeleteGoldByIdRes**](DeleteGoldByIdRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllGolds"></a>
# **getAllGolds**
> GetAllGoldsRes getAllGolds()



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GoldApi()
try {
    val result : GetAllGoldsRes = apiInstance.getAllGolds()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GoldApi#getAllGolds")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GoldApi#getAllGolds")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllGoldsRes**](GetAllGoldsRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getGoldById"></a>
# **getGoldById**
> GetGoldByIdRes getGoldById(goldId)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GoldApi()
val goldId : kotlin.Int = 56 // kotlin.Int | gold_id
try {
    val result : GetGoldByIdRes = apiInstance.getGoldById(goldId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GoldApi#getGoldById")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GoldApi#getGoldById")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **goldId** | **kotlin.Int**| gold_id | |

### Return type

[**GetGoldByIdRes**](GetGoldByIdRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

