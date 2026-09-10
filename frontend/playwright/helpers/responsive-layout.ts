import type { Page } from "@playwright/test";
import { expect } from "./test-base";
import { LONG_TIMEOUT } from "./timeouts";

export async function expectNoPageHorizontalOverflow(
  page: Page,
  message: string,
) {
  await expect
    .poll(
      () =>
        page.evaluate(
          () =>
            document.documentElement.scrollWidth -
            document.documentElement.clientWidth,
        ),
      { message, timeout: LONG_TIMEOUT },
    )
    .toBeLessThanOrEqual(0);
}
