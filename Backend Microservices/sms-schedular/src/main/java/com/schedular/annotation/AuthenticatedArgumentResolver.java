package com.schedular.annotation;
/// *
// * @author Advatix
// *
// * @since 2019
// *
// * @version 1.0
// */
// package com.sms.scheduler.annotation;
//
// import java.lang.annotation.Annotation;
// import java.util.Arrays;
// import java.util.Objects;
// import java.util.Optional;
// import java.util.regex.Pattern;
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.MethodParameter;
// import org.springframework.util.CollectionUtils;
// import org.springframework.util.StringUtils;
// import org.springframework.web.bind.support.WebDataBinderFactory;
// import org.springframework.web.context.request.NativeWebRequest;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.method.support.ModelAndViewContainer;
// import com.sms.scheduler.commons.Logger;
// import com.sms.scheduler.commons.Logger.MessageType;
// import com.sms.scheduler.exceptions.NotAuthenticatedException;
// import com.sms.scheduler.exceptions.NotAuthorizedException;
// import com.sms.scheduler.utils.AuthUtils;
// import com.sms.scheduler.utils.Constant;
// import com.sms.scheduler.utils.ErrorMessages;
// import com.sms.scheduler.utils.LogManager;
//
/// **
// * The Class AuthenticatedArgumentResolver.
// */
// public class AuthenticatedArgumentResolver implements HandlerMethodArgumentResolver {
//
// /** The auth utils. */
// @Autowired
// private AuthUtils authUtils;
//
// /** The user journey service. */
// @Autowired
// private ISqlUserJourneyService userJourneyService;
//
// /**
// * Resolve argument.
// *
// * @param param the param
// * @param mavContainer the mav container
// * @param request the request
// * @param binderFactory the binder factory
// * @return the user table
// * @throws Exception the exception
// */
// @Override
// public UserTable resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
// NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
// Annotation[] paramAnns = param.getParameterAnnotations();
// for (Annotation annotation : paramAnns) {
// if (Authenticated.class.isInstance(annotation)) {
// Authenticated authAnnotation = (Authenticated) annotation;
// HttpServletRequest httprequest = (HttpServletRequest) request.getNativeRequest();
// String token = httprequest.getHeader(Constant.AUTH_TOKEN);
// String deviceType = httprequest.getHeader(Constant.DEVICE_TYPE);
// return checkUser(authAnnotation, token, deviceType);
// }
// }
// return null;
// }
//
// /**
// * Check user.
// *
// * @param authAnnotation the auth annotation
// * @param token the token
// * @param deviceType the device type
// * @return the user table
// * @throws NotAuthenticatedException the not authenticated exception
// * @throws NotAuthorizedException the not authorized exception
// */
// private UserTable checkUser(Authenticated authAnnotation, String token, String deviceType)
// throws NotAuthenticatedException, NotAuthorizedException {
// DeviceType device = null;
// try {
// device = DeviceType.valueOf(deviceType);
// } catch (Exception e) {
// throw new NotAuthorizedException(ErrorMessages.INVALID_DEVICE_TYPE);
// }
// Boolean tokenEmpty = checkIfTokenEmpty(authAnnotation, token);
// if (tokenEmpty) {
// return null;
// }
// UserTable user = decodeUser(token, authAnnotation, device);
// if (user == null) {
// return null;
// }
// return user;
// }
//
// /**
// * Decode user.
// *
// * @param token the token
// * @param authAnnotation the auth annotation
// * @param device the device
// * @return the user table
// * @throws NotAuthenticatedException the not authenticated exception
// */
// private UserTable decodeUser(String token, Authenticated authAnnotation, DeviceType device)
// throws NotAuthenticatedException {
// UserTable user = null;
// try {
// LogManager.info(getClass(), "Token :" + token, MessageType.Info);
// String tokenData = authUtils.decodeToken(token);
// LogManager.info(getClass(), "Decoded Token :" + tokenData, MessageType.Info);
// String[] decodedTokenData = tokenData.split(Pattern.quote(Constant.SEPERATOR));
// String decodedToken = decodedTokenData[0];
// LogManager.info(getClass(),
// "Decoded Token :" + decodedToken + " - " + Arrays.toString(decodedTokenData),
// MessageType.Info);
// String userEmail = decodedToken;
//
// // OktaValidateTokenResponseDto response = oktaSsoService.validateToken(token);
// // if (Objects.isNull(response.getActive()) || !response.getActive())
// // throw new NotAuthorizedException(ErrorMessages.TOKEN_EXPIRED);
//
// // String userEmail = response.getUsername();
//
// userJourneyService.update(Logger.getLogger(getClass()).getValue(Constant.RequestId), null,
// userEmail);
//
// user = userService.findUserByUserName(userEmail, token, device);
// // fetchUserFromToken
//
// if (Objects.nonNull(user)) {
// userJourneyService.update(Logger.getLogger(getClass()).getValue(Constant.RequestId),
// user.getUserId(), user.getUserName());
//
// LogManager.info(getClass(), "Auth Request - " + user.getUserName(), MessageType.Info);
// if (user.getRoleDetails() == null) {
// Optional<MasterRoles> roleOpt = roleDetails.findOneByRoleId(user.getRoleId());
// if (roleOpt.isPresent())
// user.setRoleDetails(roleOpt.get());
// }
// }
// // if (user.getRoleId() != UserRole.SuperAdmin.getRoleId())
// // validateRoleId(authAnnotation, user.getRoleDetails());
// } catch (Exception ex) {
// throwException(authAnnotation, ex.getMessage());
// }
// return user;
// }
//
// /**
// * Validate role id.
// *
// * @param authAnnotation the auth annotation
// * @param masterRoles the master roles
// * @throws NotAuthenticatedException the not authenticated exception
// */
// private void validateRoleId(Authenticated authAnnotation, MasterRoles masterRoles)
// throws NotAuthenticatedException {
// Boolean authenticateUser = Boolean.FALSE;
// if (authAnnotation.required()) {
// int menuId = authAnnotation.menuId();
// int subMenuId = authAnnotation.subMenuId();
// Priviledge[] permissions = authAnnotation.premission();
// if (!CollectionUtils.isEmpty(masterRoles.getRoleMapping())) {
// for (RoleMenuMapping mapping : masterRoles.getRoleMapping()) {
// if (mapping.getMenuId() == menuId && mapping.getSubMenuId() == subMenuId
// && Arrays.asList(permissions).contains(mapping.getPriviledge())) {
// authenticateUser = Boolean.TRUE;
// break;
// }
// }
// }
// }
// if (!authenticateUser)
// throwException(authAnnotation, ErrorMessages.USER_NOT_ALLOW);
// }
//
// /**
// * Check if token empty.
// *
// * @param authAnnotation the auth annotation
// * @param token the token
// * @return the boolean
// * @throws NotAuthenticatedException the not authenticated exception
// */
// private Boolean checkIfTokenEmpty(Authenticated authAnnotation, String token)
// throws NotAuthenticatedException {
// if (StringUtils.isEmpty(token)) {
// throwException(authAnnotation, ErrorMessages.INVALID_TOKEN_MESSAGE);
// return true;
// }
// return false;
// }
//
// /**
// * Throw exception.
// *
// * @param authAnnotation the auth annotation
// * @param message the message
// * @throws NotAuthenticatedException the not authenticated exception
// */
// private void throwException(Authenticated authAnnotation, String message)
// throws NotAuthenticatedException {
// if (authAnnotation.required()) {
// LogManager.info(getClass(), message, MessageType.Info);
// throw new NotAuthenticatedException(message);
// }
// }
//
// /**
// * Supports parameter.
// *
// * @param parameter the parameter
// * @return true, if successful
// */
// @Override
// public boolean supportsParameter(MethodParameter parameter) {
// return parameter.hasParameterAnnotation(Authenticated.class);
// }
//
// }
