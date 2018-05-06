package service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dto.LocalAuthExecution;
import com.chaucer.o2o.entity.LocalAuth;
import com.chaucer.o2o.entity.UserInfo;
import com.chaucer.o2o.enums.WechatAuthStateEnum;
import com.chaucer.o2o.service.LocalAuthService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {
	@Autowired
	private LocalAuthService localAuthService;

	@Test

	public void testABindLocalAuth() {
		// 新增一条平台帐号
		LocalAuth localAuth = new LocalAuth();
		UserInfo personInfo = new UserInfo();
		String username = "testusername";
		String password = "testpassword";
		// 给平台帐号设置上用户信息
		// 给用户设置上用户Id,标明是某个用户创建的帐号
		personInfo.setUserId(1L);
		// 给平台帐号设置用户信息,标明是与哪个用户绑定
		localAuth.setUser(personInfo);
		// 设置帐号
		localAuth.setUsername(username);
		// 设置密码
		localAuth.setPassword(password);
		// 绑定帐号
		LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
		// 通过userId找到新增的localAuth
		localAuth = localAuthService
				.getLocalAuthByUserId(personInfo.getUserId());
		// 打印用户名字和帐号密码看看跟预期是否相符
		System.out.println("用户昵称：" + localAuth.getUser().getName());
		System.out.println("平台帐号密码：" + localAuth.getPassword());
	}

	@Test
	@Ignore
	public void testBModifyLocalAuth() {
		// 设置帐号信息
		long userId = 1;
		String username = "testusername";
		String password = "testpassword";
		String newPassword = "testnewpassword";
		// 修改该帐号对应的密码
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId,
				username, password, newPassword);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
		// 通过帐号密码找到修改后的localAuth
		LocalAuth localAuth = localAuthService
				.getLocalAuthByUsernameAndPwd(username, newPassword);
		// 打印用户名字看看跟预期是否相符
		System.out.println(localAuth.getUser().getName());
	}
}
