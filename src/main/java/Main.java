import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.BytesRefBuilder;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.*;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class Main {

	private static Path getPath() {
		return Paths.get("src/main/resources/FST.BIN").toAbsolutePath();
	}

	public static void main(String[] args) throws IOException {

		// этот код использовался для создания ресурса

		/*var builder = new FstMapBuilder();
		builder.add("топор", 1);
		builder.add("дерево", 2);
		builder.add("топор", 30);

		builder.finish().save(getPath());*/

		var map = FstMap.readFromResources();

		out.println(Arrays.toString(map.get("топор")));
		out.println(Arrays.toString(map.get("дерево")));
	}
}
