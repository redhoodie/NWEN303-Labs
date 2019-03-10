package part2;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TextInfo {
	List<String> list;
	Map<String, List<Integer>> inverseIndex;
	
	public TextInfo(String name) {
		Stream<String> data = Main.readFile(name).parallel();
		list = data
			.flatMap(line->Main.splitOnSpaces(line).stream())
			 .map(w->Main.normalize(w)).collect(Collectors.toList());

		list = Collections.unmodifiableList(list);
		inverseIndex = IntStream.range(0, list.size()).parallel().boxed()
			.collect(Collectors.groupingByConcurrent(i->list.get(i)));

		inverseIndex = Collections.unmodifiableMap(inverseIndex);
		// map word to list of occurrences.
		//Once we have list and inverseIndex, the fun can begin
	}

	public List<String> context(String first,String second) {
		List<Integer>pos=inverseIndex.get(first);
		if(pos==null) {return Collections.emptyList();}
		Optional<Integer> found=pos.parallelStream()
		.filter(i->i+1!=list.size() && list.get(i+1).equals(second))
		.findFirst();
		if(!found.isPresent()) {return Collections.emptyList();}
		int less=Math.max(found.get()-10,0);
		int more=Math.min(found.get()+10,list.size());
		return list.subList(less, more);
	}

	//	...

}
