package com.pactera.model;

import java.util.Date;

public class TfUser extends TfUserKey {
    private String username;

    private String rtxno;

    private String email;

    private String mobil;

    private String sex;

    private Integer subcompanyid;

    private Integer departmentid;

    private String userno;

    private Date pwdlastset;

    private Integer useraccountcontrol;

    private Date pwdexpires;

    private String pwd;

    private String pwd2;

    private Integer accessfailedcount;

    private Integer status;

    private Date updateTime;

    private Integer jobid;

    private Integer isInternal;

    private Integer identity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRtxno() {
        return rtxno;
    }

    public void setRtxno(String rtxno) {
        this.rtxno = rtxno == null ? null : rtxno.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil == null ? null : mobil.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getSubcompanyid() {
        return subcompanyid;
    }

    public void setSubcompanyid(Integer subcompanyid) {
        this.subcompanyid = subcompanyid;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public Date getPwdlastset() {
        return pwdlastset;
    }

    public void setPwdlastset(Date pwdlastset) {
        this.pwdlastset = pwdlastset;
    }

    public Integer getUseraccountcontrol() {
        return useraccountcontrol;
    }

    public void setUseraccountcontrol(Integer useraccountcontrol) {
        this.useraccountcontrol = useraccountcontrol;
    }

    public Date getPwdexpires() {
        return pwdexpires;
    }

    public void setPwdexpires(Date pwdexpires) {
        this.pwdexpires = pwdexpires;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2 == null ? null : pwd2.trim();
    }

    public Integer getAccessfailedcount() {
        return accessfailedcount;
    }

    public void setAccessfailedcount(Integer accessfailedcount) {
        this.accessfailedcount = accessfailedcount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Integer isInternal) {
        this.isInternal = isInternal;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}