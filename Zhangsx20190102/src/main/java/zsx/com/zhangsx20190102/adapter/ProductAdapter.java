package zsx.com.zhangsx20190102.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import zsx.com.zhangsx20190102.R;
import zsx.com.zhangsx20190102.bean.ProsBean;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {
   private Context context;
    private List<ProsBean.Datas.MiaoSha.Lists> list;

    public ProductAdapter(Context context, List<ProsBean.Datas.MiaoSha.Lists> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pro_item, viewGroup, false);
        ProductVH productVH = new ProductVH(view);
        return productVH;
    }


    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ProductVH productVH, int i) {

        ProsBean.Datas.MiaoSha.Lists product = list.get(i);
        productVH.title.setText(product.title);
        String images = product.images;
        String[] imagA= images.split("\\|");
        if (imagA !=null&&imagA.length >0){
            Glide.with(context).load(imagA[0]).into(productVH.iv);
        }else {
            productVH.iv.setImageResource(R.mipmap.ic_launcher);
        }
      productVH.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (itemListener!=null){
                  itemListener.onItemClickListener(productVH.getLayoutPosition(),v);
              }
          }
      });

        productVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemListener!=null){
                    itemListener.onItemLongClickListener(productVH.getLayoutPosition(),v);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null? 0 :list.size();
    }
  //添加数据
    public void setList(List<ProsBean.Datas.MiaoSha.Lists> data) {
        if (data!=null){
            this.list=data;
        }
        notifyDataSetChanged();
    }

    class ProductVH extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView title;

        public ProductVH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.tv_icon);
            title=itemView.findViewById(R.id.tv_title);
        }
    }


    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener{
        void onItemClickListener(int posi, View view);

        void onItemLongClickListener(int posi, View view);
    }

}
