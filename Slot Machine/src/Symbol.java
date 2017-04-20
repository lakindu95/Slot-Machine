public class Symbol implements ISymbol{

    private String image=null;
    private int value;

    public Symbol(String image,int value){
        setImage(image);
        setValue(value);
    }
    public Symbol(){
    	setImage(image);
    	setValue(value);
    }
    public void setImage(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }
}
