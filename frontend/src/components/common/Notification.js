import { alert, Stack, defaultModules } from "@pnotify/core";
import * as PNotifyMobile from "@pnotify/mobile";
import _ from "lodash-es";

// Set up PNotify with mobile module
defaultModules.set(PNotifyMobile, {});

// Create a notification stack
const notifyStack = new Stack({
  dir1: "down",
  dir2: "left",
  firstpos1: 25,
  firstpos2: 25,
  modal: false,
  maxOpen: 3,
  maxStrategy: "close",
  maxClosureCausesWait: false,
  push: "top",
});

// Function to create and show a notification
const notify = (text, type) => {
  const notification = alert({
    type: type,
    text: text,
    styling: "brighttheme",
    mode: "light",
    sticker: false,
    buttons: {
      closer: false,
      sticker: false,
    },
    stack: notifyStack,
  });
  notification.refs.elem.addEventListener("click", () => {
    notification.close();
  });
};

// Export functions to create different types of notifications
export const Notification = {
  info: ({ message }) => {
    notify(message, "notice");
  },
  success: ({ message }) => {
    notify(message, "success");
  },
  warning: ({ message }) => {
    notify(message, "error");
  },
  closeAll: () => {
    notifyStack.close();
  },
};
