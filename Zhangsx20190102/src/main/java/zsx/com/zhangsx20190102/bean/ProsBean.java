package zsx.com.zhangsx20190102.bean;

import java.util.List;

public class ProsBean {
    public Datas  data;
    public String msg;


    public class Datas {
     public List<MiaoSha> miaosha;

        public class MiaoSha {
           public String name;
           public  String time;
           public List<Lists> list;

            public class Lists {
                public String images;
                public String title;
            }
        }
    }
}
