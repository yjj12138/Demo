package com.util;

import com.github.pagehelper.PageInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Pagetag extends SimpleTagSupport {
    private String url;
    private PageInfo pageInfo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if(url.indexOf("?") != -1) {
            url = url + "&";
        }else {
            url = url + "?";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<a style='text-decoration: none;' href='"+url+"&size="+pageInfo.getPageSize()+"'>首页</a>&nbsp;&nbsp;");
        stringBuffer.append("<a style='text-decoration: none;' href='"+url+"index="+(pageInfo.getPageNum()==1?1:pageInfo.getPageNum()-1)+"&size="+pageInfo.getPageSize()+"'>上一页</a>&nbsp;&nbsp;");
        for (int i=1;i<=pageInfo.getPages();i++){
            stringBuffer.append("<a style='text-decoration: none;' href='"+url+"index="+i+"&size="+pageInfo.getPageSize()+"'>"+i+"</a>&nbsp;&nbsp;");
        }
        stringBuffer.append("<a style='text-decoration: none;' href='"+url+"index="+(pageInfo.getPageNum()==pageInfo.getPages()?pageInfo.getPages():pageInfo.getPageNum()+1)+"&size="+pageInfo.getPageSize()+"'>下一页</a>&nbsp;&nbsp;");
        stringBuffer.append("<a style='text-decoration: none;' href='"+url+"index="+pageInfo.getPages()+"&size="+pageInfo.getPageSize()+"'>尾页</a>&nbsp;&nbsp;");
        stringBuffer.append("共"+pageInfo.getTotal()+"条 每页显示 "+pageInfo.getPageSize()+"&nbsp;"+pageInfo.getPageNum()+"/"+pageInfo.getPages());
        stringBuffer.append("<select id='psize'> name='size'");
        stringBuffer.append("<option value='5' "+(pageInfo.getPageSize()==5?"selected":"")+">5</option>");
        stringBuffer.append("<option value='10' "+(pageInfo.getPageSize()==10?"selected":"")+">10</option>");
        stringBuffer.append("<option value='15' "+(pageInfo.getPageSize()==15?"selected":"")+">15</option>");
        stringBuffer.append("</select>");
        this.getJspContext().getOut().print(stringBuffer.toString());
    }
}
