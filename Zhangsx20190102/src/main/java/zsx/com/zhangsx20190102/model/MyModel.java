package zsx.com.zhangsx20190102.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;

import zsx.com.zhangsx20190102.api.ProApi;
import zsx.com.zhangsx20190102.bean.ProsBean;
import zsx.com.zhangsx20190102.contract.MyContract;
import zsx.com.zhangsx20190102.net.OkhttpCallBack;
import zsx.com.zhangsx20190102.net.OkhttpUtils;
import zsx.com.zhangsx20190102.net.RequestCallBack;


public class MyModel implements MyContract.MyModel {
       Handler handler =  new Handler();
    private ProsBean proBean;

    @Override
    public void Pro(HashMap<String, String> map, String str,Class cls, final RequestCallBack requestCallBack) {
      OkhttpUtils.getmInstanse().doPost(str, map,cls, new OkhttpCallBack() {
            @Override
            public void onFair(String msg) {
                if (requestCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallBack.onfair("网不行啊,老铁");
                        }
                    });
                }
            }

            @Override
            public void onsuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    parResult(result,requestCallBack);
                   // proBean = new Gson().fromJson(result, ProsBean.class);
                }
            }
        });
    }

    private void parResult(final String result, final RequestCallBack requestCallBack) {
        proBean = new Gson().fromJson(result, ProsBean.class);

        if (requestCallBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallBack.onsuccess(proBean);
                }
            }) ;

        }

    }
}
