package com.example.order;
import java.sql.Connection;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
  
public class DBUtil {  
    private ArrayList<String> arrayList = new ArrayList<String>();  
    private ArrayList<String> brrayList = new ArrayList<String>();  
    private ArrayList<String> crrayList = new ArrayList<String>();  
    private HttpConnSoap Soap = new HttpConnSoap();  
  
    public static Connection getConnection() {  
        Connection con = null;  
        try {  
            //Class.forName("org.gjt.mm.mysql.Driver");  
            //con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");               
        } catch (Exception e) {  
            //e.printStackTrace();  
        }  
        return con;  
    }  
  
    
    //---------------1换台到2
    
    
     public void User_Change(String User_Name1, String User_Name2) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("User_Name1"); 
            arrayList.add("User_Name2"); 
         
            brrayList.add(User_Name1);
            brrayList.add(User_Name2); 
           
              
            Soap.GetWebServre("User_Change", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  User_Change");
        }  
    
    //------------------------用户表
    /** 
     * 修改用户状态 
     *  
     * @return 
     */  
    
    public void Update_Logged(String User_Name,String Logged_in,String Note) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("User_Name"); 
            arrayList.add("Logged_in"); 
            arrayList.add("Note");
         
            brrayList.add(User_Name);
            brrayList.add(Logged_in); 
            brrayList.add(Note); 
           
              
            Soap.GetWebServre("Update_Logged", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  Update_Logged");
        }  
 
    
    /** 
    * 修改用户类型 
    *  
    * @return 
    */  
   
   public void Update_Type(String User_Name, String User_Type,String Memo) {  
   		  
           arrayList.clear();  
           brrayList.clear();  
             
           arrayList.add("User_Name"); 
           arrayList.add("User_Type"); 
           arrayList.add("Memo"); 
          
        
           brrayList.add(User_Name);
           brrayList.add(User_Type); 
           brrayList.add(Memo); 
         
          
             
           Soap.GetWebServre("Update_Type", arrayList, brrayList);  
           System.out.println("------->>DBUTIL  Update_Type");
       }  
    
    /** 
     * 获取所有客户的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectAllUser() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        
        crrayList = Soap.GetWebServre("selectAllUser", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "用户ID");  
//        tempHash.put("User_Name", "用户名	");  
//        tempHash.put("User_Password", "用户密码");  
//        tempHash.put("User_Type", "用户类型"); 
//        tempHash.put("Logged_in", "状态"); 
//        tempHash.put("Note", "备注"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 7) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("User_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("User_Password", crrayList.get(j + 2));  
            hashMap.put("User_Type", crrayList.get(j + 3));  
            hashMap.put("Logged_in", crrayList.get(j + 4));
            hashMap.put("Note", crrayList.get(j + 5));
            hashMap.put("Memo", crrayList.get(j + 6));
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    
    /** 
     * 获取某客户的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> getUser(String User_Name) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("User_Name"); 
        brrayList.add(User_Name);
        
        crrayList = Soap.GetWebServre("selectUser", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  
//
//        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "用户ID");  
//        tempHash.put("User_Name", "用户名	");  
//        tempHash.put("User_Password", "用户密码");  
//        tempHash.put("User_Type", "用户类型"); 
//        tempHash.put("Logged_in", "状态"); 
//        tempHash.put("Note", "备注"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 7) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("User_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("User_Password", crrayList.get(j + 2));  
            hashMap.put("User_Type", crrayList.get(j + 3));  
            hashMap.put("Logged_in", crrayList.get(j + 4));
            hashMap.put("Note", crrayList.get(j + 5));
            hashMap.put("Memo", crrayList.get(j + 6));
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
 
    
    /** 
     * 获取某类型客户的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> getKindsofUser(String User_Type) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("User_Type"); 
        brrayList.add(User_Type);
        
        crrayList = Soap.GetWebServre("selectKindsofUser", arrayList, brrayList);  
       System.out.println("selectKindsofUser  crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "用户ID");  
//        tempHash.put("User_Name", "用户名");  
//        tempHash.put("User_Password", "用户密码");  
//        tempHash.put("User_Type", "用户类型"); 
//        tempHash.put("Logged_in", "状态"); 
//        tempHash.put("Note", "备注"); 
//        list.add(tempHash);  
//        
       
        for (int j = 0; j < crrayList.size(); j += 7) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("User_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("User_Password", crrayList.get(j + 2));  
            hashMap.put("User_Type", crrayList.get(j + 3));  
            hashMap.put("Logged_in", crrayList.get(j + 4));
            hashMap.put("Note", crrayList.get(j + 5));
            hashMap.put("Memo", crrayList.get(j + 6));
            list.add(hashMap);  
        }  
        System.out.println("DBUTIL,getKindsofUser,list----->>"+list.toString());
        return list;  
    }  
    /** 
     * 增加一客户信息 
     *  
     * @return 
     */  
    public void insert_User(String User_Name,String User_Password, String User_Type, String Logged_in,String Memo) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("User_Name"); 
        arrayList.add("User_Password");
        arrayList.add("User_Type");  
        arrayList.add("Logged_in");
        arrayList.add("Memo"); 
       
        brrayList.add(User_Name);
        brrayList.add(User_Password);
        brrayList.add(User_Type);  
        brrayList.add(Logged_in); 
        brrayList.add(Memo); 
       
          
        Soap.GetWebServre("insert_User", arrayList, brrayList);  
        System.out.println("------->>DBUTIL按键插入数据");
    }  
      
    
    /** 
     * 删除一人员信息 
     *  
     * @return 
     */  
    
    public void delete_Users(String User_Name) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("User_Name");  
        brrayList.add(User_Name);  
          
        Soap.GetWebServre("delete_Users", arrayList, brrayList);  
    }  
    //---------------------菜谱表
    /** 
     * 增加一菜谱信息 
     *  
     * @return 
     */  
    public void insert_Recipe(String R_Name, String R_Price, String R_Type) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("R_Name"); 
        arrayList.add("R_Price");
        arrayList.add("R_Type");  

       
        brrayList.add(R_Name);
        brrayList.add(R_Price);
        brrayList.add(R_Type);  
       
          
        Soap.GetWebServre("insert_Recipe", arrayList, brrayList);  
        System.out.println("------->>DBUTIL按键插入数据");
    }  
    
    /** 
     * 删除一条菜谱信息 
     *  
     * @return 
     */  
    
    public void delete_Recipe(String R_Name) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("R_Name");  
        brrayList.add(R_Name);  
          
        Soap.GetWebServre("delete_Recipe", arrayList, brrayList);  
    }  
    
    
    /** 
     * 才找某类型菜谱信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectKindsofReciper(String R_Type) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("R_Type"); 
        brrayList.add(R_Type);
        
        crrayList = Soap.GetWebServre("selectKindsofReciper", arrayList, brrayList);  
       System.out.println("selectKindsofReciper  crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "用户ID");  
//        tempHash.put("User_Name", "用户名");  
//        tempHash.put("User_Password", "用户密码");  
//        tempHash.put("User_Type", "用户类型"); 
//        tempHash.put("Logged_in", "状态"); 
//        tempHash.put("Note", "备注"); 
//        list.add(tempHash);  
//        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("R_ID", crrayList.get(j));  
            hashMap.put("R_Name", crrayList.get(j + 1));  
            hashMap.put("R_Price", crrayList.get(j + 2));  
            hashMap.put("R_Type", crrayList.get(j + 3));  
 
            list.add(hashMap);  
        }  
        System.out.println("DBUTIL,selectKindsofReciper,list----->>"+list.toString());
        return list;  
    }  
    
    /** 
     * 获取某菜谱的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectRecipe(String R_Name) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("R_Name"); 
        brrayList.add(R_Name);
        
        crrayList = Soap.GetWebServre("selectRecipe", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
        HashMap<String, String> tempHash = new HashMap<String, String>();  
        tempHash.put("R_ID", "R_ID");  
        tempHash.put("R_Name", "R_Name");  
        tempHash.put("R_Price", "R_Price");  
        tempHash.put("R_Type", "R_Type"); 
        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("User_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("User_Password", crrayList.get(j + 2));  
            hashMap.put("User_Type", crrayList.get(j + 3)); 
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
 
    
    
    /** 
     * 获取某菜谱分类
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectR_Type(){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
       
        
        crrayList = Soap.GetWebServre("selectR_Type", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("R_ID", "R_ID");  
//        tempHash.put("R_Name", "R_Name");  
//        tempHash.put("R_Price", "R_Price");  
//        tempHash.put("R_Type", "R_Type"); 
//        list.add(tempHash);  
//        
       
        for (int j = 0; j < crrayList.size(); j += 1) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("R_Type", crrayList.get(j));  
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    //------------------------点餐表
    
    /** 
     * 增加一订餐信息 
     *  
     * @return 
     */  
    public void insert_Order(String R_ID, String User_Name,String R_Price,String R_Name) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("R_ID"); 
        arrayList.add("User_Name");
        arrayList.add("R_Price");
        arrayList.add("R_Name");
       

       
        brrayList.add(R_ID);
        brrayList.add(User_Name);
        brrayList.add(R_Price);
        brrayList.add(R_Name);
       
       
          
        Soap.GetWebServre("insert_Order", arrayList, brrayList);  
        System.out.println("------->>DBUTIL  insert_Order");
    }  
    
    
    /** 
     * 删除一条订餐信息 
     *  
     * @return 
     */  
    
    public void delete_Order(String O_ID) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("O_ID");  
        brrayList.add(O_ID);  
          
        Soap.GetWebServre("delete_Order", arrayList, brrayList);  
    }  
    
    
    /** 
     * 获取某菜谱的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectOrder(String User_Name) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("User_Name"); 
        brrayList.add(User_Name);
        
        crrayList = Soap.GetWebServre("selectOrder", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("O_ID", "O_ID");  
//        tempHash.put("R_ID", "R_ID");  
//        tempHash.put("User_Name", "User_Name");  
//        tempHash.put("O_Pay", "O_Pay"); 
//        tempHash.put("O_Cook", "O_Cook"); 
//        tempHash.put("R_Price", "R_Price"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 10) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("O_ID", crrayList.get(j));  
            hashMap.put("R_ID", crrayList.get(j + 1));  
            hashMap.put("User_Name", crrayList.get(j + 2));  
            hashMap.put("O_Pay", crrayList.get(j + 3)); 
            hashMap.put("O_Cook", crrayList.get(j + 4)); 
            hashMap.put("R_Price", crrayList.get(j + 5)); 
            hashMap.put("R_Name", crrayList.get(j + 6)); 
            hashMap.put("Order_Time", crrayList.get(j + 7)); 
            hashMap.put("Cook_Time", crrayList.get(j + 8)); 
            hashMap.put("Sent_Time", crrayList.get(j + 9)); 
             
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    
    /** 
     * 获取已烹饪的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectCooked() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
//        
//        arrayList.add("User_Name"); 
//        brrayList.add(User_Name);
        
        crrayList = Soap.GetWebServre("selectCooked", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("O_ID", "O_ID");  
//        tempHash.put("R_ID", "R_ID");  
//        tempHash.put("User_Name", "User_Name");  
//        tempHash.put("O_Pay", "O_Pay"); 
//        tempHash.put("O_Cook", "O_Cook"); 
//        tempHash.put("R_Price", "R_Price"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 10) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("O_ID", crrayList.get(j));  
            hashMap.put("R_ID", crrayList.get(j + 1));  
            hashMap.put("User_Name", crrayList.get(j + 2));  
            hashMap.put("O_Pay", crrayList.get(j + 3)); 
            hashMap.put("O_Cook", crrayList.get(j + 4)); 
            hashMap.put("R_Price", crrayList.get(j + 5)); 
            hashMap.put("R_Name", crrayList.get(j + 6)); 
            hashMap.put("Order_Time", crrayList.get(j + 7)); 
            hashMap.put("Cook_Time", crrayList.get(j + 8)); 
            hashMap.put("Sent_Time", crrayList.get(j + 9)); 
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    /** 
     * 获取未烹饪的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectUnCook() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
//        
//        arrayList.add("User_Name"); 
//        brrayList.add(User_Name);
        
        crrayList = Soap.GetWebServre("selectUnCook", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("O_ID", "O_ID");  
//        tempHash.put("R_ID", "R_ID");  
//        tempHash.put("User_Name", "User_Name");  
//        tempHash.put("O_Pay", "O_Pay"); 
//        tempHash.put("O_Cook", "O_Cook"); 
//        tempHash.put("R_Price", "R_Price"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 10) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("O_ID", crrayList.get(j));  
            hashMap.put("R_ID", crrayList.get(j + 1));  
            hashMap.put("User_Name", crrayList.get(j + 2));  
            hashMap.put("O_Pay", crrayList.get(j + 3)); 
            hashMap.put("O_Cook", crrayList.get(j + 4)); 
            hashMap.put("R_Price", crrayList.get(j + 5)); 
            hashMap.put("R_Name", crrayList.get(j + 6)); 
            hashMap.put("Order_Time", crrayList.get(j + 7)); 
            hashMap.put("Cook_Time", crrayList.get(j + 8)); 
            hashMap.put("Sent_Time", crrayList.get(j + 9)); 
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    /** 
     * 修改用户菜谱付账 
     *  
     * @return 
     */  
    
    public void Update_Order(String User_Name) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("User_Name"); 

         
            brrayList.add(User_Name);
        
            
              
            Soap.GetWebServre("Update_Order", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  Update_Order");
        }  
    
    /** 
     * 修改烹饪状态 
     *  
     * @return 
     */  
    
    public void Update_Cook(String O_ID,String O_Cook) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("O_ID"); 
            arrayList.add("O_Cook"); 

         
            brrayList.add(O_ID);
            brrayList.add(O_Cook);
            
            Soap.GetWebServre("Update_Cook", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  Update_Cook  "  + O_ID);
        }  
    
    
    /** 
     * 获取已完成结账订单的信息  
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectO_Pay() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
//        
//        arrayList.add("User_Name"); 
//        brrayList.add(User_Name);
        
        crrayList = Soap.GetWebServre("selectO_Pay", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  


       
        for (int j = 0; j < crrayList.size(); j += 10) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("O_ID", crrayList.get(j));  
            hashMap.put("R_ID", crrayList.get(j + 1));  
            hashMap.put("User_Name", crrayList.get(j + 2));  
            hashMap.put("O_Pay", crrayList.get(j + 3)); 
            hashMap.put("O_Cook", crrayList.get(j + 4)); 
            hashMap.put("R_Price", crrayList.get(j + 5)); 
            hashMap.put("R_Name", crrayList.get(j + 6)); 
            hashMap.put("Order_Time", crrayList.get(j + 7)); 
            hashMap.put("Cook_Time", crrayList.get(j + 8)); 
            hashMap.put("Sent_Time", crrayList.get(j + 9)); 
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    

    /** 
     * 删除所有已完成订餐信息   
     *  
     * @return 
     */  
    
    public void deleteAll_Order() {  
  
        arrayList.clear();  
        brrayList.clear();  
          

        Soap.GetWebServre("deleteAll_Order", arrayList, brrayList);  
    }  
    
    //----------------------结账表
    
    
    

    /** 
     * 增加一结账信息 
     *  
     * @return 
     */  
    public void insert_Pay(String User_Name, String P_Amount) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        
        arrayList.add("User_Name");
        arrayList.add("P_Amount");
        

        brrayList.add(User_Name);
        brrayList.add(P_Amount);
       
          
        Soap.GetWebServre("insert_Pay", arrayList, brrayList);  
        System.out.println("------->>DBUTIL  insert_Pay");
    }  
    
    /** 
     * 修改结账状态 
     *  
     * @return 
     */  
    
    public void Update_Pay(String User_Name) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("User_Name"); 

         
            brrayList.add(User_Name);
        
            
              
            Soap.GetWebServre("Update_Pay", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  Update_Pay");
        }  
    
    
    /** 
     * 获取未烹饪的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectPay() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
//        
//        arrayList.add("User_Name"); 
//        brrayList.add(User_Name);
   //     System.out.println("DBUTIL,selectPay,list----->>"+arrayList.toString()+"   "+brrayList.toString());
        crrayList = Soap.GetWebServre("selectPay", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("O_ID", "O_ID");  
//        tempHash.put("R_ID", "R_ID");  
//        tempHash.put("User_Name", "User_Name");  
//        tempHash.put("O_Pay", "O_Pay"); 
//        tempHash.put("O_Cook", "O_Cook"); 
//        tempHash.put("R_Price", "R_Price"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("P_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("P_Amount", crrayList.get(j + 2).substring(0,crrayList.get(j+2).length()-2));  
            hashMap.put("P_State", crrayList.get(j + 3));
            list.add(hashMap);  
        }  
        System.out.println("DBUTIL,selectPay,list----->>"+list.toString());
        return list;  
    }  
    
    /** 
     * 获取未烹饪的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectAllPay(){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
//        
//        arrayList.add("User_Name"); 
//        brrayList.add(User_Name);
   //     System.out.println("DBUTIL,selectPay,list----->>"+arrayList.toString()+"   "+brrayList.toString());
        crrayList = Soap.GetWebServre("selectAllPay", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("P_ID", crrayList.get(j));  
            hashMap.put("User_Name", crrayList.get(j + 1));  
            hashMap.put("P_Amount", crrayList.get(j + 2).substring(0,crrayList.get(j+2).length()-2));  
            hashMap.put("P_State", crrayList.get(j + 3));
            list.add(hashMap);  
        }  
        System.out.println("DBUTIL,selectPay,list----->>"+list.toString());
        return list;  
    }  
    
    /** 
     * 删除所有已结帐
     *  
     * @return 
     */  
    
    public void delete_Pay() {  
  
        arrayList.clear();  
        brrayList.clear();  

          
        Soap.GetWebServre("delete_Pay", arrayList, brrayList);  
    }  
    
    
    //-----------------------人员表
    
    /** 
     * 修改人员密码
     *  
     * @return 
     */  
    
    public void Update_Personnel(String P_Name, String P_Password, String P_Function) {  
    		  
            arrayList.clear();  
            brrayList.clear();  
              
            arrayList.add("P_Name"); 
            arrayList.add("P_Password"); 
            arrayList.add("P_Function"); 
         
            brrayList.add(P_Name);
            brrayList.add(P_Password); 
            brrayList.add(P_Function); 
           
              
            Soap.GetWebServre("Update_Personnel", arrayList, brrayList);  
            System.out.println("------->>DBUTIL  Update_Personnel");
        }  
 
  
 
    /** 
     * 获取某人员的信息 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectPersonnel(String P_Name) {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("P_Name"); 
        brrayList.add(P_Name);
        
        crrayList = Soap.GetWebServre("selectPersonnel", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("ID", "ID");  
//        tempHash.put("P_Name", "P_Name	");  
//        tempHash.put("P_Password", "P_Password");  
//        tempHash.put("P_Function", "P_Function"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("ID", crrayList.get(j));  
            hashMap.put("P_Name", crrayList.get(j + 1));  
            hashMap.put("P_Password", crrayList.get(j + 2));  
            hashMap.put("P_Function", crrayList.get(j + 3));  
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
 
    /** 
     * 获取所有人员的信息
     *  
     * @return 
     */  
    public List<HashMap<String, String>> selectAllPersonnel() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        
        crrayList = Soap.GetWebServre("selectAllPersonnel", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("ID", "ID");  
//        tempHash.put("P_Name", "P_Name	");  
//        tempHash.put("P_Password", "P_Password");  
//        tempHash.put("P_Function", "P_Function"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("ID", crrayList.get(j));  
            hashMap.put("P_Name", crrayList.get(j + 1));  
            hashMap.put("P_Password", crrayList.get(j + 2));  
            hashMap.put("P_Function", crrayList.get(j + 3));  
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    /** 
     * 获取某类型人员的信息
     *  
     * @return 
     */  
    public List<HashMap<String, String>> selectKindsofPersonnel(String P_Function){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        arrayList.add("P_Function"); 
        brrayList.add(P_Function);
        
        crrayList = Soap.GetWebServre("selectKindsofPersonnel", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("ID", "ID");  
//        tempHash.put("P_Name", "P_Name	");  
//        tempHash.put("P_Password", "P_Password");  
//        tempHash.put("P_Function", "P_Function"); 
//        list.add(tempHash);  
        
       
        for (int j = 0; j < crrayList.size(); j += 4) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("ID", crrayList.get(j));  
            hashMap.put("P_Name", crrayList.get(j + 1));  
            hashMap.put("P_Password", crrayList.get(j + 2));  
            hashMap.put("P_Function", crrayList.get(j + 3));  
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    /** 
     * 增加一个人员信息
     *  
     * @return 
     */  
    public void insert_Personnel(String P_Name, String P_Password,String P_Function){  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("P_Name"); 
        arrayList.add("P_Password");
        arrayList.add("P_Function");  
       
        brrayList.add(P_Name);
        brrayList.add(P_Password);
        brrayList.add(P_Function);  
       
          
        Soap.GetWebServre("insert_Personnel", arrayList, brrayList);  
        System.out.println("-insert_Personnel------>>DBUTIL按键插入数据");
    }  
    
    /** 
     * 删除一人员信息 
     *  
     * @return 
     */  
    
    public void delete_Personnel(String P_Name) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("P_Name");  
        brrayList.add(P_Name);  
          
        Soap.GetWebServre("delete_Personnel", arrayList, brrayList);  
    }  
    
    
    //-------------------------建议表
    
    /** 
     * 增加一个人员信息
     *  
     * @return 
     */  
    public void insert_Suggestion(String S_suggestion){  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("S_suggestion"); 
       
        brrayList.add(S_suggestion);
       
          
        Soap.GetWebServre("insert_Suggestion", arrayList, brrayList);  
        System.out.println("-insert_Suggestion------>>DBUTIL按键插入数据");
    }  
    
    /** 
     * 删除一建议 
     *  
     * @return 
     */  
    
    public void delete_A_Suggestion(String S_ID) {  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("S_ID");  
        brrayList.add(S_ID);  
          
        Soap.GetWebServre("delete_A_Suggestion", arrayList, brrayList);  
    }  
    
    
    /** 
     * 删除所有建议
     *  
     * @return 
     */  
    
    public void delete_All_Suggestion() {  
  
        arrayList.clear();  
        brrayList.clear();  
          

          
        Soap.GetWebServre("delete_All_Suggestion", arrayList, brrayList);  
    }  
    
    /** 
     * 获取某类型人员的信息
     *  
     * @return 
     */  
    public List<HashMap<String, String>> selectSuggestion(){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        

        
        crrayList = Soap.GetWebServre("selectSuggestion", arrayList, brrayList);  
       //System.out.println("crraylist。SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  


        
       
        for (int j = 0; j < crrayList.size(); j += 2) {  
            HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("S_ID", crrayList.get(j));  
            hashMap.put("S_suggestion", crrayList.get(j + 1));   
            list.add(hashMap);  
        }  
      //  System.out.println("DBUTIL,list----->>"+list.toString());
        return list;  
    }  
    
    
    
    
}  