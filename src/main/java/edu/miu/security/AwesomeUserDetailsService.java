package edu.miu.security;

import edu.miu.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// why did we need to give a path
@Service("userDetailsService")
@Transactional
//@RequiredArgsConstructor

public class AwesomeUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public AwesomeUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var user = userRepo.findByEmail(username);
      var userDetails = new AwesomeUserDetails(user);
      return userDetails;
    }
}
