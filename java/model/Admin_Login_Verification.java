package model;

public class Admin_Login_Verification {
    
    private String  adminid;
    private String adminpassword;
    public Admin_Login_Verification(String adminid, String adminpassword)
    {
        this.adminid = adminid;
        this.adminpassword = adminpassword;
    }
    
    public boolean verifyAdmin()
    {
        if(adminid.trim().equalsIgnoreCase("Admin") && adminpassword.equals("admin"))
        {
            System.out.println("Admin Credentials Valid!");
            return true;
        }
        return false;
    }
}
