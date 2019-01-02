package zsx.com.zhangsx20190102.presenter;

import java.util.HashMap;

import zsx.com.zhangsx20190102.api.ProApi;
import zsx.com.zhangsx20190102.bean.ProsBean;
import zsx.com.zhangsx20190102.contract.MyContract;
import zsx.com.zhangsx20190102.model.MyModel;
import zsx.com.zhangsx20190102.net.RequestCallBack;


public class Mypresenter extends MyContract.Mypresenter {
  private MyModel myModel;
  private MyContract.MyView myView;

    public Mypresenter(MyContract.MyView myView){
        myModel=   new MyModel();
        this.myView = myView;
    }
    @Override
    public void pro(HashMap<String, String> map) {
           myModel.Pro(map, ProApi.PRO_API, new RequestCallBack() {
               @Override
               public void onfair(String msg) {
                    myView.fail(msg);
               }

               @Override
               public void onsuccess(ProsBean proBean) {
                  myView.success(proBean);
               }
           });
    }
    public void destroy(){

        if (myView!=null){
            myView = null;
        }

    }
}
