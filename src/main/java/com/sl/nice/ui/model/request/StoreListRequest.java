package com.sl.nice.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StoreListRequest {

    private int offset;
    private int count;
    private String member_id;
    private String company_flag;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCompany_flag() {
        return company_flag;
    }

    public void setCompany_flag(String company_flag) {
        this.company_flag = company_flag;
    }
}
