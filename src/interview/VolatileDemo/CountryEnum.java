package interview.VolatileDemo;

public enum CountryEnum {
    one(1,"齐国"),
    TWO(2,"楚国"),
    THREE(3,"燕国"),
    FOUR(4,"赵国"),
    FIVE(5,"魏国"),
    SIX(6,"韩国")
    ;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum foreachCountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element:
             values) {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }

    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
}
