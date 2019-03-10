package part2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static Stream<String>readFile(String localName){
		try{
			Path p = Paths.get(Main.class.getResource(localName).toURI());
			return Files.lines(p);
		}
		catch (IOException | URISyntaxException e) {
			throw new Error(e);
		}
	}
	public static List<String> splitOnSpaces(String s){//not great code...
		return Arrays.asList(s.split("\\s+"));//good code depends on exactly
		//what space means and how to handle spaces on start-end
	}
	public static String normalize(String s){
		s = s.toLowerCase();
		if(s.endsWith("s")){return s.substring(0,s.length()-1);}
		return s;//real normalization would be massively complicated
	}
	public static void main(String[] args) {
//		Stream<String>data=readFile("Test.txt").parallel();
//		Map<String,Long> res = data
//			.flatMap(line->splitOnSpaces(line).stream())
//			.map(w->normalize(w))
//			.collect(Collectors.groupingByConcurrent(
//				s -> s,//the key to group is the whole word
//				Collectors.counting())//mapped to the occurrences of such word
//			);
//		System.out.println(res);
		
		TextInfo ti=new TextInfo("Test.txt");
		System.out.println("Context");
		System.out.println(ti.context("closer", "to"));
	}
}
