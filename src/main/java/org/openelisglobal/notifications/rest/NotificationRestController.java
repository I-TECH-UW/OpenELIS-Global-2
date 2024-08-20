package org.openelisglobal.notifications.rest;

import java.security.Security;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
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
import nl.martijndwars.webpush.PushService;

import com.fasterxml.jackson.databind.ObjectMapper;

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

        // Ensure BouncyCastleProvider is added for cryptographic operations
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        NotificationSubscriptions ns = notificationSubscriptionDAO
                .getNotificationSubscriptionByUserId(Long.valueOf(userId));

        if (ns == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found");
        }

        try {
            // Configure PushService with VAPID keys
            PushService pushService = new PushService(
                    env.getProperty("vapid.public.key"),
                    env.getProperty("vapid.private.key"),
                    "mailto:your-email@example.com");

            String title = "OpenELIS Global Notification";
            String body = notification.getMessage();
            String url = "http://localhost";

            // Create a notification message
            Map<String, String> payload = new HashMap<>();
            payload.put("title", title);
            payload.put("body", body);
            payload.put("url", url);

            ObjectMapper objectMapper = new ObjectMapper();
            String payloadJson = objectMapper.writeValueAsString(payload);

            // Use fully qualified name for web push Notification
            nl.martijndwars.webpush.Notification webPushNotification = new nl.martijndwars.webpush.Notification(
                    ns.getPfEndpoint(),
                    ns.getPfP256dh(),
                    ns.getPfAuth(),
                    payloadJson);

            HttpResponse response = pushService.send(webPushNotification);

            return ResponseEntity.ok()
                    .body("Push notification sent successfully. Response: " + response.getStatusLine());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send push notification: " + e.getMessage());
        }

    }

    // @GetMapping("/notification/testpush")
    // public ResponseEntity<?> testPushNotification() {

    // // Fetch user and subscription
    // SystemUser user = systemUserService.getUserById("111");
    // NotificationSubscriptions ns =
    // notificationSubscriptionDAO.getNotificationSubscriptionByUserId(Long.valueOf(111));

    // String title = "Test Notification";
    // String body = "This is a test notification";
    // String url = "https://example.com";

    // if (ns.getPfAuth() == null || ns.getPfP256dh() == null || ns.getPfEndpoint()
    // == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not
    // found");
    // }

    // }

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
