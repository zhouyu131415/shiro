package cm.neusoft.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
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
		
		//4:创建当前登陆的主体
		Subject subject = SecurityUtils.getSubject();
		
		//5:绑定主体登陆的身份/凭证，即账号密码
		//参数1:将要登陆的用户名，参数2:登陆用户的密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","6661");
		
		//6:主体登陆
		subject.login(token);
		
		//7:判断登陆是否成功
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
		
		//8:登出（注销）
		subject.logout();
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
	}
}
