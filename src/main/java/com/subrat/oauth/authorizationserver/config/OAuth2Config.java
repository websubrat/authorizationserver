package com.subrat.oauth.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
	
	private String clientId="subrat";
	private String clientSecret="subrat-secret";
	//signer key
	private String privateKey="-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEowIBAAKCAQEApnePvxBdcEXbTCK568jqN1MieKdaUcU/+bQwwEFQA3ffv3N6\n" + 
			"10rI6zN4Oq584S+dG41bXOAlGHh6uTxsBkAN5X6cjnBa5oy4c6efH2XftdqAVndV\n" + 
			"QqgJh6qtcNtP4yCQfwHc3YU7v2RmzfbNuoVpwyUw526JPJFvkfRF8vAF6fdDKMHe\n" + 
			"X07f2/MM0SiLpIFBv61l3O7uSFIc4e6VBuS9EWfqRQc2pNdV8KqPWMjB7Qfa/ygz\n" + 
			"9XdQlsq21v54mNmmFV3Rstkww7xxWaLtFIiLTHQsWglKaTMs6aP9iBCl71sL7Lzp\n" + 
			"JD3sl0o2n09W7dJmvRMGCiNEEAQLCRh8dIIyfQIDAQABAoIBAFllnfmylStlKIbz\n" + 
			"ozrflACQEdQPs650Ko4z2XZ13L1wE+Xh41dZHxtIccm5yPC5vDp+qFDxQpqooOd3\n" + 
			"bl/Q6GWVoAfSiM6FRc0+eHLJJ+GmbQOCZHQ+f5eu7iPMc1N7On4qhu0jztlS1u7H\n" + 
			"GuaPHSQihefTcbnoNK2CLEpx4gsp00joss/IXszYavnDhxhOKnv5cIid3XKRNDvw\n" + 
			"XFHaqCVtDAvnGXeGg95S+NiwMkfYzWjFQ09mKO5p7f0Sgw+9oTTO8/b5Aab+pU+0\n" + 
			"qA+c0vJeSI9DdKEJToEr/utlhZWHUI7eNCLkDy+mfQXV0iIxd3jNskBuiDjX9DLj\n" + 
			"CXJoCbUCgYEA3cPScsz8rYbw56ZpA4wpxT+ArJGXFVmP9JpmXfZD0rmJWcLXOJBG\n" + 
			"Tiz/GeOgHHFxZiLlm/oQacLwdHkhP0E5maq13d+io5xubVusQR5tqt35NQA6qTyI\n" + 
			"GHomDlTOziEKtu/ZfsPP9EqKI3F+uTOzGriMZXGPbEYlpcIE9sYW19cCgYEAwCpc\n" + 
			"GXgZDLmjpy8jStJ31ZL6WWz9GXg2lSUOsqFKDzacwzqVYPZquypgXP+bcsqnJABI\n" + 
			"PM7TLwmKjOpYjpEta/RL2LHIeRettPtd99T9B0PIHLvkyUdGsq/vDiPTizhZ9jlV\n" + 
			"xCuqDtJMhOFJ73ZRtN/OhVGHa/73RYWFECul7csCgYAhz6Feb3J24oQITY7lQ5F6\n" + 
			"yVvM88WdM7KHEKNa4BknH7KZROObdCPD94hNYi8fJwyrDdcEGPQ3q8kNeQpgookt\n" + 
			"rKFd2qLpXwhXFAsHQqRl0MIWGprXAqudT8JNnRCKzO7dRWRbt28VR+gXz04K3p8I\n" + 
			"C+sb7Fx5nZhP1Wz8UudYsQKBgEyq2dHjg4L7MZrLCLaaX+sa170D6brWplg8z69f\n" + 
			"ADAvpr2RSvXDenOwBYA1Dqzuw15uqmNwVPtIQDnWaXx/2XJfpj8PNx9w3gH949Cz\n" + 
			"7juOxHEKWc4gHMm/vY2Tp5u84M+Gsnwwu4lsR9EgHSJnv1N9k2THUVM6Byd6jMsV\n" + 
			"zv6fAoGBAKYm3qDXvqNySKaD29a0kmc3pXORFVxajg3fuZqfnk9hNcwtehSgudts\n" + 
			"Evr75rDftRn4i0TpqAhFMrYnAfXAopnnaSBAarv08Zub072aSiR33Gl0ZajDHRZD\n" + 
			"NUrnV/r0cMlyfjXG+QzWITJi/vXhagJtj4AWPx7pZAThzoGxEfb4\n" + 
			"-----END RSA PRIVATE KEY-----";
	//verifier key
	private String publicKey="-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApnePvxBdcEXbTCK568jq\n" + 
			"N1MieKdaUcU/+bQwwEFQA3ffv3N610rI6zN4Oq584S+dG41bXOAlGHh6uTxsBkAN\n" + 
			"5X6cjnBa5oy4c6efH2XftdqAVndVQqgJh6qtcNtP4yCQfwHc3YU7v2RmzfbNuoVp\n" + 
			"wyUw526JPJFvkfRF8vAF6fdDKMHeX07f2/MM0SiLpIFBv61l3O7uSFIc4e6VBuS9\n" + 
			"EWfqRQc2pNdV8KqPWMjB7Qfa/ygz9XdQlsq21v54mNmmFV3Rstkww7xxWaLtFIiL\n" + 
			"THQsWglKaTMs6aP9iBCl71sL7LzpJD3sl0o2n09W7dJmvRMGCiNEEAQLCRh8dIIy\n" + 
			"fQIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
	
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Bean
	public JwtAccessTokenConverter tokenEnhancer()
	{
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);;
		converter.setVerifierKey(publicKey);
		return converter;
	}
	
	
	@Bean
	public JwtTokenStore tokenStore()
	{
		return new JwtTokenStore(tokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientId)
		.secret(clientSecret)
		.scopes("read","write")
		.authorizedGrantTypes("password","refresh_token")
		.accessTokenValiditySeconds(200000)
		.refreshTokenValiditySeconds(200000000);
		
		
	}
	
	
	

}
