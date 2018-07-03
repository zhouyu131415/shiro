package cm.neusoft.shiro;

import java.util.Arrays;

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
	public void testHasRoleByRealm() throws Exception{
		//1:创建SecurityManager工厂对象:加载配置文件,创建工厂对象
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
		
		//2:通过工厂对象，创建SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//3:将SecurityManager绑定到当前运行环境中，让系统随时都可以访问securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//4:创建当前登陆的主体
		Subject subject = SecurityUtils.getSubject();
		
		//5:绑定主体登陆的身份/凭证，即账号密码
		//参数1:将要登陆的用户名，参数2:登陆用户的密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","666");
		
		//6:主体登陆
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//判断当前用户是否拥有某个权限
		System.out.println(subject.isPermitted("user:delete"));
		
		//判断当前用户是否用某个角色
		System.out.println(subject.hasRole("role1"));
		
		
	}
	
	@Test
	public void testHasRole() throws Exception{
		//1:创建SecurityManager工厂对象:加载配置文件,创建工厂对象
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		
		//2:通过工厂对象，创建SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//3:将SecurityManager绑定到当前运行环境中，让系统随时都可以访问securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//4:创建当前登陆的主体
		Subject subject = SecurityUtils.getSubject();
		
		//5:绑定主体登陆的身份/凭证，即账号密码
		//参数1:将要登陆的用户名，参数2:登陆用户的密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","666");
		
		//6:主体登陆
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//进行授权操作时前提：用户必须通过认证
		System.out.println(subject.isPermitted("user:update"));
		//判断当前用户是否拥有一些权限,返回true表示全都有,false表示不全部拥有
		System.out.println(subject.isPermittedAll("user:create", "user:delete"));
		//判断当前用户是否拥有一些权限,返回boolean数组,true表示有false没有
		System.out.println(Arrays.toString(subject.isPermitted("user:list", "user:delete")));
		
		//判断当前用户是否拥有某个权限,没有返回值，如果没有指定权限则抛出异常
		subject.checkPermission("user:delete");
		
		//判断当前用户是否用某个角色
//		System.out.println(subject.hasRole("role1"));
		//判断当前用户是否拥有一些角色，返回true表示全部拥有
//		System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		//判断当前用户是否拥有一些角色，返回true表示全部拥有
//		System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1", "role2"))));
		
		//判断当前用户是否用手某个角色:没有返回值
//		subject.checkRole("role3");
	}
	
	@Test
	public void testLoginByPasswordRealm() throws Exception{
		//1:创建SecurityManager工厂对象:加载配置文件,创建工厂对象
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
		
		//2:通过工厂对象，创建SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//3:将SecurityManager绑定到当前运行环境中，让系统随时都可以访问securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//4:创建当前登陆的主体
		Subject subject = SecurityUtils.getSubject();
		
		//5:绑定主体登陆的身份/凭证，即账号密码
		//参数1:将要登陆的用户名，参数2:登陆用户的密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","666");
		
		//6:主体登陆
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//7:判断登陆是否成功
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
		
		//8:登出（注销）
		subject.logout();
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
	}
	
	@Test
	public void testLoginByRealm() throws Exception{
		//1:创建SecurityManager工厂对象:加载配置文件,创建工厂对象
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		//2:通过工厂对象，创建SecurityManager对象
		SecurityManager securityManager = factory.getInstance();
		
		//3:将SecurityManager绑定到当前运行环境中，让系统随时都可以访问securityManager对象
		SecurityUtils.setSecurityManager(securityManager);
		
		//4:创建当前登陆的主体
		Subject subject = SecurityUtils.getSubject();
		
		//5:绑定主体登陆的身份/凭证，即账号密码
		//参数1:将要登陆的用户名，参数2:登陆用户的密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","666");
		
		//6:主体登陆
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//7:判断登陆是否成功
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
		
		//8:登出（注销）
		subject.logout();
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
	}
	
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
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//7:判断登陆是否成功
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
		
		//8:登出（注销）
		subject.logout();
		System.out.println("验证登陆是否成功:" + subject.isAuthenticated());
	}
}
