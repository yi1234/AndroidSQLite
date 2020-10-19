package cn.hhe.androidsqlite.ui.main;

/**
 * Create By zhongwen
 * on 2020/10/19
 */
public class FeedBean {
    public String title;
    public String subTitle;
    public String age;
    public String sex;

    public FeedBean(String title, String subTitle, String age) {
        this.title = title;
        this.subTitle = subTitle;
        this.age = age;
    }

    public FeedBean(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public FeedBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
