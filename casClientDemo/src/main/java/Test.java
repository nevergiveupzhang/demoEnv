import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		List<String> availableCode = new ArrayList<String>();
		availableCode.add("01");
		availableCode.add("02");
		System.out.println(availableCode.stream().map(e -> "'" + e + "'").collect(Collectors.joining(",", "(", ")")));
	}
}
