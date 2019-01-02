package zsx.com.zhangsx20190102.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.zhangsx20190102.R;
import zsx.com.zhangsx20190102.adapter.ProductAdapter;
import zsx.com.zhangsx20190102.bean.ProsBean;
import zsx.com.zhangsx20190102.contract.MyContract;
import zsx.com.zhangsx20190102.presenter.Mypresenter;


public class ProductActivity extends AppCompatActivity implements MyContract.MyView {
    private List<ProsBean.Datas.MiaoSha.Lists> list;
    private ProductAdapter productAdapter;

    @BindView(R.id.pv_product)
    XRecyclerView recyclerView;
    private Mypresenter mypresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
       initView();
       initData();

        xrecy();
    }



    private void xrecy() {
        HashMap<String,String> par = new HashMap<>();
        par.put("","");

        mypresenter.pro(par);
    }

    private void initData() {

        list = new ArrayList<>();
        productAdapter = new ProductAdapter(this,list);
        recyclerView.setAdapter(productAdapter);
        productAdapter.setItemListener(new ProductAdapter.ItemListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                Toast.makeText(ProductActivity.this,"点击事件",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(int pos, View view) {
                Toast.makeText(ProductActivity.this,"长按事件:"+pos,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mypresenter = new Mypresenter(this);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,1,false));
    }

    @Override
    public void success(ProsBean proBean) {
       // productAdapter.setList(proBean.data.miaosha);
    }

    @Override
    public void fail(String str) {
        Toast.makeText(ProductActivity.this,"22",Toast.LENGTH_SHORT).show();
    }
}
