package cm.neusoft.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Hello world!
 *
 */
public class PermissionRealm extends AuthorizingRealm {
	
	public String getName( ) {
		return "PermissionRealm";
	}
	
	/**
	 * 授权操纵
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//传入参数：principals:用户认证信息
		//SimpleAuthenticationInfo:认证方法返回封装认证信息中第一个参数：用户信息（username）
		
		//当前登陆用户名信息:用户凭证
		String username = (String)principals.getPrimaryPrincipal();
		
		//模拟查询数据库：查询用户实现指定的角色，以及用户权限
		List<String> roles = new ArrayList<String>();
		List<String> permission = new ArrayList<String>();
		
		//假设用户在数据库中拥有role1角色
		roles.add("role3");
		
		//假设用户在数据库中拥有user:delete
		permission.add("user:delete");
		
		//返回用户在数据库中的权限与角色
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		info.addRoles(roles);
		info.addStringPermissions(permission);
		return info;
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
