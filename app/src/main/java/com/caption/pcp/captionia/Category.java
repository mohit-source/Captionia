package com.caption.pcp.captionia;

/**
 * Created by PCP on 24-12-2018.
 */

public class Category {

    private String Category_name;
    private String Image_url;

    public String getInsert()

    {
        return Category_name;
    }

    public void setInsert(String Category_name)
    {
        this.Category_name = Category_name;
    }

    public String getUrl()
    {
        return Image_url;
    }

    public void setUrl(String Image_url)
    {
        this.Image_url = Image_url;
    }

    public Category()
    {

    }
}
