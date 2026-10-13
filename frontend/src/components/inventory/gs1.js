import { parseBarcode } from "gs1-barcode-parser-mod";

/** ASCII 29, the group separator a scanner substitutes for GS1's FNC1. */
const GROUP_SEPARATOR = "\u001d";

const FIELDS = {
  "01": "gtin",
  10: "lotNumber",
  17: "expirationDate",
  21: "serial",
};

export const parseGs1 = (scanned) => {
  const raw = (scanned || "").trim();
  const result = { raw, recognised: false, fields: {}, unparsed: [] };
  if (!raw) return result;

  // A bare EAN-8, UPC-A, EAN-13 or GTIN-14, which the library rejects. Eight
  // digits could also be AI 17 and a date, but no label prints a lone expiry.
  if (/^\d{8}$|^\d{12,14}$/.test(raw)) {
    result.fields.gtin = raw;
    result.recognised = true;
    return result;
  }

  let rest = raw;
  // The library rejects the leading separator some scanners emit.
  while (rest.startsWith(GROUP_SEPARATOR)) rest = rest.slice(1);

  let elements;
  try {
    elements = parseBarcode(rest).parsedCodeItems;
  } catch {
    result.unparsed.push(raw);
    return result;
  }

  elements.forEach(({ ai, data, raw: digits, isoDate }) => {
    const key = FIELDS[ai];
    if (key === "expirationDate") {
      // The library rolls an impossible date forward: Feb 31 becomes Mar 3.
      if (isoDate.slice(5, 7) === digits.slice(2, 4)) {
        result.fields.expirationDate = isoDate;
      } else {
        result.unparsed.push(`${ai}${digits}`);
      }
    } else if (key) {
      result.fields[key] = data;
    }
  });
  result.recognised = Object.keys(result.fields).length > 0;
  return result;
};

/** A UPC or EAN left-padded with zeros is the same product's GTIN-14. */
export const gtinMatches = (a, b) => {
  if (!a || !b) return false;
  const strip = (value) => String(value).trim().replace(/^0+/, "");
  return strip(a) === strip(b);
};
