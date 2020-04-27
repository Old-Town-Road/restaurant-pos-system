package testing;

import java.util.LinkedHashMap;
import models.MenuItem;

public class scratch {

	public static void main(String[] args) {
		MenuItem pepPizza = new MenuItem(1, "Pepperoni Pizza", 13.99, 0, 0);
		String tname = pepPizza.getTableName();
		LinkedHashMap<String, String> kv = new LinkedHashMap<String,String>();
		try {
			kv.putAll(pepPizza.getDataKeyValuePairs());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
