package au.com.telstra.simcardactivator;

public class ActivateSimRequest {
    private String iccid;
    private String customerEmail;

    public String getIccid(){
        return iccid;
    }
    public String getCustomerEmail(){
        return customerEmail;
    }
    public void setIccid(String iccid){
        this.iccid=iccid;
    }
    public void setCustomerEmail(String customerEmail){
        this.customerEmail=customerEmail;
    }
}
