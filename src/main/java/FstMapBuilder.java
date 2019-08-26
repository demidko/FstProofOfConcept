import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.IntSequenceOutputs;

import java.io.IOException;
import java.util.*;

public class FstMapBuilder {

	private final Map<String, Set<Integer>> map = new HashMap<>();

	/**
	 * @param flexion     ключ
	 * @param lemmaId значение
	 */
	public void add(String flexion, int lemmaId) {
		map.computeIfAbsent(flexion, k -> new HashSet<>()).add(lemmaId);
	}

	public FST<IntsRef> finish() throws IOException {

		var result = new Builder<>(
			FST.INPUT_TYPE.BYTE1,
			IntSequenceOutputs.getSingleton()
		);

		for(var entry: map.entrySet()) {

			var ints = entry.getValue().stream().mapToInt(Number::intValue).toArray();

			result.add(
				LuceneChiefCooker.prepareLuceneString(entry.getKey()),
				new IntsRef(ints, 0, ints.length)
			);
		}

		return result.finish();
	}
}
