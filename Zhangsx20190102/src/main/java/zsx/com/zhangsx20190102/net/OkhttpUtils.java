package zsx.com.zhangsx20190102.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
            Handler handler= new Handler();
            OkHttpClient okHttpClient;
            public  static OkhttpUtils  mInstanse;
            public OkhttpUtils(){
     //拦截器
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClient= new OkHttpClient.Builder()
                        .readTimeout(5, TimeUnit.SECONDS)
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS)
                        .build();
            }
            public static OkhttpUtils getmInstanse(){
                if (mInstanse==null){
                    synchronized (OkhttpUtils.class){
                        if (mInstanse==null){
                            mInstanse= new OkhttpUtils();
                        }
                    }
                }
                return mInstanse;
            }


            public  void  doPost(String uri, HashMap<String,String> par,Class cls, final OkhttpCallBack okhttpCallBack){

                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, String> map : par.entrySet()) {
                    builder.add(map.getKey(),map.getValue());
                }


                RequestBody requestBody = builder.build();
                final Request request=new Request.Builder().url(uri).post(requestBody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (okhttpCallBack!=null){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    okhttpCallBack.onFair("网络异常啊 老铁");
                                }
                            });
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                          if (okhttpCallBack!=null){
                              if (200==response.code()) {
                                  final String result = response.body().string();
                                  handler.post(new Runnable() {
                                      @Override
                                      public void run() {
                                          okhttpCallBack.onsuccess(result);
                                      }
                                  });
                              }
                          }
                    }
                });
            }
   public void cancalAllTask(){
                if (okHttpClient!=null){
                    okHttpClient.dispatcher().cancelAll();
                }
   }
}
