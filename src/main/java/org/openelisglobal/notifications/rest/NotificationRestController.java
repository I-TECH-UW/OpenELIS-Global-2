package org.openelisglobal.notifications.rest;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.notifications.dao.NotificationDAO;
import org.openelisglobal.notifications.dao.NotificationSubscriptionDAO;
import org.openelisglobal.notifications.entity.Notification;
import org.openelisglobal.notifications.entity.NotificationSubscriptions;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class NotificationRestController {

    private final NotificationDAO notificationDAO;
    private final SystemUserService systemUserService;
    private final NotificationSubscriptionDAO notificationSubscriptionDAO;
    private static final String USER_SESSION_DATA = "userSessionData";

    @Autowired
    private ConfigurableEnvironment env;

    @Autowired
    public NotificationRestController(NotificationDAO notificationDAO, SystemUserService systemUserService,
            NotificationSubscriptionDAO notificationSubscriptionDAO) {
        this.notificationDAO = notificationDAO;
        this.systemUserService = systemUserService;
        this.notificationSubscriptionDAO = notificationSubscriptionDAO;
    }

    @GetMapping("/notifications/all")
    public List<Notification> getNotifications() {
        return notificationDAO.getNotifications();
    }

    @GetMapping("/notifications")
    public List<Notification> getNotificationsByUserId(HttpServletRequest request) {
        String sysUserId = getSysUserId(request);
        return notificationDAO.getNotificationsByUserId(Long.parseLong(sysUserId));
    }

    @PostMapping("/notification/{userId}")
    public ResponseEntity<?> saveNotification(@PathVariable String userId, @RequestBody Notification notification) {
        notification.setUser(systemUserService.getUserById(userId));
        notification.setCreatedDate(OffsetDateTime.now());
        notification.setReadAt(null);
        notificationDAO.save(notification);
        return ResponseEntity.ok().body("Notification saved successfully");
    }

    @PutMapping("/notification/markasread/{id}")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable String id) {
        Notification notification = notificationDAO.getNotificationById(Long.parseLong(id));
        notification.setReadAt(OffsetDateTime.now());
        notificationDAO.updateNotification(notification);
        return ResponseEntity.ok().body("Notification updated successfully");
    }

    @PutMapping("/notification/markasread/all")
    public ResponseEntity<?> markAllNotificationsAsRead(HttpServletRequest request) {
        String sysUserId = getSysUserId(request);
        notificationDAO.setAllUserNotificationsToRead(Long.valueOf(sysUserId));
        return ResponseEntity.ok().body("All notifications updated successfully");
    }

    @GetMapping("/systemusers")
    public List<SystemUser> getSystemUsers() {
        return notificationDAO.getSystemUsers();
    }

    @GetMapping("/notification/public_key")
    public ResponseEntity<Map<String, String>> getPublicKey() {

        String publicKey = env.getProperty("vapid.public.key");

        Map<String, String> response = new HashMap<>();
        response.put("publicKey", publicKey);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/notification/subscribe")
public ResponseEntity<?> subscribe(@RequestBody NotificationSubscriptions notificationSubscription,
        HttpServletRequest request) {

    String sysUserId = getSysUserId(request);

    // Fetch the user object
    SystemUser user = systemUserService.getUserById(sysUserId);

    // Ensure user object is not null
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    // Set the user entity directly
    notificationSubscription.setUser(user);
    
    System.out.println("userId 1 " + sysUserId);
    System.out.println("User ID 3 " + user.getId());
    System.out.println("User ID 4 from notification " + notificationSubscription.getUser().getId());

    notificationSubscriptionDAO.saveOrUpdate(notificationSubscription);

    return ResponseEntity.ok().body("Subscribed successfully");
}


    

    protected String getSysUserId(HttpServletRequest request) {
        UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
        if (usd == null) {
            usd = (UserSessionData) request.getAttribute(USER_SESSION_DATA);
            if (usd == null) {
                return null;
            }
        }
        return String.valueOf(usd.getSystemUserId());
    }
}
