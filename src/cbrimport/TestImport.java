package cbrimport;

import java.io.IOException;
import java.util.List;

public class TestImport {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ImportData load = new ImportData();
		List<Double> list = load.load_data_coefficient_tips("c:\\tip.txt");
		for(Double it:list)
			System.out.println(it);
			
	}

}
