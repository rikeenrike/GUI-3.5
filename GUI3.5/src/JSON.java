import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSON {
    static JSONObject folder;
    static JSONArray list;
    static JSONObject info;
    static JSONParser jsonParser;
    static {
        jsonParser = new JSONParser();
        folder = new JSONObject();
        list = new JSONArray();
        info = new JSONObject();
    }
    
   static JSONArray reader() throws IOException, ParseException, org.json.simple.parser.ParseException{
        FileReader reader = new FileReader("C:\\Users\\enriq\\Desktop\\REFINED\\GUI3.0\\src\\z.STUDLIST.json");
        if (reader.ready()) {
            Scanner collect = new Scanner(reader);
            String line = "";
            while(collect.hasNext()){
                line = line + collect.nextLine();
            }
            if (!line.equals("")) {
                reader.close();
                FileReader reader2 = new FileReader("C:\\Users\\enriq\\Desktop\\REFINED\\GUI3.0\\src\\z.STUDLIST.json");
                folder = (JSONObject) jsonParser.parse(reader2);
                list = (JSONArray) folder.get("folder");
            }
        }
        reader.close();
        return list;
    }
    static void writer() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\enriq\\Desktop\\REFINED\\GUI3.0\\src\\z.STUDLIST.json");
        writer.write(folder.toJSONString());
        writer.close();
        
    }
}