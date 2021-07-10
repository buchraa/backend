package com.mouridiyya.bibliomouride.controller.security;


import com.mouridiyya.bibliomouride.configuration.security.jwt.JwtUtils;
import com.mouridiyya.bibliomouride.configuration.security.services.UserDetailsImpl;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.security.ERole;
import com.mouridiyya.bibliomouride.entity.security.Role;
import com.mouridiyya.bibliomouride.entity.security.User;
import com.mouridiyya.bibliomouride.model.payload.request.LoginRequest;
import com.mouridiyya.bibliomouride.model.payload.request.SignupRequest;
import com.mouridiyya.bibliomouride.model.payload.response.JwtResponse;
import com.mouridiyya.bibliomouride.model.payload.response.MessageResponse;
import com.mouridiyya.bibliomouride.repository.RoleRepository;
import com.mouridiyya.bibliomouride.repository.UserRepository;
import com.mouridiyya.bibliomouride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private UserService userService;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		try {
			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles));
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "trad":
						Role modRole = roleRepository.findByName(ERole.ROLE_TRANSLATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}



	@GetMapping("/user/current")
	public ResponseEntity<?> getCurrentUser() {

		try {
			return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/users")
	public Page<User> getAllUser(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "8") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{

		return userService.manageUsers(pageNo, pageSize, sortBy);

	}

	@PostMapping("/updateUser")
	public User updateUserDetails(@RequestBody SignupRequest signUpRequest) {

		User update = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		if( signUpRequest.getUsername()!=null && !signUpRequest.getUsername().isEmpty())

		{

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						case "trad":
							Role modRole = roleRepository.findByName(ERole.ROLE_TRANSLATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
					}
				});
			}
			Optional<User> oldUser = userRepository.findByUsername(signUpRequest.getUsername());
			oldUser.ifPresent(user -> update.setId(user.getId()));
			oldUser.ifPresent(user -> update.setRoles(roles));

		}
		return userRepository.save(update);


	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> getUser(@PathVariable long id) {
		try {
		User user = userService.get(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
	public void delete(@PathVariable long id) {
		userService.delete(id);
	}

	
}

