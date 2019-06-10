import java.util.ArrayList;
import java.util.List;

public class JConsoleDemo {
	static class OOMObject{
		private byte[]placeholder=new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list=new ArrayList<OOMObject>();
		for(int i=0;i<num;i++) {
			Thread.sleep(100);
			list.add(new OOMObject());
		}
		System.gc();
	}
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}

}
