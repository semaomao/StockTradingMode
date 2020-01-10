import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/*
1时间	    
2开盘	   
3最高	   
4最低	   
5收盘	         
6成交量	
790 箱体1.MA1   	
890 箱体1.MA2   	
990 箱体1.MA3   	
1090 箱体1.MA4   	
1190 箱体1.箱顶  	
1290 箱体1.箱腰  	
1390 箱体1.箱低  	
1490 箱体1.高1   	
1590 箱体1.中1   	
1690 箱体1.低1   	
1790 箱体1.      	
1890 箱体1.      	
1990 箱体1.      	
2090 箱体1.      	
2190 箱体1.      	
2290 箱体1.      	
2390 箱体1.      	
2490 箱体1.      	
2590 箱体1.      	
2690 箱体1.      	
2790 箱体1.      	
2890 箱体1.      	
2990 箱体1.      	
3090 箱体1.      	
3190 箱体1.      	
3290 箱体1.      	
3390 箱体1.      	
3490 箱体1.      	
3590 箱体1.      	
3690 箱体1.      	
3790 箱体1.      	
38VOL-TDX.VVOL  	
39VOL-TDX.      	
40VOL-TDX.VOLUME	
41VOL-TDX.MAVOL1	
42VOL-TDX.MAVOL2	
4390_筹码.      	
4490_筹码.割肉  	
4590_筹码.分歧吸筹	
4690_筹码.大傻猫	
4790_筹码.聪明人	
4890_RSI.RSI9  	
4990_RSI.MA9   	
5090_RSI.      	   
51WR.WR1   	   
52WR.WR2   	  
53RSI.RSI1  	  
54RSI.RSI2  	  
55RSI.RSI3  	  
56KD.K     	   
57KD.D */
public class MainExcelOper {
	public static Log log = LogFactory.getLog(MainExcelOper.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String stockName="stockName";
		String stockCode="stockCode";
		System.out.print("===start==");
		StringBuilder txtValue = new StringBuilder();
		String filename="C:\\new_tdx\\T0002\\export\\600986.txt";
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(filename), "GBK");// 考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt ;
            
            int row=0;
            
            ArrayList<String[]> data_ls=new ArrayList<String[]>();//数据集

            
            while ((lineTxt = bufferedReader.readLine()) != null) {
            	
            	if (lineTxt.equals("")) continue;
                lineTxt += " \n";
                
                System.out.print("readTxt:" + lineTxt+ "");
                txtValue.append(lineTxt);
                
                //第一行 股票名字和代码
                if(lineTxt.contains("(") && lineTxt.contains(")")){
                	int pos=lineTxt.indexOf("(");
                	if(pos>0) {
	                	stockName=lineTxt.substring(0,pos).trim();
	                	stockCode=lineTxt.substring(pos+1,lineTxt.indexOf(")"));
	                	System.out.println("stockName:"+stockName);
	                	System.out.println("stockCode:"+stockCode);
	                	//break;
                	}
                }
                
                //第二行  字段
                if(lineTxt.contains("时间")) {
                	String[] header_name_array=lineTxt.split("\\t");
                	int count=0;
                	for(String name:header_name_array) {
                		if(!name.trim().equals("")) {
                			System.out.println("name["+count+++"]:"+name.trim()+"\n");
                		}
                	}
      
                	
                }else {
                
	                //第三行开始 就是数据
	                String[] data_array=lineTxt.split("\\t");
	                
	                if (data_array!=null && data_array.length>2) {
		            	int count=0;
		            	for(String name:data_array) {
		            		//if(!name.trim().equals("")) 
		            		{
		            			System.out.println("data["+count+++"]:"+name.trim()+"\n");
		            		}
		            	}
		            	
		            	data_ls.add(data_array);//保存到缓存
	                }
                }
            	//if(row>3) break;
            	
            	row++;
                
                
            }
            read.close();
            
            System.out.println(data_ls.size());
            

            List<Object> json_list = new ArrayList<Object>();
            for(String[] line_data:data_ls) {
            	
            	System.out.println(line_data[0]);
            	
            	
                
                Map<String, Object> agencyMap = new HashMap<>();
                agencyMap.put("stockName",line_data[0]);
                json_list.add(agencyMap);
                
                

            }
            
            //写入json文件
            String listJson = JSON.toJSONString(json_list);
            CreateFileUtil.createJsonFile(listJson, "c:\\test\\", "qiangcaimao");
            

            
            System.out.print("===end==");
        }catch (Exception e) {
            e.printStackTrace();
        } 
	}
}
