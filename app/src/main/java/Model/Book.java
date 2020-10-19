package Model;

public class Book {
    private String bauth, bdesc,bgenreid,bimg,blink,bname,brating,brevno;

    public void Book(String bauth, String bdesc,String bgenreid,String bimg,String blink,String bname,String brating,String brevno){
        this.bauth=bauth;
        this.bdesc=bdesc;
        this.bgenreid=bgenreid;
        this.bimg=bimg;
        this.blink=blink;
        this.bname=bname;
        this.brating=brating;
        this.brevno=brevno;
    }
    public void Book(){

    }

    public String getBauth() {
        return bauth;
    }

    public String getBdesc() {
        return bdesc;
    }

    public String getBgenreid() {
        return bgenreid;
    }

    public String getBimg() {
        return bimg;
    }

    public String getBlink() {
        return blink;
    }

    public String getBname() {
        return bname;
    }

    public String getBrating() {
        return brating;
    }

    public String getBrevno() {
        return brevno;
    }

    public void setBauth(String bauth) {
        this.bauth = bauth;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    public void setBgenreid(String bgenreid) {
        this.bgenreid = bgenreid;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public void setBlink(String blink) {
        this.blink = blink;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBrating(String brating) {
        this.brating = brating;
    }

    public void setBrevno(String brevno) {
        this.brevno = brevno;
    }

}

