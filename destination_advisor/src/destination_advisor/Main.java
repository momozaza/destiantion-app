package destination_advisor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	
	// tsv file
	static File file ;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		
		InputStream inputstream = new FileInputStream("data/cities1000.txt");
		file = new File("data/cities.tsv");
		bw = new BufferedWriter(new FileWriter(file));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
		String line = null;
		while ((line = br.readLine()) != null) {
//			findGeonameId(line);
			extractData(line);
		}

		br.close();
		bw.close();
		
	}
	
	private static void extractData(String line) throws IOException{
		
		String cityId = null;
        String cityName = null;
        String lat = null;
        String lng = null;
        String[] split = line.split("\\t");
        cityId = split[0];
        cityName = split[2];
        lat = split[4];
        lng = split[5];		
		System.out.printf("%s\t%s\t%s\t%s\t\n", cityId, cityName, lat, lng);
		bw.write(cityId);
		bw.write("\t");
		bw.write(cityName);
		bw.write("\t");
		bw.write(lat);
		bw.write("\t");
		bw.write(lng);
		bw.write("\n");

	}
	
	private static void findGeonameId(String line){
		String id ;
		String city;
		String lat;
		String lng;
//		(\S+[0-9]+\S+).*?(\\s+[\\x00-\\x7F]+[\\x00-\\x7F]\s).*?((\+|-)?(\S+\d{1,6}+(\.[0-9]+))).((\+|-)?(\d{1,6}+(\.[0-9]+)))
		//(\\S+[0-9]+\\S+).*?((\\+|-)?(\\S+\\d{1,6}+(\\.[0-9]+))).((\\+|-)?(\\d{1,6}+(\\.[0-9]+)))
		Pattern p = Pattern.compile("(\\S+[0-9]+\\S+).*?([\\w]+).*?((\\+|-)?(\\S+\\d{1,6}+(\\.[0-9]+))).((\\+|-)?(\\d{1,6}+(\\.[0-9]+)))");
		Matcher m = p.matcher(line);
		while (m.find()) {
			id = m.group(1);
			city = m.group(2);
			lat = m.group(3);
			lng = m.group(7);
		  System.out.println( " ID: " + id + "......City: " + city
				  + "......lat: " + lat  + "......lng: " + lng );
		}
	}

}
