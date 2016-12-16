package ua.pavlo.tikhonov.simplegithubclient.model.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import ua.pavlo.tikhonov.simplegithubclient.model.api.basic.AccessToken;

public final class ApiModule {

    private static final boolean ENABLE_LOG = true;

    private static final boolean ENABLE_AUTH = true;

    private ApiModule() {
    }

    public static ApiInterface getApiInterface(String url) {

        OkHttpClient httpClient = new OkHttpClient();

            if (ENABLE_AUTH) {
                httpClient.interceptors().add(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", AccessToken.getAccessToken())
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                });
            }

            if (ENABLE_LOG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.interceptors().add(interceptor);
            }

            Retrofit.Builder builder = new Retrofit.Builder().
                    baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

            builder.client(httpClient);

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
