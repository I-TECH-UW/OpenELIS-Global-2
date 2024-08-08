// Any other custom service worker logic can go here.

self.addEventListener("push", async function (event) {
  if (event.data) {
    const data = event.data.json();
    const notificationOptions = {
      body: data.body || "Sample Body",
      tag: data.external_id || "default-tag",
    };

    event.waitUntil(
      self.registration.showNotification(
        "OpenELIS Test Message Received",
        notificationOptions,
      ),
    );
  }
});

// This allows the web app to trigger skipWaiting via
// registration.waiting.postMessage({type: 'SKIP_WAITING'})
self.addEventListener("message", (event) => {
  if (event.data && event.data.type === "SKIP_WAITING") {
    self.skipWaiting();
  }
});

// self.addEventListener('push', (e) => {
//     const data = e.data?.json();
//     if (data) {
//       self.registration.showNotification(data.title, {
//         body: data.body,
//       });
//     }
//   });

//   self.addEventListener('notificationclick', (e) => {
//     e.notification.close();
//     e.waitUntil(focusOrOpenWindow());
//   });

//   async function focusOrOpenWindow() {
//     const url = new URL('/', self.location.origin).href;

//     const allWindows = await self.clients.matchAll({
//       type: 'window',
//     });
//     const appWindow = allWindows.find((w) => w.url === url);

//     if (appWindow) {
//       return appWindow.focus();
//     } else {
//       return self.clients.openWindow(url);
//     }
//   }
