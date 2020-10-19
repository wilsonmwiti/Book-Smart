package Model;

public class Category {
    private String catname;
    private String cardimg;

    public void Category(){

    }

    public void Category(String name,String image){
        this.catname=name;
        this.cardimg=image;
    }

    public String getCatname(){
        return catname;
    }

    public void setCatname(String name) {
        this.catname = name;
    }

    public void setCardimg(String image) {
        this.cardimg = image;
    }

    public String getCardimg(){
        return cardimg;
    }
}
