package com.example.order;


import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.io.OutputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.ArrayList;  
  
public class HttpConnSoap {  
    public ArrayList<String> GetWebServre(String methodName, ArrayList<String> Parameters, ArrayList<String> ParValues) {  
        ArrayList<String> Values = new ArrayList<String>();  
          
        //ServerUrl��ָwebservice��url  
        //10.0.2.2����androidģ�������ʱ��أ�PC��������������д��127.0.0.1  
        //11125��ָ�˿ںţ������ص�IIS�ϵ�ʱ�����Ķ˿�  
        //Service1.asmx��ָ�ṩ�����ҳ��  
 //           String ServerUrl = "http://10.0.2.2:38464/Service1.asmx";  
        //    String ServerUrl = "http://172.26.22.138:38464/Service1.asmx"; 
//        String ServerUrl = "http://qxw1147090001.my3w.com/Service1.asmx";
        String ServerUrl = "http://192.168.43.128:38463/Service1.asmx";
        //     String ServerUrl = "http://172.27.3.198:38464/Service1.asmx";
        //                 String ServerUrl = "http://192.168.43.128:38464/Service1.asmx"; 
        //String soapAction="http://tempuri.org/LongUserId1";  
        String soapAction = "http://tempuri.org/" + methodName;  
        //String data = "";  
        String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
                + "<soap:Body />";  
        String tps, vps, ts;  
        String mreakString = "";  
        //<inser_user xmlns="http://tempuri.org/">">
        mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">";  
        for (int i = 0; i < Parameters.size(); i++) {  
            tps = Parameters.get(i).toString();  
            //���ø÷����Ĳ���Ϊ.net webService�еĲ�������  
            vps = ParValues.get(i).toString();  
            //ts = <User_Name>"User_Name"+</User_Name>
            ts = "<" + tps + ">" + vps + "</" + tps + ">";  
            mreakString = mreakString + ts;  
        }  
        mreakString = mreakString + "</" + methodName + ">";  
        /* 
        +"<HelloWorld xmlns=\"http://tempuri.org/\">" 
        +"<x>string11661</x>" 
        +"<SF1>string111</SF1>" 
        + "</HelloWorld>" 
        */  
        String soap2 = "</soap:Envelope>";  
        String requestData = soap + mreakString + soap2;  
        //System.out.println(requestData);  
  
        try {  
            URL url = new URL(ServerUrl);  
            HttpURLConnection con = (HttpURLConnection) url.openConnection();  
            byte[] bytes = requestData.getBytes("utf-8");  
            
            con.setDoInput(true);  
            con.setDoOutput(true);  
            con.setUseCaches(false);  
            con.setConnectTimeout(6000);// ���ó�ʱʱ��  
            con.setRequestMethod("POST");  
            con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");  
            con.setRequestProperty("SOAPAction", soapAction);  
            con.setRequestProperty("Content-Length", "" + bytes.length);  
            
            System.out.println("׼����������");
            OutputStream outStream = con.getOutputStream();  
            outStream.write(bytes);  
            outStream.flush();  
            outStream.close();  
            
            System.out.println("׼���������");
            InputStream inStream = con.getInputStream();  
            
            //data=parser(inStream);  
            //System.out.print("11");  
            Values = inputStreamtovaluelist(inStream, methodName);  
            System.out.println("Values------------->>"+Values.size()+ Values.toString());  
            return Values;  
  
        } catch (Exception e) {  
            System.out.println("2221");  
            e.printStackTrace();
            return null;  
        }  
    }  
  
    public ArrayList<String> inputStreamtovaluelist(InputStream in, String MonthsName) throws IOException {  
    	StringBuffer out = new StringBuffer();  
        String s1 = "";  
        //���ַ����ֽڶ�ȡ������ֶ�ȡ������������ַ�����һ�ζ�ȡ���Ӷ����ֲ����������
//        byte[] b = new byte[4096];  
//        ArrayList<String> Values = new ArrayList<String>();  
//        Values.clear();  
//        //�����ݸ��ƽ�out��s1
//        for (int n; (n = in.read(b)) != -1;) {  
//            s1 = new String(b, 0, n);  
//            //��s1����out����
//            out.append(s1);  
//        }  
        ArrayList<String> Values = new ArrayList<String>();  
      Values.clear();  
      
       
        InputStreamReader isr=new InputStreamReader(in);    
        char buf[] = new char[20];    
        int nBufLen = isr.read(buf);    
        while(nBufLen!=-1){    
            out.append(new String(buf, 0, nBufLen));    
            nBufLen = isr.read(buf);    
        }    
       
        System.out.println("OUT----->>" + out);  
        System.out.println("s1----->>" + s1); 
        //��outת����si
        //s1�������ԡ�><��Ϊ�ָ����ţ��ֱ����s13�ĸ���������
        s1 = out.toString();
        String[] s13 = s1.split("><");  
        String ifString = MonthsName + "Result";  
       // System.out.println("ifString----->>" + ifString);
        String TS = "";  
        String vs = "";  
        System.out.println("s13lenght----->>" + s13.length);
        //�����صĳ���Ϊ8�����ص����ݶ��в��������ݿ���ַ��Σ�������ѯ�������
        if(s13.length <= 8){
        	Values.clear();
        	System.out.println("Values.size-------->>"+ Values.size());
        	return Values;
        }
        Boolean getValueBoolean = false;  
        for (int i = 0; i < s13.length; i++) {
        	TS = s13[i];  
        	System.out.println("TS = s13[i]-------->>"+ TS +"i="+ i );
            int j, k, l;  
            j = TS.indexOf(ifString);  
            //IFSTRING�����ֵ�λ��
            k = TS.lastIndexOf(ifString);  
  
            if (j >= 0) {  
            	System.out.println("TS---->"+TS);  
                System.out.println("j and k and ifstring------��"+j+"  "+k+"  " + ifString);  
                if (getValueBoolean == false) {  
                    getValueBoolean = true;  
                } else {  
  
                }  
  
                if ((j >= 0) && (k > j)) {  
                    System.out.println("FFF" + TS.lastIndexOf("/" + ifString));  
                    //System.out.println(TS);  
                    l = ifString.length() + 1;  
                    vs = TS.substring(j + l, k - 2);  
                    //System.out.println("fff"+vs);  
                    Values.add(vs);  
                    System.out.println("�˳�" + vs);  
                    getValueBoolean = false;  
                    return Values;  
                }  
  
            }  
            if (TS.lastIndexOf("/" + ifString) >= 0) {  
                getValueBoolean = false;  
                System.out.println(" getValueBoolean = false"); 
                System.out.println("0.5Values string-------->>"+ Values.size());
                return Values;  
            }  
            if ((getValueBoolean) && (TS.lastIndexOf("/" + ifString) < 0) && (j < 0)) {  
                k = TS.length();  
                //System.out.println(TS);  
                vs = TS.substring(7, k - 8);  
                System.out.println("ffffffffffff"+vs);  
                Values.add(vs);  
            }  
  
        }  
        System.out.println("0.5Values string-------->>"+ Values.size());
        return Values;  
    }  
  
}  