
### 获取access_token

- 在浏览器访问 localhost:8080/oauth/authorize?client_id=clientId&response_type=code&redirect_uri=http://www.baidu.com 会跳转到http://localhost:8080/login，输入用户名这里输入的是admin(其实用户名可以输入任何字符)，密码123456(密码必须和UserDetailsService配置的用户密码保持一致)
   
- 登录成功后跳转到授权页面，选择同意Approve并点击授权Authorize

- 授权成功后会跳转到AuthorizationServer配置的redirectUris上，这里配置的是百度，会跳转到www.baidu.com并携带一个code参数

- 根据上个步骤获取到的code值，然后获取access_token
  
  - 使用POST请求方式访问 http://localhost:8080/oauth/token
  - 设置请求头Authorization，Username设置为我们设置的clientId，Password为我们设置的clientSecret
  - 参数 grant_type的值固定为authorization_code，code 为上个步骤获取到的值，其它值都是认证服务器配置的值

#### 短信验证码登录获取token

通过短信验证码获取令牌的过程也是自定义认证的过程。 组定义认证流程如下：

- 自定义一个过滤器实现认证 SmsCodeAuthenticationFilter
- 认证的时候需要将认证信息封装到一个令牌实体中 SmsCodeAuthenticationToken
- 最终实现认证的是认证提供商 SmsCodeAuthenticationProvider
- 将自定义的过滤器添加到UsernamePasswordAuthenticationFilter过滤器的前面

**用法**

- 发送短信验证码
![l3YkIH.png](https://s2.ax1x.com/2019/12/31/l3YkIH.png)

- 获取token，通过rest client 获取token
![l3YNQ0.png](https://s2.ax1x.com/2019/12/31/l3YNQ0.png)

- 或者通过curl命令行获取token
![l3YgQx.png](https://s2.ax1x.com/2019/12/31/l3YgQx.png)

[认证(OAuth2)-App社交登录获取token](https://blog.csdn.net/vbirdbest/article/details/94448303)

