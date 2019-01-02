package zsx.com.zhangsx20190102.contract;

import java.util.HashMap;


import zsx.com.zhangsx20190102.bean.ProsBean;
import zsx.com.zhangsx20190102.net.RequestCallBack;

public interface MyContract {
    public  abstract class Mypresenter{
        public abstract void pro(HashMap<String,String> map);

    }

    public interface MyModel{
        void Pro(HashMap<String, String> map, String str, RequestCallBack requestCallBack);
    }
    public  interface  MyView{
        public void success(ProsBean proBean);
        public void fail(String str);
    }
}
