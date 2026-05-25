package vn.huuchuong.pabackbyhuuchuong.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.huuchuong.pabackbyhuuchuong.base.BaseResponse;
import vn.huuchuong.pabackbyhuuchuong.entity.Role;
import vn.huuchuong.pabackbyhuuchuong.entity.User;
import vn.huuchuong.pabackbyhuuchuong.exception.BusinessException;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserLoginRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserRequestRegister;
import vn.huuchuong.pabackbyhuuchuong.payload.response.AuthResponse;
import vn.huuchuong.pabackbyhuuchuong.payload.response.UserLoginResponse;
import vn.huuchuong.pabackbyhuuchuong.repository.UserRepository;
import vn.huuchuong.pabackbyhuuchuong.service.UserService;
import vn.huuchuong.pabackbyhuuchuong.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public Void register(UserRequestRegister userRequestRegister) {

        if (userRepository.existsByUsername(userRequestRegister.getUsername())) {
            throw new BusinessException("Username already exists");
        }
        if (!userRequestRegister.getEmail().contains("@")){
            throw new BusinessException("Email address is not valid");
        }


        User user = modelMapper.map(userRequestRegister, User.class);
        user.setPassword(passwordEncoder.encode(userRequestRegister.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return null;
    }

    @Override
    public BaseResponse<AuthResponse> login(UserLoginRequest u, HttpServletRequest httpReq) {
        // kiem ra va lay ra usser theo usser name
        User userOtp = userRepository.findByUsername(u.getUsername());
        if (userOtp == null) {
            throw new BusinessException("Username not exists");
        }

        // sau do kiem tra passs word
        if (!passwordEncoder.matches(u.getPassword(), userOtp.getPassword())) {
            throw new BusinessException("Wrong password");
        }



        // tao token va tra ve cho client
        UserLoginResponse userLoginResponse = modelMapper.map(userOtp, UserLoginResponse.class);
        refreshTokenService.revokeByUsernameAndUserAgent(u.getUsername(), httpReq.getHeader("User-Agent")); // se xoa di rfresh token cu neu login

        String accessToken = JwtUtils.createAccessToken(userLoginResponse, httpReq); // tien hanh tao refersh va access token
        String refreshToken = JwtUtils.createRefreshToken(userLoginResponse, httpReq);

        refreshTokenService.create(
                userLoginResponse.getUsername(),
                refreshToken,
                httpReq.getHeader("User-Agent"),
                7L * 24 * 60 * 60 * 1000  // 7 ngày
        ); // tao

        AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
        return BaseResponse.success(authResponse, "Đăng nhập thành công");


    }
}
