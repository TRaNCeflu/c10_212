package com.example.newsapitest.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * ... 评论集合...
 */
public class CommentLab {
   // private static CommentLab sCommentLab;
    int num_comment;
    private List<Comment> mComments;
  //  public static CommentLab get(Context context,int num){
        //if(sCommentLab == null)
   //         sCommentLab = new CommentLab(context,num);
  //      return sCommentLab;
  //  }

    public CommentLab(Context context, int num){
        mComments = new ArrayList<>();
        num_comment = num;

       for(int i=0;i<num_comment;i++)
           mComments.add(random());

    }
    private Comment random(){
        int j = (int) (Math.random()*11);
        switch(j) {
            case 0:
                return new Comment("爱迪生","上海市普陀区","666666","2016-10-26");
            case 1:
                return new Comment("微软小冰","内蒙古呼和浩特","祖国万岁","2016-7-20");

            case 2:
                return new Comment("小米小爱","海南省","今天我在寝室吃汉堡很大声，我很牛逼吗","2018-10-26");

            case 3:
                return new Comment("北极光","辽宁省","又是一片水文","2018-10-26");

            case 4:
                return new Comment("安德森","广州","小编是****","2018-7-21");

            case 5:
                return new Comment("大哥","辽宁省","哈哈哈","2017-6-1");

            case 6:
                return new Comment("火星网友","辽宁省","666666","2016-6-2");

            case 7:
                return new Comment("某局书记","辽宁省","蛮有趣的","2015-8-3");

            case 8:
                return new Comment("心悦会员","浙江省温州","支持国产","2018-7-4");

            case 9:
                return new Comment("张湖大","广东省东菀市","嘿嘿嘿","2017-6-26");

            case 10:
                return new Comment("撸猫大叔","北京市东城区","还不错","2015-8-5");
            case 11:
                return new Comment("张洪豪","天津市","真的是这样吗","2014-5-26");



        }
        return null;
    }
    public List<Comment> getComments() {
        return mComments;
    }

  public Comment getComment(UUID id){
        for ( Comment comment:mComments){
            if(comment.getId().equals(id)){
                return  comment;
            }
        }
        return null;
  }

}
