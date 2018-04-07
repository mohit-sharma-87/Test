package com.codelife.test.dataService;


import com.codelife.test.BuildConfig;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Mohit Sharma on 18/09/15.
 */
public class NetworkClient {



    private NetworkClient() {
    }

    public static Retrofit getRestAdapter(final HashMap<String, String> requestHeaderMap) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(END_POINT_INTERCEPTOR)
                .addNetworkInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    Set<Map.Entry<String, String>> entrySet = requestHeaderMap.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        if (entry.getValue().isEmpty())
                            builder.removeHeader(entry.getKey());
                        else
                            builder.addHeader(entry.getKey(), entry.getValue());
                    }

                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .addNetworkInterceptor(interceptor);

        OkHttpClient client = clientBuilder.build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(getGSonClient()))
                .build();
    }

    private static final Interceptor END_POINT_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .header("Connection", "close")
                    .header("Accept", "application/json");
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    };

    private static Gson getGSonClient() {
        return new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && expose.serialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.deserialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .serializeNulls()
                .create();
    }

}



