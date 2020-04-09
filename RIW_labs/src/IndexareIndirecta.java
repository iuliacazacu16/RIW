import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

//import org.bson.Document;

//import com.mongodb.DBCursor;
//import com.mongodb.client.MongoCollection;

public class IndexareIndirecta {
	static Map<String, Map<String, Integer>> map_inv = new LinkedHashMap<String, Map<String, Integer>>();
	public static void  indexareIndirecta() throws IOException {

		FileReader input= new FileReader("E:\\FACULTATE\\An IV\\Sem II\\RIW\\RIW_LAB2\\RIW_labs\\indexDirect.txt");	
		StringBuilder sb = new StringBuilder();
		int c; 
		String index[]=new String[3];
		int flag=0;
		while ((c = input.read()) != -1)
		{
			if (c==' ' || c=='\n'|| c=='\r')
			{ 
				String newWord = sb.toString();
				if(sb.length()!=0)
				{	
					index[flag++]=newWord;
					if(flag==3)
					{
					InserareMap(index);
					flag=0;
					}
				}
				sb.setLength(0);


			}
			else
			{
				sb.append((char)c);
			}
		}
		input.close();
		afisare();
	}

	public static void InserareMap(String index[]) {
		Map<String, Integer> map_fis =null;
		if (!map_inv.containsKey(index[1])) {
			map_fis = new LinkedHashMap<String, Integer>();
	
			map_fis.put(index[0],Integer.parseInt(index[2]));
			map_inv.put(index[1],map_fis);

		}
		else {
			map_fis=map_inv.get(index[1]);
			map_fis.put(index[0],Integer.parseInt(index[2]));
			map_inv.put(index[1],map_fis);
		}
	}

	public static void afisare() throws IOException{
		Writer fis_out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\FACULTATE\\An IV\\Sem II\\RIW\\RIW_LAB2\\RIW_labs\\indexIndirect.txt")));
		map_inv.forEach((cuvant,map)->{                
			map.forEach((docum,nrAp)->{        
				try {
					fis_out.write(cuvant+" "+ docum+" "+nrAp);
					fis_out.write(System.lineSeparator());
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		});
		fis_out.close();
	}
}

