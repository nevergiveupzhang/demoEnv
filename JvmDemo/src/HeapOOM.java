import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	static class OOMObject{}
	
	public static void main(String[] args) throws InterruptedException {
		int count=0;
		List<OOMObject> list=new ArrayList<OOMObject>();
		while(true) {
			count++;
			Thread.sleep(1);
			list.add(new OOMObject());
		}
	}
}
