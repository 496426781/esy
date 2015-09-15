
package cn.ecailan.esy.protocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ry8.external.activeandroid.Model;
import org.ry8.external.activeandroid.annotation.Column;
import org.ry8.external.activeandroid.annotation.Table;

@Table(name = "STATUS")
public class STATUS extends Model
{

     @Column(name = "succeed")
     public int succeed;

     @Column(name = "error_code")
     public int error_code;

     @Column(name = "error_desc")
     public String error_desc;

 public  void fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return ;
      }

     JSONArray subItemArray;

     this.succeed = jsonObject.optInt("succeed");

     this.error_code = jsonObject.optInt("error_code");

     this.error_desc = jsonObject.optString("error_desc");
     return ;
 }
 
 public  void fromJson(int succeed)  throws JSONException
 {
     this.succeed = 1;
     return ;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("succeed", succeed);
     localItemObject.put("error_code", error_code);
     localItemObject.put("error_desc", error_desc);
     return localItemObject;
 }

}
