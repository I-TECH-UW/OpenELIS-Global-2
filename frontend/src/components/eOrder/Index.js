import { React, useRef, useState } from "react";
import EOrderSearch from "./EOrderSearch";
import EOrder from "./EOrder";

export { default as EOrderSearch } from "./EOrderSearch";
export { default as EOrder } from "./EOrder";

const EOrderPage = () => {
  const eOrderRef = useRef(null);
  const [eOrders, setEOrders] = useState([]);
  return (
    <>
      <EOrderSearch setEOrders={setEOrders} eOrderRef={eOrderRef} />
      <EOrder eOrderRef={eOrderRef} eOrders={eOrders} setEOrders={setEOrders} />
    </>
  );
};

export default EOrderPage;
