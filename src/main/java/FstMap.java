import org.apache.lucene.store.InputStreamDataInput;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.IntSequenceOutputs;
import org.apache.lucene.util.fst.Util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FstMap {

	private final FST<IntsRef> fst;

	private FstMap(FST<IntsRef> fst) {
		this.fst = fst;
	}

	public int[] get(String flexion) throws IOException {
		IntsRef res = Util.get(fst, LuceneChiefCooker.prepareLuceneString(flexion));
		return res == null ? null : res.ints;
	}

	public static FstMap readFromResources() throws IOException {
		return new FstMap(new FST<>(
			new InputStreamDataInput(FstMap.class.getResourceAsStream("FST.BIN")),
			IntSequenceOutputs.getSingleton()
		));
	}
}
