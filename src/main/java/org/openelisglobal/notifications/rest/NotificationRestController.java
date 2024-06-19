package org.openelisglobal.notifications.rest;

import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.notifications.dao.NotificationDAO;
import org.openelisglobal.notifications.entity.Notification;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/rest")
@RestController
public class NotificationRestController {

    private final NotificationDAO notificationDAO;
    private SystemUserService systemUserService;
    private static final String USER_SESSION_DATA = "userSessionData";


    @Autowired
    public NotificationRestController(NotificationDAO notificationDAO, SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
        this.notificationDAO = notificationDAO;
    }

    @GetMapping("/notifications")
    public List<Notification> getNotifications() {

        return notificationDAO.getNotifications();
    }

    @PostMapping("/notification")
    public ResponseEntity<?> saveNotification(@RequestBody Notification notification,
    HttpServletRequest request
    ) {

        notification.setCreatedDate(OffsetDateTime.now());

        notification.setReadAt(null);


        notification.setUser(systemUserService.getUserById(getSysUserId(request)));

        // notification.setUser();

        notificationDAO.save(notification);

        return ResponseEntity.ok().body("Notification saved successfully");
    }


    protected String getSysUserId(HttpServletRequest request) {
    UserSessionData usd = (UserSessionData) request
      .getSession()
      .getAttribute(USER_SESSION_DATA);
    if (usd == null) {
      usd = (UserSessionData) request.getAttribute(USER_SESSION_DATA);
      if (usd == null) {
        return null;
      }
    }
    return String.valueOf(usd.getSystemUserId());
  }

}
