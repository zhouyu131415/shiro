package cm.neusoft.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Hello world!
 *
 */
public class MyRealm extends AuthorizingRealm {
	
	public String getName( ) {
		return "MyRealm";
	}
	
	/**
	 * 授权操纵
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	
	/**
	 * 认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//参数token，表示登陆时封装的UsernamePasswordToken
		
		//通过用户名到数据库中查用户信息，封装成一个AuthenticationInfo对象返回，方便认证器进行对比
		//获取token中的用户名
		String username = (String)token.getPrincipal();
		
		//通过用户名查询数据库，修改用户对应数据查询返回，账号与密码
		if(!"zhangsan".equals(username)) {
			return null;
		}
		
		String password = "666";
		
		//info对象表示realm登陆比对信息；参数1:账号,参数2:密码,3:当前realm名字
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		
		System.out.println(token);
		return info;
	}
    
}
