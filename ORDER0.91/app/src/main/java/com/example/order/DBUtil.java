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
  
    
    //---------------1��̨��2
    
    
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
    
    //------------------------�û���
    /** 
     * �޸��û�״̬ 
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
    * �޸��û����� 
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
     * ��ȡ���пͻ�����Ϣ 
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectAllUser() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        
        crrayList = Soap.GetWebServre("selectAllUser", arrayList, brrayList);  
       //System.out.println("crraylist��SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "�û�ID");  
//        tempHash.put("User_Name", "�û���	");  
//        tempHash.put("User_Password", "�û�����");  
//        tempHash.put("User_Type", "�û�����"); 
//        tempHash.put("Logged_in", "״̬"); 
//        tempHash.put("Note", "��ע"); 
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
     * ��ȡĳ�ͻ�����Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  
//
//        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "�û�ID");  
//        tempHash.put("User_Name", "�û���	");  
//        tempHash.put("User_Password", "�û�����");  
//        tempHash.put("User_Type", "�û�����"); 
//        tempHash.put("Logged_in", "״̬"); 
//        tempHash.put("Note", "��ע"); 
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
     * ��ȡĳ���Ϳͻ�����Ϣ 
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
       System.out.println("selectKindsofUser  crraylist��SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "�û�ID");  
//        tempHash.put("User_Name", "�û���");  
//        tempHash.put("User_Password", "�û�����");  
//        tempHash.put("User_Type", "�û�����"); 
//        tempHash.put("Logged_in", "״̬"); 
//        tempHash.put("Note", "��ע"); 
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
     * ����һ�ͻ���Ϣ 
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
        System.out.println("------->>DBUTIL������������");
    }  
      
    
    /** 
     * ɾ��һ��Ա��Ϣ 
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
    //---------------------���ױ�
    /** 
     * ����һ������Ϣ 
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
        System.out.println("------->>DBUTIL������������");
    }  
    
    /** 
     * ɾ��һ��������Ϣ 
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
     * ����ĳ���Ͳ�����Ϣ 
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
       System.out.println("selectKindsofReciper  crraylist��SIZE = "+crrayList.size());
        arrayList.clear();  
        brrayList.clear();  

        
//        HashMap<String, String> tempHash = new HashMap<String, String>();  
//        tempHash.put("User_ID", "�û�ID");  
//        tempHash.put("User_Name", "�û���");  
//        tempHash.put("User_Password", "�û�����");  
//        tempHash.put("User_Type", "�û�����"); 
//        tempHash.put("Logged_in", "״̬"); 
//        tempHash.put("Note", "��ע"); 
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
     * ��ȡĳ���׵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡĳ���׷���
     *  
     * @return 
     */  
    
    public List<HashMap<String, String>> selectR_Type(){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
       
        
        crrayList = Soap.GetWebServre("selectR_Type", arrayList, brrayList);  
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
    
    //------------------------��ͱ�
    
    /** 
     * ����һ������Ϣ 
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
     * ɾ��һ��������Ϣ 
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
     * ��ȡĳ���׵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡ����⿵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡδ��⿵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * �޸��û����׸��� 
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
     * �޸����״̬ 
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
     * ��ȡ����ɽ��˶�������Ϣ  
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ɾ����������ɶ�����Ϣ   
     *  
     * @return 
     */  
    
    public void deleteAll_Order() {  
  
        arrayList.clear();  
        brrayList.clear();  
          

        Soap.GetWebServre("deleteAll_Order", arrayList, brrayList);  
    }  
    
    //----------------------���˱�
    
    
    

    /** 
     * ����һ������Ϣ 
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
     * �޸Ľ���״̬ 
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
     * ��ȡδ��⿵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡδ��⿵���Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ɾ�������ѽ���
     *  
     * @return 
     */  
    
    public void delete_Pay() {  
  
        arrayList.clear();  
        brrayList.clear();  

          
        Soap.GetWebServre("delete_Pay", arrayList, brrayList);  
    }  
    
    
    //-----------------------��Ա��
    
    /** 
     * �޸���Ա����
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
     * ��ȡĳ��Ա����Ϣ 
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡ������Ա����Ϣ
     *  
     * @return 
     */  
    public List<HashMap<String, String>> selectAllPersonnel() {  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        
        
        crrayList = Soap.GetWebServre("selectAllPersonnel", arrayList, brrayList);  
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ��ȡĳ������Ա����Ϣ
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
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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
     * ����һ����Ա��Ϣ
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
        System.out.println("-insert_Personnel------>>DBUTIL������������");
    }  
    
    /** 
     * ɾ��һ��Ա��Ϣ 
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
    
    
    //-------------------------�����
    
    /** 
     * ����һ����Ա��Ϣ
     *  
     * @return 
     */  
    public void insert_Suggestion(String S_suggestion){  
  
        arrayList.clear();  
        brrayList.clear();  
          
        arrayList.add("S_suggestion"); 
       
        brrayList.add(S_suggestion);
       
          
        Soap.GetWebServre("insert_Suggestion", arrayList, brrayList);  
        System.out.println("-insert_Suggestion------>>DBUTIL������������");
    }  
    
    /** 
     * ɾ��һ���� 
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
     * ɾ�����н���
     *  
     * @return 
     */  
    
    public void delete_All_Suggestion() {  
  
        arrayList.clear();  
        brrayList.clear();  
          

          
        Soap.GetWebServre("delete_All_Suggestion", arrayList, brrayList);  
    }  
    
    /** 
     * ��ȡĳ������Ա����Ϣ
     *  
     * @return 
     */  
    public List<HashMap<String, String>> selectSuggestion(){  
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
  
        arrayList.clear();  
        brrayList.clear();  
        crrayList.clear();  
        

        
        crrayList = Soap.GetWebServre("selectSuggestion", arrayList, brrayList);  
       //System.out.println("crraylist��SIZE = "+crrayList.size());
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