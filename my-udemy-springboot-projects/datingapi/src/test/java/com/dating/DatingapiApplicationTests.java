package com.dating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dating.controllers.UserAccountController;
import com.dating.entities.Interest;
import com.dating.entities.UserAccount;
import com.dating.repos.InterestRepository;
import com.dating.repos.UserAccountRepository;


// be patient for your Lord...

@SpringBootTest
class DatingapiApplicationTests {

	@Mock
	 UserAccountRepository userAccountRepository;
	
	@Mock
	private InterestRepository interestRepository;
	
	@InjectMocks
	UserAccountController controller;
	
	@Test
	void testRegisterUser() {
		UserAccount userAccount = new UserAccount();
		UserAccount savedUserAccount = new UserAccount();
		savedUserAccount.setId(123);
		when(userAccountRepository.save(userAccount)).thenReturn(savedUserAccount);
	    UserAccount outputUserAccount = controller.registerUser(userAccount);
	    assertNotNull(outputUserAccount);
	    System.out.println("a111");
	    assertNotNull(outputUserAccount.getId()); 
	    System.out.println(outputUserAccount.getId());
	    assertEquals(123, outputUserAccount.getId());
	    verify(userAccountRepository).save(userAccount);
	}

	void testUpdateInterest() {
		Interest interest = new Interest();
		interest.setUserAccountId(123);
		
		controller.updateInterest(interest);
	}
}
