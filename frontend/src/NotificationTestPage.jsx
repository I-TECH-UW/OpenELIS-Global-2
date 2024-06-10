import { useState } from "react";
import { postToOpenElisServer } from "./components/utils/Utils";

export default function NotificationTestPage() {
  const [isOpen, setIsOpen] = useState(false);

  const [data, setData] = useState({
    message: undefined,
  });

  const submit = () => {
    
    postToOpenElisServer("/rest/notification",JSON.stringify(data),()=>{
      console.log("success")
    });
  }

  

  return (
    <div>
     
      <input type="text" name="message" id="message"  value={data.message??""} onChange={(e)=>setData({...data,message:e.target.value})} />
      <button 
      onClick={()=>submit()}
      >submit</button>
    </div>
  );
}
