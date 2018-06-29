package cm.neusoft.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

/*
 * 测试shiro认证
 */
public class ShiroTest {
	
	@Test
	public void testLogin() throws Exception{
		//1:创建SecurityManager工厂对象:加载配置文件,创建工厂对象
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		//2:通过工厂对象，创建SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//3:将SecurityManager绑定到当前运行环境中，让系统随时都可以访问securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//4:
	}
}
