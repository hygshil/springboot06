package com.xiexin.shiro;

import com.xiexin.bean.Userinfo;
import com.xiexin.bean.UserinfoExample;
import com.xiexin.service.UserinfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MybatisRealm extends AuthorizingRealm {
    @Autowired
    private UserinfoService userinfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username =(String) authenticationToken.getPrincipal();
        UserinfoExample example = new UserinfoExample();
        UserinfoExample.Criteria criteria = example.createCriteria();
       criteria.andUsernameEqualTo(username);
        List<Userinfo> userinfos = userinfoService.selectByExample(example);
        Userinfo dbUserinfo=null;
        if(userinfos!=null&&userinfos.size()>0){
           dbUserinfo = userinfos.get(0);
           //获取账户和密码
            String password = dbUserinfo.getPassword();
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
        return simpleAuthenticationInfo;
        }
        return null;
    }
}
