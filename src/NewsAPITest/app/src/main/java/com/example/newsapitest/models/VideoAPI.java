package com.example.newsapitest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * ... 视频API...
 */
public class VideoAPI {
    public static class number implements Serializable{
        public String thumbUrl;
        public String source;
        public String url;
        public String title;
        public String brief;
        public String avatar;

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
    public static class Showapi_res_body implements Serializable {
        public int ret_code;

        @SerializedName("0")
        public number number0;
        @SerializedName("1")
        public number number1;
        @SerializedName("2")
        public number number2;
        @SerializedName("3")
        public number number3;
        @SerializedName("4")
        public number number4;
        @SerializedName("5")
        public number number5;
        @SerializedName("6")
        public number number6;
        @SerializedName("7")
        public number number7;
        @SerializedName("8")
        public number number8;
        @SerializedName("9")
        public number number9;
        @SerializedName("10")
        public number number10;
        @SerializedName("11")
        public number number11;
        @SerializedName("12")
        public number number12;

        public number getNumber0() {
            return number0;
        }

        public void setNumber0(number number0) {
            this.number0 = number0;
        }

        public number getNumber1() {
            return number1;
        }

        public void setNumber1(number number1) {
            this.number1 = number1;
        }

        public number getNumber2() {
            return number2;
        }

        public void setNumber2(number number2) {
            this.number2 = number2;
        }

        public number getNumber3() {
            return number3;
        }

        public void setNumber3(number number3) {
            this.number3 = number3;
        }

        public number getNumber4() {
            return number4;
        }

        public void setNumber4(number number4) {
            this.number4 = number4;
        }

        public number getNumber5() {
            return number5;
        }

        public void setNumber5(number number5) {
            this.number5 = number5;
        }

        public number getNumber6() {
            return number6;
        }

        public void setNumber6(number number6) {
            this.number6 = number6;
        }

        public number getNumber7() {
            return number7;
        }

        public void setNumber7(number number7) {
            this.number7 = number7;
        }

        public number getNumber8() {
            return number8;
        }

        public void setNumber8(number number8) {
            this.number8 = number8;
        }

        public number getNumber9() {
            return number9;
        }

        public void setNumber9(number number9) {
            this.number9 = number9;
        }

        public number getNumber10() {
            return number10;
        }

        public void setNumber10(number number10) {
            this.number10 = number10;
        }

        public number getNumber11() {
            return number11;
        }

        public void setNumber11(number number11) {
            this.number11 = number11;
        }

        public number getNumber12() {
            return number12;
        }

        public void setNumber12(number number12) {
            this.number12 = number12;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

    }

    public Showapi_res_body showapi_res_body;
    public String showapi_res_error;
    public String showapi_res_id;
    public int showapi_res_code;

    public Showapi_res_body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }
}
