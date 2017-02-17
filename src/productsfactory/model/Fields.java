/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

/**
 *
 * @author Daniel Paulo
 */
public class Fields {
    
    private String OsDesc;
    private String DescFuncional;
    private String OperatorID;
    private String DlServiceCode;
    private String DlUpdateStamp;
    private String OsNumber;
    private String UserName;

    public String getOsNumber() {
        return this.getDlServiceCode()+this.getDlUpdateStamp();
    }

    public void setOsNumber(String OsNumber) {
        this.OsNumber = OsNumber;
    }

    public String getOsDesc() {
        return OsDesc;
    }

    public void setOsDesc(String OsDesc) {
        this.OsDesc = OsDesc;
    }

    public String getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(String OperatorID) {
        this.OperatorID = OperatorID;
    }

    public String getDlServiceCode() {
        return DlServiceCode;
    }

    public void setDlServiceCode(String DlServiceCode) {
        this.DlServiceCode = DlServiceCode;
    }

    public String getDlUpdateStamp() {
        return DlUpdateStamp;
    }

    public void setDlUpdateStamp(String DlUpdateStamp) {
        this.DlUpdateStamp = DlUpdateStamp;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getDescFuncional() {
        return DescFuncional;
    }

    public void setDescFuncional(String DescFuncional) {
        this.DescFuncional = DescFuncional;
    }
    
    
}
