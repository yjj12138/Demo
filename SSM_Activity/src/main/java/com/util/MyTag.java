package com.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class MyTag extends SimpleTagSupport {
    private String value;
    private Boolean test;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = this.getJspContext().getOut();
        //得到标签对中间的内容
        StringWriter stringWriter = new StringWriter();
        this.getJspBody().invoke(stringWriter);
        //输出内容
        writer.print(stringWriter + "~~" + value + "~~" + test);
    }
}
