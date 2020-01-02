package top.jbzm.k8s.demo.util;

import com.google.gson.reflect.TypeToken;
import io.kubernetes.client.*;
import io.kubernetes.client.models.V1DeleteOptions;
import io.kubernetes.client.models.V1Status;
import top.jbzm.k8s.demo.module.CronHpa;
import top.jbzm.k8s.demo.module.CronHpaList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jbzm
 * @date 2019-11-25 17:04
 */
public class CronHpaV1beta1Api {
  private ApiClient apiClient;

  public CronHpaV1beta1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public CronHpaV1beta1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public CronHpa createNamespacedCronHpa(String namespace, CronHpa body, String pretty)
      throws ApiException {
    ApiResponse<CronHpa> resp = createNamespacedCronHpaWithHttpInfo(namespace, body, pretty);
    return resp.getData();
  }

  public ApiResponse<CronHpa> createNamespacedCronHpaWithHttpInfo(
      String namespace, CronHpa body, String pretty) throws ApiException {
    com.squareup.okhttp.Call call =
        createNamespacedCronHpaSetValidateBeforeCall(namespace, body, pretty, null, null);
    Type localVarReturnType = new TypeToken<CronHpa>() {}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  private com.squareup.okhttp.Call createNamespacedCronHpaSetValidateBeforeCall(
      String namespace,
      CronHpa body,
      String pretty,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {

    // verify the required parameter 'namespace' is set
    if (namespace == null) {
      throw new ApiException(
          "Missing the required parameter 'namespace' when calling createNamespacedDaemonSet(Async)");
    }

    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(
          "Missing the required parameter 'body' when calling createNamespacedDaemonSet(Async)");
    }

    com.squareup.okhttp.Call call =
        createNamespacedCronHpaSetCall(
            namespace, body, pretty, progressListener, progressRequestListener);
    return call;
  }

  public com.squareup.okhttp.Call createNamespacedCronHpaSetCall(
      String namespace,
      CronHpa body,
      String pretty,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {
    Object localVarPostBody = body;

    // create path and map variables
    String localVarPath =
        "/apis/autoscaling.alibabacloud.com/v1beta1/namespaces/{namespace}/cronhorizontalpodautoscalers/{name}"
            .replaceAll("\\{" + "namespace" + "\\}", apiClient.escapeString(namespace.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    if (pretty != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("pretty", pretty));
    }

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json", "application/yaml", "application/vnd.kubernetes.protobuf"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {"*/*"};
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

    if (progressListener != null) {
      apiClient
          .getHttpClient()
          .networkInterceptors()
          .add(
              new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(
                    com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                  com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                  return originalResponse
                      .newBuilder()
                      .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                      .build();
                }
              });
    }

    String[] localVarAuthNames = new String[] {"BearerToken"};
    return apiClient.buildCall(
        localVarPath,
        "POST",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarFormParams,
        localVarAuthNames,
        progressRequestListener);
  }

  public CronHpa readNamespacedCronHpa(
      String name, String namespace, String pretty, Boolean exact, Boolean export)
      throws ApiException {
    ApiResponse<CronHpa> resp =
        readNamespacedCronHpaWithHttpInfo(name, namespace, pretty, exact, export);
    return resp.getData();
  }

  public ApiResponse<CronHpa> readNamespacedCronHpaWithHttpInfo(
      String name, String namespace, String pretty, Boolean exact, Boolean export)
      throws ApiException {
    com.squareup.okhttp.Call call =
        readNamespacedCronHpaValidateBeforeCall(name, namespace, pretty, exact, export, null, null);
    Type localVarReturnType = new TypeToken<CronHpa>() {}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  private com.squareup.okhttp.Call readNamespacedCronHpaValidateBeforeCall(
      String name,
      String namespace,
      String pretty,
      Boolean exact,
      Boolean export,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {

    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(
          "Missing the required parameter 'name' when calling readNamespacedCronHpa(Async)");
    }

    // verify the required parameter 'namespace' is set
    if (namespace == null) {
      throw new ApiException(
          "Missing the required parameter 'namespace' when calling readNamespacedCronHpa(Async)");
    }

    com.squareup.okhttp.Call call =
        readNamespacedCronHpaCall(
            name, namespace, pretty, exact, export, progressListener, progressRequestListener);
    return call;
  }

  public com.squareup.okhttp.Call readNamespacedCronHpaCall(
      String name,
      String namespace,
      String pretty,
      Boolean exact,
      Boolean export,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {
    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath =
        "/apis/autoscaling.alibabacloud.com/v1beta1/namespaces/{namespace}/cronhorizontalpodautoscalers/{name}"
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()))
            .replaceAll("\\{" + "namespace" + "\\}", apiClient.escapeString(namespace.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    if (pretty != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("pretty", pretty));
    }
    if (exact != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("exact", exact));
    }
    if (export != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("export", export));
    }

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json", "application/yaml", "application/vnd.kubernetes.protobuf"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

    final String[] localVarContentTypes = {"*/*"};
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

    if (progressListener != null) {
      apiClient
          .getHttpClient()
          .networkInterceptors()
          .add(
              new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(
                    com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                  com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                  return originalResponse
                      .newBuilder()
                      .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                      .build();
                }
              });
    }

    String[] localVarAuthNames = new String[] {"BearerToken"};
    return apiClient.buildCall(
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarFormParams,
        localVarAuthNames,
        progressRequestListener);
  }

  public CronHpaList listNamespacedCronHpa(
      String namespace,
      String pretty,
      String _continue,
      String fieldSelector,
      Boolean includeUninitialized,
      String labelSelector,
      Integer limit,
      String resourceVersion,
      Integer timeoutSeconds,
      Boolean watch)
      throws ApiException {
    ApiResponse<CronHpaList> resp =
        listNamespacedCronHpaWithHttpInfo(
            namespace,
            pretty,
            _continue,
            fieldSelector,
            includeUninitialized,
            labelSelector,
            limit,
            resourceVersion,
            timeoutSeconds,
            watch);
    return resp.getData();
  }

  public ApiResponse<CronHpaList> listNamespacedCronHpaWithHttpInfo(
      String namespace,
      String pretty,
      String _continue,
      String fieldSelector,
      Boolean includeUninitialized,
      String labelSelector,
      Integer limit,
      String resourceVersion,
      Integer timeoutSeconds,
      Boolean watch)
      throws ApiException {
    com.squareup.okhttp.Call call =
        listNamespacedCronHpaValidateBeforeCall(
            namespace,
            pretty,
            _continue,
            fieldSelector,
            includeUninitialized,
            labelSelector,
            limit,
            resourceVersion,
            timeoutSeconds,
            watch,
            null,
            null);
    Type localVarReturnType = new TypeToken<CronHpaList>() {}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  private com.squareup.okhttp.Call listNamespacedCronHpaValidateBeforeCall(
      String namespace,
      String pretty,
      String _continue,
      String fieldSelector,
      Boolean includeUninitialized,
      String labelSelector,
      Integer limit,
      String resourceVersion,
      Integer timeoutSeconds,
      Boolean watch,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {

    // verify the required parameter 'namespace' is set
    if (namespace == null) {
      throw new ApiException(
          "Missing the required parameter 'namespace' when calling listNamespacedCronHpa(Async)");
    }

    com.squareup.okhttp.Call call =
        listNamespacedCronHpaCall(
            namespace,
            pretty,
            _continue,
            fieldSelector,
            includeUninitialized,
            labelSelector,
            limit,
            resourceVersion,
            timeoutSeconds,
            watch,
            progressListener,
            progressRequestListener);
    return call;
  }

  public com.squareup.okhttp.Call listNamespacedCronHpaCall(
      String namespace,
      String pretty,
      String _continue,
      String fieldSelector,
      Boolean includeUninitialized,
      String labelSelector,
      Integer limit,
      String resourceVersion,
      Integer timeoutSeconds,
      Boolean watch,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {
    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath =
        "/apis/autoscaling.alibabacloud.com/v1beta1/namespaces/{namespace}/cronhorizontalpodautoscalers"
            .replaceAll("\\{" + "namespace" + "\\}", apiClient.escapeString(namespace.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    if (pretty != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("pretty", pretty));
    }
    if (_continue != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("continue", _continue));
    }
    if (fieldSelector != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("fieldSelector", fieldSelector));
    }
    if (includeUninitialized != null) {
      localVarQueryParams.addAll(
          apiClient.parameterToPair("includeUninitialized", includeUninitialized));
    }
    if (labelSelector != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("labelSelector", labelSelector));
    }
    if (limit != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("limit", limit));
    }
    if (resourceVersion != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("resourceVersion", resourceVersion));
    }
    if (timeoutSeconds != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("timeoutSeconds", timeoutSeconds));
    }
    if (watch != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("watch", watch));
    }

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json",
      "application/yaml",
      "application/vnd.kubernetes.protobuf",
      "application/json;stream=watch",
      "application/vnd.kubernetes.protobuf;stream=watch"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

    final String[] localVarContentTypes = {"*/*"};
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

    if (progressListener != null) {
      apiClient
          .getHttpClient()
          .networkInterceptors()
          .add(
              new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(
                    com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                  com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                  return originalResponse
                      .newBuilder()
                      .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                      .build();
                }
              });
    }

    String[] localVarAuthNames = new String[] {"BearerToken"};
    return apiClient.buildCall(
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarFormParams,
        localVarAuthNames,
        progressRequestListener);
  }

  public V1Status deleteNamespacedCronHpa(
      String name,
      String namespace,
      V1DeleteOptions body,
      String pretty,
      Integer gracePeriodSeconds,
      Boolean orphanDependents,
      String propagationPolicy)
      throws ApiException {
    ApiResponse<V1Status> resp =
        deleteNamespacedCronHpaWithHttpInfo(
            name, namespace, body, pretty, gracePeriodSeconds, orphanDependents, propagationPolicy);
    return resp.getData();
  }

  public ApiResponse<V1Status> deleteNamespacedCronHpaWithHttpInfo(
      String name,
      String namespace,
      V1DeleteOptions body,
      String pretty,
      Integer gracePeriodSeconds,
      Boolean orphanDependents,
      String propagationPolicy)
      throws ApiException {
    com.squareup.okhttp.Call call =
        deleteNamespacedCronHpaValidateBeforeCall(
            name,
            namespace,
            body,
            pretty,
            gracePeriodSeconds,
            orphanDependents,
            propagationPolicy,
            null,
            null);
    Type localVarReturnType = new TypeToken<V1Status>() {}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  private com.squareup.okhttp.Call deleteNamespacedCronHpaValidateBeforeCall(
      String name,
      String namespace,
      V1DeleteOptions body,
      String pretty,
      Integer gracePeriodSeconds,
      Boolean orphanDependents,
      String propagationPolicy,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {

    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(
          "Missing the required parameter 'name' when calling deleteNamespacedCronHpa(Async)");
    }

    // verify the required parameter 'namespace' is set
    if (namespace == null) {
      throw new ApiException(
          "Missing the required parameter 'namespace' when calling deleteNamespacedCronHpa(Async)");
    }

    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(
          "Missing the required parameter 'body' when calling deleteNamespacedCronHpa(Async)");
    }

    com.squareup.okhttp.Call call =
        deleteNamespacedCronHpaCall(
            name,
            namespace,
            body,
            pretty,
            gracePeriodSeconds,
            orphanDependents,
            propagationPolicy,
            progressListener,
            progressRequestListener);
    return call;
  }

  public com.squareup.okhttp.Call deleteNamespacedCronHpaCall(
      String name,
      String namespace,
      V1DeleteOptions body,
      String pretty,
      Integer gracePeriodSeconds,
      Boolean orphanDependents,
      String propagationPolicy,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {
    Object localVarPostBody = body;

    // create path and map variables
    String localVarPath =
        "/apis/autoscaling.alibabacloud.com/v1beta1/namespaces/{namespace}/cronhorizontalpodautoscalers/{name}"
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()))
            .replaceAll("\\{" + "namespace" + "\\}", apiClient.escapeString(namespace.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    if (pretty != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("pretty", pretty));
    }
    if (gracePeriodSeconds != null) {
      localVarQueryParams.addAll(
          apiClient.parameterToPair("gracePeriodSeconds", gracePeriodSeconds));
    }
    if (orphanDependents != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("orphanDependents", orphanDependents));
    }
    if (propagationPolicy != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("propagationPolicy", propagationPolicy));
    }

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json", "application/yaml", "application/vnd.kubernetes.protobuf"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

    final String[] localVarContentTypes = {"*/*"};
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

    if (progressListener != null) {
      apiClient
          .getHttpClient()
          .networkInterceptors()
          .add(
              new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(
                    com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                  com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                  return originalResponse
                      .newBuilder()
                      .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                      .build();
                }
              });
    }

    String[] localVarAuthNames = new String[] {"BearerToken"};
    return apiClient.buildCall(
        localVarPath,
        "DELETE",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarFormParams,
        localVarAuthNames,
        progressRequestListener);
  }

  public CronHpa replaceCronHpa(String name, CronHpa body, String pretty) throws ApiException {
    ApiResponse<CronHpa> resp = replaceCronHpaWithHttpInfo(name, body, pretty);
    return resp.getData();
  }

  public ApiResponse<CronHpa> replaceCronHpaWithHttpInfo(String name, CronHpa body, String pretty)
      throws ApiException {
    com.squareup.okhttp.Call call =
        replaceCronHpaValidateBeforeCall(name, body, pretty, null, null);
    Type localVarReturnType = new TypeToken<CronHpa>() {}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  private com.squareup.okhttp.Call replaceCronHpaValidateBeforeCall(
      String name,
      CronHpa body,
      String pretty,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {

    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(
          "Missing the required parameter 'name' when calling replaceCronHpa(Async)");
    }

    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(
          "Missing the required parameter 'body' when calling replaceCronHpa(Async)");
    }

    com.squareup.okhttp.Call call =
        replaceCronHpaCall(name, body, pretty, progressListener, progressRequestListener);
    return call;
  }

  public com.squareup.okhttp.Call replaceCronHpaCall(
      String name,
      CronHpa body,
      String pretty,
      final ProgressResponseBody.ProgressListener progressListener,
      final ProgressRequestBody.ProgressRequestListener progressRequestListener)
      throws ApiException {
    Object localVarPostBody = body;

    // create path and map variables
    String localVarPath =
        "/apis/autoscaling.alibabacloud.com/v1beta1/namespaces/{namespace}/cronhorizontalpodautoscalers/{name}"
            .replaceAll("\\{" + "namespace" + "\\}", apiClient.escapeString("default"))
            .replaceAll("\\{" + "name" + "\\}", apiClient.escapeString(name.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    if (pretty != null) {
      localVarQueryParams.addAll(apiClient.parameterToPair("pretty", pretty));
    }

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json", "application/yaml", "application/vnd.kubernetes.protobuf"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

    final String[] localVarContentTypes = {"*/*"};
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

    if (progressListener != null) {
      apiClient
          .getHttpClient()
          .networkInterceptors()
          .add(
              new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(
                    com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                  com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                  return originalResponse
                      .newBuilder()
                      .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                      .build();
                }
              });
    }

    String[] localVarAuthNames = new String[] {"BearerToken"};
    return apiClient.buildCall(
        localVarPath,
        "PUT",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarPostBody,
        localVarHeaderParams,
        localVarFormParams,
        localVarAuthNames,
        progressRequestListener);
  }
}
