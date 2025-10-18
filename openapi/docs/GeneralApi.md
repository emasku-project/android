# GeneralApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getMarketSummary**](GeneralApi.md#getMarketSummary) | **GET** /api/v1/general/market-summary |  |
| [**getSettings**](GeneralApi.md#getSettings) | **GET** /api/v1/general/settings |  |
| [**getSummary**](GeneralApi.md#getSummary) | **GET** /api/v1/general/summary |  |
| [**updateTaxSetting**](GeneralApi.md#updateTaxSetting) | **POST** /api/v1/general/settings/tax |  |


<a id="getMarketSummary"></a>
# **getMarketSummary**
> GetMarketSummaryRes getMarketSummary()



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GeneralApi()
try {
    val result : GetMarketSummaryRes = apiInstance.getMarketSummary()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GeneralApi#getMarketSummary")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GeneralApi#getMarketSummary")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetMarketSummaryRes**](GetMarketSummaryRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getSettings"></a>
# **getSettings**
> GetSettingsRes getSettings()



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GeneralApi()
try {
    val result : GetSettingsRes = apiInstance.getSettings()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GeneralApi#getSettings")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GeneralApi#getSettings")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetSettingsRes**](GetSettingsRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getSummary"></a>
# **getSummary**
> GetSummaryRes getSummary()



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GeneralApi()
try {
    val result : GetSummaryRes = apiInstance.getSummary()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GeneralApi#getSummary")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GeneralApi#getSummary")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetSummaryRes**](GetSummaryRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateTaxSetting"></a>
# **updateTaxSetting**
> UpdateTaxSettingRes updateTaxSetting(body)



### Example
```kotlin
// Import classes:
//import id.my.rizalanggoro.emasku.openapi.infrastructure.*
//import id.my.rizalanggoro.emasku.openapi.models.*

val apiInstance = GeneralApi()
val body : UpdateTaxSettingReq =  // UpdateTaxSettingReq | body
try {
    val result : UpdateTaxSettingRes = apiInstance.updateTaxSetting(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GeneralApi#updateTaxSetting")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GeneralApi#updateTaxSetting")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**UpdateTaxSettingReq**](UpdateTaxSettingReq.md)| body | |

### Return type

[**UpdateTaxSettingRes**](UpdateTaxSettingRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

