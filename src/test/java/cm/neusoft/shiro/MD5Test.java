package cm.neusoft.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class MD5Test {

	@Test
	public void testMD5() throws Exception{
		String password = "666"; 
				
		//加密：md5
		Md5Hash md5Hash = new Md5Hash(password);
		System.out.println(md5Hash);
		//fae0b27c451c728867a567e8c1bb4e53
		
		//加密：md5 + 盐
		md5Hash = new Md5Hash(password, "zhangsan");
		System.out.println(md5Hash);
		//2f1f526e25fdefa341c7a302b47dd9df
		
		//加密：md5 + 盐 + 散列次数
		md5Hash = new Md5Hash(password, "zhangsan", 3);
		System.out.println(md5Hash);
		
	}
}
