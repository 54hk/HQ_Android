package project.hq.com.hq_android.modle;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.hq.com.hq_android.HQConstants;
import project.hq.com.hq_android.utils.SharePreUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Json on 2017/12/2.
 */

public class HttpMethods {
    //retrofit对象
    private Retrofit retrofit;
    private OkHttpClient.Builder client;

    public <T> T createService(Class<T> serviceClass) {
        client = new OkHttpClient.Builder();
        client.connectTimeout(HQConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
//                .client(client.build())
                .client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HQConstants.BAST_URL)
                .build();
        return retrofit.create(serviceClass);
    }

    //在访问httpmethods的时候创建单列
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public OkHttpClient.Builder getClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
//                        保存Cookie
                        SharePreUtils.setString(HQConstants.COOKIE,request.headers().get("Set-Cookie"));
                        Request.Builder builder1 = request.newBuilder();
                        if (!SharePreUtils.getString(HQConstants.COOKIE, "").isEmpty() && SharePreUtils.getString(HQConstants.COOKIE, "").length() != 0) {
                            builder1.addHeader("Cookie", SharePreUtils.getString(HQConstants.COOKIE, "").toString());
                        }
                        return chain.proceed(builder1.build());
                    }
                }).retryOnConnectionFailure(true);

        client.connectTimeout(HQConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return client;
    }
}


